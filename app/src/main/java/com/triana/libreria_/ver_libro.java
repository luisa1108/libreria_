package com.triana.libreria_;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class ver_libro extends AppCompatActivity {

    private TextView tituloTextView;
    private TextView autorTextView;
    private ImageView imagenImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_libro);

        // Inicializar las vistas
        tituloTextView = findViewById(R.id.tituloTextView);
        autorTextView = findViewById(R.id.autorTextView);
        imagenImageView = findViewById(R.id.imagenImageView);

        // Recuperar el objeto Libro del Intent
        Libro libro = (Libro) getIntent().getSerializableExtra("libro");
        if (libro != null) {
            // Mostrar la información del libro
            tituloTextView.setText(libro.getTitulo());
            autorTextView.setText(libro.getAutor());
            Picasso.get().load(libro.getImagen()).into(imagenImageView);

            // Abrir la URL de lectura en el navegador al hacer clic en la imagen
            imagenImageView.setOnClickListener(v -> {
                String urlLectura = libro.getUrlLectura();
                if (urlLectura != null && !urlLectura.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urlLectura));
                    startActivity(intent);
                } else {
                    Toast.makeText(ver_libro.this, "URL de lectura no disponible", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

