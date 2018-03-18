package com.example.fina.fina_1202150284_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListNamaMahasiswaAsyncTask extends AppCompatActivity {

    Handler h = new Handler();
    int status = 0;
    private ProgressDialog pDialog;
    private ListView item;
    public String NamaMhs[] = new String[]{
            "Fina", "Ari", "Bayu", "Rahma", "bobby", "Maudya", "jhony", "Justin", "Angel", "Achmad"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama_mahasiswa_async_task);

        item = findViewById(R.id.ListAsyntask);
        //set array kedalam adapter
        item.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
    }

    class AsyncTaskView extends AsyncTask<Void, String, String> {
        ArrayAdapter<String> adapter;

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) item.getAdapter(); //get array dari adapter
            pDialog = new ProgressDialog(ListNamaMahasiswaAsyncTask.this);
            pDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            pDialog.setTitle("Loading Data");
            pDialog.setMax(NamaMhs.length);
            pDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (String Names : NamaMhs) { //Perulangan untuk show list nama
                publishProgress(Names);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Semua nama telah terdaftar";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            status++; //loading increment bar
            pDialog.setProgress(status);
            pDialog.setMessage(String.valueOf(status));
            //kondisi jika progress sudah maksimal makan progress dialognya kek close
            if (status == NamaMhs.length) {
                pDialog.dismiss();
            }
        }

        @Override
        protected void onPostExecute (String r){ // result setelah selesai diproses
            Toast.makeText(ListNamaMahasiswaAsyncTask.this, r, Toast.LENGTH_LONG).show();
        }
    }

    public void Start (View view){
        AsyncTaskView a = new AsyncTaskView();
        a.execute();
    }
}