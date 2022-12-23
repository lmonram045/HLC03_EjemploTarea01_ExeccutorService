package com.montoyaramirezluis.hlc03_ejemplotarea01_execcutorservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;

public class NetworkUtil {

    public static Bitmap fetchImage(String url) {
        Bitmap bitmapImage = null; // Aquí se almacenará la imagen que se descargue.

        try {
            InputStream inputStream = new URL(url).openStream(); // Abrimos la conexión
            bitmapImage = BitmapFactory.decodeStream(inputStream); // Decodificamos la imagen
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmapImage; // Devolvemos la imagen
    }
}
