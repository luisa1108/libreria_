package com.triana.libreria_;

import org.json.JSONObject;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Api_libro {
    public static Libro obtenerInfoLibro(String isbn) {
        String apiUrl = "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data";
        HttpURLConnection connection = null;

        try {
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "libreria_/1.0 (johan.triana619@pascualbravo.edu.co)");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONObject bookInfo = jsonObject.getJSONObject("ISBN:" + isbn);

                String titulo = bookInfo.has("title") ? bookInfo.getString("title") : "Título no disponible";
                String imagen = bookInfo.has("cover") ? bookInfo.getJSONObject("cover").getString("medium") : "Imagen no disponible";
                String autor = bookInfo.has("authors") ? bookInfo.getJSONArray("authors").getJSONObject(0).getString("name") : "Autor no disponible";

                // Crear y devolver un objeto Libro con la información obtenida
                return new Libro(titulo, autor, imagen);

            } else {
                System.out.println("GET request not worked");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return new Libro("none", "none", "none");
    }
    // Método para obtener una lista de libros basada en un término de búsqueda
    public static List<Libro> buscarLibros(String terminoBusqueda) {
        List<Libro> librosConImagen = new ArrayList<>();
        String apiUrl = "https://openlibrary.org/search.json?q=" + terminoBusqueda.replace(" ", "+");
        HttpURLConnection connection = null;

        try {
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "libreria_/1.0 (johan.triana619@pascualbravo.edu.co)");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray docs = jsonObject.getJSONArray("docs");

                // Iteramos sobre los resultados y recolectamos los ISBNs
                for (int i = 0; i < docs.length() && i < 20; i++) {
                    JSONObject doc = docs.getJSONObject(i);
                    if (doc.has("isbn")) {
                        JSONArray isbnArray = doc.getJSONArray("isbn");
                        String isbn = isbnArray.getString(0); // Añadimos el primer ISBN de la lista
                        Libro libro = obtenerInfoLibro(isbn);
                        // Verificamos que el libro tenga una imagen antes de añadirlo a la lista
                        if (libro != null && libro.getImagen() != null && !libro.getImagen().isEmpty()) {
                            librosConImagen.add(libro);
                        }
                    }
                }

            } else {
                System.out.println("GET request not worked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return librosConImagen;
    }


}
