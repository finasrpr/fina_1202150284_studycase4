package com.example.fina.fina_1202150284_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnListNamaMahasiswa, btnGambar; //membuat objek

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mereferensikan objek menggunakan id yang sudah ada
        btnListNamaMahasiswa = findViewById(R.id.bCariNama);
        btnGambar = findViewById(R.id.bCariGambar);

        btnListNamaMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //ketika button di klik maka akan melakukan sebuah aktivitas
                startActivity(new Intent(MainActivity.this, ListNamaMahasiswaAsyncTask.class));
            }
        });

        btnGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImageAsyncTask.class));
            }
        });
    }
}
