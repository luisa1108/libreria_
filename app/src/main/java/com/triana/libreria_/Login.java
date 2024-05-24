package com.triana.libreria_;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(Login.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(Login.this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();

                // Inicia MainActivity
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });

         signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad de registro
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });
    }
}

