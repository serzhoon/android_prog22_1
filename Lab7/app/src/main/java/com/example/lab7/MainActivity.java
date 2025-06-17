package com.example.lab7;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText fullName, login, email, phoneNumber, password, confirmPassword, birthDate;
    private Spinner teachersSpinner;
    private CheckBox agreeCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullName = findViewById(R.id.full_name);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        birthDate = findViewById(R.id.birth_date);
        teachersSpinner = findViewById(R.id.teachers_spinner);
        agreeCheckbox = findViewById(R.id.agree_checkbox);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.teachers_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teachersSpinner.setAdapter(adapter);

        findViewById(R.id.register_button).setOnClickListener(view -> {
            if (validateInput()) {
                Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInput() {
        if (!fullName.getText().toString().matches("^[А-Яа-яЁё\\-\\s]+$")) {
            fullName.setError("Введите ФИО на кириллице");
            return false;
        }

        if (!login.getText().toString().matches("^[a-zA-Z]+$")) {
            login.setError("Логин должен содержать только латинские буквы");
            return false;
        }

        if (!Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$").matcher(email.getText().toString()).matches()) {
            email.setError("Введите корректный email");
            return false;
        }

        if (!phoneNumber.getText().toString().matches("^\\+\\d{1,3}\\d{10}$")) {
            phoneNumber.setError("Введите номер в международном формате");
            return false;
        }

        if (password.getText().toString().length() < 6) {
            password.setError("Пароль должен быть не менее 6 символов");
            return false;
        }

        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError("Пароли не совпадают");
            return false;
        }

        if (!birthDate.getText().toString().matches("^\\d{2}\\.\\d{2}\\.\\d{4}$")) {
            birthDate.setError("Введите дату в формате ДД.ММ.ГГГГ");
            return false;
        }

        String[] dateParts = birthDate.getText().toString().split("\\.");
        int year = Integer.parseInt(dateParts[2]);
        if (year < 1900) {
            birthDate.setError("Год рождения не может быть ранее 1900 года");
            return false;
        }

        if (!agreeCheckbox.isChecked()) {
            Toast.makeText(this, "Вы должны согласиться на обработку данных", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}