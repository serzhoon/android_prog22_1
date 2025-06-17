package com.example.lab12_13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GameFragment extends Fragment implements SnakeGame.GameEventListener {
    private FrameLayout gameContainer;
    private Button playButton;
    private TextView scoreTextView;
    private SnakeGame snakeGame;

    public GameFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game, container, false);
        gameContainer = root.findViewById(R.id.gameContainer);
        playButton = root.findViewById(R.id.playButton);
        scoreTextView = root.findViewById(R.id.scoreTextView);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    public void startGame() {

        playButton.setVisibility(View.GONE);
        scoreTextView.setVisibility(View.GONE);

        // Создаём экземпляр игры
        snakeGame = new SnakeGame(getContext(), this);

        // Добавляем игровую область
        gameContainer.addView(snakeGame);

        // Запускаем игру
        snakeGame.startGame();


        gameContainer.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeRight() {
                snakeGame.changeDirection(SnakeGame.Direction.RIGHT);
            }
            @Override
            public void onSwipeLeft() {
                snakeGame.changeDirection(SnakeGame.Direction.LEFT);
            }
            @Override
            public void onSwipeUp() {
                snakeGame.changeDirection(SnakeGame.Direction.UP);
            }
            @Override
            public void onSwipeDown() {
                snakeGame.changeDirection(SnakeGame.Direction.DOWN);
            }
        });
    }

    @Override
    public void onGameOver(final int score) {
        if(getActivity() != null) {
            getActivity().runOnUiThread(new Runnable(){
                @Override
                public void run() {

                    gameContainer.removeView(snakeGame);
                    snakeGame = null;


                    scoreTextView.setText("Конец игры! Счет: " + score);
                    scoreTextView.setVisibility(View.VISIBLE);
                    playButton.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    public void onScoreUpdated(final int score) {
        if(getActivity() != null) {
            getActivity().runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    scoreTextView.setText("Счет: " + score);
                }
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(snakeGame != null){
            snakeGame.pauseGame();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(snakeGame != null){
            snakeGame.resumeGame();
        }
    }
}