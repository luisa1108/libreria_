package com.triana.libreria_;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.squareup.picasso.Picasso;

public class LibroAdapter extends ArrayAdapter<Libro> {
    public LibroAdapter(Context context, List<Libro> libros) {
        super(context, 0, libros);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Libro libro = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_libro, parent, false);
        }
        TextView tituloLibro = convertView.findViewById(R.id.titulo_libro);
        TextView autorLibro = convertView.findViewById(R.id.autor_libro);
        ImageView imagenLibro = convertView.findViewById(R.id.imagen_libro);

        tituloLibro.setText(libro.getTitulo());
        autorLibro.setText(libro.getAutor());
        // Aquí puedes configurar la imagen del libro si está disponible
        if (!libro.getImagen().isEmpty()) {
            Picasso.get().load(libro.getImagen()).into(imagenLibro);
        }

        return convertView;
    }
}

