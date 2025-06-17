package com.example.lab12_13;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends View {
    private static final int CELL_SIZE = 40;
    private static final int INITIAL_SNAKE_LENGTH = 5;
    private static final long GAME_UPDATE_INTERVAL = 200;

    private Paint snakePaint;
    private Paint foodPaint;
    private LinkedList<Point> snake;
    private Point food;
    private Direction currentDirection = Direction.RIGHT;
    private int gameWidthInCells;
    private int gameHeightInCells;
    private boolean isGameOver = false;
    private int score = 0;
    private GameEventListener gameEventListener;
    private Handler handler;
    private boolean isGameRunning = false;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
    }

    public interface GameEventListener {
        void onGameOver(int score);
        void onScoreUpdated(int score);
    }

    public SnakeGame(Context context, GameEventListener listener) {
        super(context);
        this.gameEventListener = listener;
        snakePaint = new Paint();
        snakePaint.setColor(Color.GREEN);
        foodPaint = new Paint();
        foodPaint.setColor(Color.RED);
        handler = new Handler();
    }

    public void startGame() {
        score = 0;
        if (gameEventListener != null) {
            gameEventListener.onScoreUpdated(score);
        }
        gameWidthInCells = getWidth() / CELL_SIZE;
        gameHeightInCells = getHeight() / CELL_SIZE;
        snake = new LinkedList<>();
        for (int i = 0; i < INITIAL_SNAKE_LENGTH; i++) {
            snake.addFirst(new Point(i, gameHeightInCells / 2));
        }
        currentDirection = Direction.RIGHT;
        placeFood();
        isGameOver = false;
        isGameRunning = true;
        handler.postDelayed(gameLoop, GAME_UPDATE_INTERVAL);
    }

    public void pauseGame() {
        isGameRunning = false;
        handler.removeCallbacks(gameLoop);
    }

    public void resumeGame() {
        if (!isGameOver) {
            isGameRunning = true;
            handler.postDelayed(gameLoop, GAME_UPDATE_INTERVAL);
        }
    }

    private Runnable gameLoop = new Runnable() {
        @Override
        public void run() {
            if (isGameRunning && !isGameOver) {
                updateGame();
                invalidate();
                handler.postDelayed(this, GAME_UPDATE_INTERVAL);
            }
        }
    };

    private void updateGame() {
        Point head = snake.getFirst();
        Point newHead = null;
        switch (currentDirection) {
            case UP:
                newHead = new Point(head.x, head.y - 1);
                break;
            case DOWN:
                newHead = new Point(head.x, head.y + 1);
                break;
            case LEFT:
                newHead = new Point(head.x - 1, head.y);
                break;
            case RIGHT:
                newHead = new Point(head.x + 1, head.y);
                break;
        }
        if (newHead.x < 0 || newHead.x >= gameWidthInCells || newHead.y < 0 || newHead.y >= gameHeightInCells) {
            gameOver();
            return;
        }
        for (Point p : snake) {
            if (newHead.equals(p)) {
                gameOver();
                return;
            }
        }
        if (newHead.equals(food)) {
            snake.addFirst(newHead);
            score++;
            if (gameEventListener != null) {
                gameEventListener.onScoreUpdated(score);
            }
            placeFood();
        } else {
            snake.addFirst(newHead);
            snake.removeLast();
        }
    }

    private void placeFood() {
        Random random = new Random();
        int x, y;
        boolean onSnake;
        do {
            x = random.nextInt(gameWidthInCells);
            y = random.nextInt(gameHeightInCells);
            food = new Point(x, y);
            onSnake = false;
            for (Point p : snake) {
                if (food.equals(p)) {
                    onSnake = true;
                    break;
                }
            }
        } while (onSnake);
    }

    private void gameOver() {
        isGameOver = true;
        isGameRunning = false;
        if (gameEventListener != null) {
            gameEventListener.onGameOver(score);
        }
    }

    public void changeDirection(Direction newDirection) {
        if ((currentDirection == Direction.UP && newDirection == Direction.DOWN) ||
                (currentDirection == Direction.DOWN && newDirection == Direction.UP) ||
                (currentDirection == Direction.LEFT && newDirection == Direction.RIGHT) ||
                (currentDirection == Direction.RIGHT && newDirection == Direction.LEFT)) {
            return;
        }
        currentDirection = newDirection;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (snake != null && food != null) {
            // Рисуем еду
            canvas.drawRect(food.x * CELL_SIZE, food.y * CELL_SIZE,
                    (food.x + 1) * CELL_SIZE, (food.y + 1) * CELL_SIZE, foodPaint);
            // Рисуем змейку
            for (Point p : snake) {
                canvas.drawRect(p.x * CELL_SIZE, p.y * CELL_SIZE,
                        (p.x + 1) * CELL_SIZE, (p.y + 1) * CELL_SIZE, snakePaint);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        gameWidthInCells = w / CELL_SIZE;
        gameHeightInCells = h / CELL_SIZE;
        if (isGameRunning || isGameOver) {
            startGame();
        }
    }
}