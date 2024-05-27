package com.triana.libreria_;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;
    private Button registerButton;
    private TextView signupTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        signupTextView = findViewById(R.id.signupTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String userPassword = password.getText().toString();

                if(email.isEmpty() || userPassword.isEmpty()) {
                    Toast.makeText(Login.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("clientes");
                    ref.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // El usuario existe, ahora verificamos el documento
                                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                                    String document = userSnapshot.child("document").getValue(String.class);
                                    if (document.equals(userPassword)) {
                                        // Inicio de sesión exitoso
                                        Toast.makeText(Login.this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                        return;
                                    }
                                }
                                // El documento no coincide
                                Toast.makeText(Login.this, "Error en el documento", Toast.LENGTH_SHORT).show();
                            } else {
                                // El usuario no existe
                                Toast.makeText(Login.this, "Error en el usario", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Error en la consulta a la base de datos
                            Toast.makeText(Login.this, "Error en la consulta a la base de datos", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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

