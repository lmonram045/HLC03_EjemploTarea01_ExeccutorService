package com.montoyaramirezluis.hlc03_ejemplotarea01_execcutorservice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/** Vamos a cargar una imagen desde internet y mostrarla en un ImageView, en un hilo a parte para no
 * bloquear la interfaz de usuario.
 */

public class MainActivity extends AppCompatActivity {

    String mUrl = "https://images.pexels.com/photos/2486168/pexels-photo-2486168.jpeg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.image);

        // ExecutorService service = Executors.newFixedThreadPool(10); // Creamos un pool de 10 hilos
        ExecutorService service = Executors.newSingleThreadExecutor(); // Creamos un solo hilo

        Handler handler = new Handler(Looper.getMainLooper()); // Creamos un Handler para poder actualizar la interfaz de usuario

        // Creamos un objeto de la clase que implementa Runnable
        service.execute(new Runnable() {
            @Override
            public void run() {
                // Este codigo se ejecutará en un hilo a parte, no bloqueará la interfaz de usuario.
                // Aquí se pondría el código que tarda mucho en ejecutarse. (Tarea pesada)
                Bitmap bitmap = NetworkUtil.fetchImage(mUrl); // Descargamos la imagen


                /** Podemos usar el metodo runOnUiThread() de la clase Activity, pero es mejor usar un
                 * Handler, ya que este último es más eficiente.
                 *
                *runOnUiThread(new Runnable() {
                *    @Override
                *    public void run() {
                *        // Aquí se pondría el código que modifica la interfaz de usuario.
                *        // (Se ejecuta en el hilo principal)
                *        if (bitmap != null) {
                *            imageView.setImageBitmap(bitmap); // Mostramos la imagen en el ImageView
                *        }
                *    }
                *}); */

                // Otra forma de hacerlo es con un Handler
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Aquí se pondría el código que modifica la interfaz de usuario.
                        // (Se ejecuta en el hilo principal)
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap); // Mostramos la imagen en el ImageView
                        }
                    }
                });
            }
        });

    }
}