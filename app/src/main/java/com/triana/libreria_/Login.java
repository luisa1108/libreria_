package com.triana.libreria_;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity{
    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(Login.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }else{
                    // Add your login logic here
                    Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
