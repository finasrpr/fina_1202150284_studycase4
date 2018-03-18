package com.example.fina.fina_1202150284_studycase4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class ImageAsyncTask extends AppCompatActivity {

    private EditText editURL;
    private Button buttonCari;
    private ImageView imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_async_task);

        editURL = findViewById(R.id.edit_url);
        buttonCari = findViewById(R.id.cari_image);
        imageURL = findViewById(R.id.image_url);
    }

    class LoadImage extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap bitmap = null;

            //menyimpan url dalam sebuah string
            String url = editURL.getText().toString();

            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent()); //decode url
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imageURL.setImageBitmap(bitmap);
            } else {
                Toast.makeText( ImageAsyncTask.this, "Link yang Anda Masukkan Salah", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void Cari(View view) {

        new LoadImage().execute();  //mengekseskusi method LoadImage
    }
}


//
