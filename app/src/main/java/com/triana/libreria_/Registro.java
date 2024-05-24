package com.triana.libreria_;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    private EditText name;
    private EditText document;
    private EditText email;
    private Button registerButton;

    // Referencia a la base de datos de Firebase
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        name = findViewById(R.id.name);
        document = findViewById(R.id.document);
        email = findViewById(R.id.email);
        registerButton = findViewById(R.id.register_button);

        // Inicializa la referencia a la base de datos de Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Crea un nuevo cliente con los datos ingresados
                Map<String, Object> cliente = new HashMap<>();
                cliente.put("name", name.getText().toString());
                cliente.put("document", document.getText().toString());
                cliente.put("email", email.getText().toString());

                // Guarda el cliente en la base de datos de Firebase bajo la definición de "clientes"
                mDatabase.child("clientes").push().setValue(cliente).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Los datos se guardaron correctamente
                            Toast.makeText(Registro.this, "Los datos se guardaron correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            // Hubo un error al guardar los datos
                            Toast.makeText(Registro.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
            }
        });
    }
}

