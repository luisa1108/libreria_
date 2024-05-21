package com.triana.libreria_;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent; // Importa la clase Intent
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Asegúrate de que este sea el layout correcto para el login

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if(username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Aquí tu lógica de inicio de sesión
                    Toast.makeText(Login.this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();

                    // Inicia MainActivity
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}

