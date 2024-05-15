package com.triana.libreria_;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private LibroAdapter adapter;
    private List<Libro> listaLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lista_libros);
        listaLibros = new ArrayList<>();
        adapter = new LibroAdapter(this, listaLibros);
        listView.setAdapter(adapter);

        // Iniciar la tarea asíncrona para buscar libros
        new BuscarLibrosTask().execute("muerte");
        // En el método onCreate o después de inicializar tu ListView
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Libro libroSeleccionado = listaLibros.get(position);
            Intent intent = new Intent(MainActivity.this, ver_libro.class);
            intent.putExtra("libro", libroSeleccionado); // Asegúrate de que Libro implemente Serializable
            startActivity(intent);
        });


    }

    // Clase interna para la tarea asíncrona
    private class BuscarLibrosTask extends AsyncTask<String, Void, List<Libro>> {
        @Override
        protected List<Libro> doInBackground(String... terminosBusqueda) {
            // Realizar la búsqueda en un hilo secundario
            return Api_libro.buscarLibros(terminosBusqueda[0]);
        }

        @Override
        protected void onPostExecute(List<Libro> librosEncontrados) {
            // Actualizar la UI en el hilo principal
            if (librosEncontrados != null) {
                listaLibros.clear();
                listaLibros.addAll(librosEncontrados);
                adapter.notifyDataSetChanged();
            }
        }
    }

}

