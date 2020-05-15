package com.myapplicationdev.android.lp2_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnStore;
    EditText txtData;
    ListView lv;
    CustomAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStore = findViewById(R.id.btnStore);
        txtData = findViewById(R.id.txtData);
        lv = findViewById(R.id.mylist);

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertToDoData(new TODO(0,getDate(),txtData.getText().toString().trim()));
                db.close();
                Toast.makeText(MainActivity.this, "Stored", Toast.LENGTH_SHORT).show();
                txtData.setText("");
                loadValues();
            }
        });


    }

    protected String getDate(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = df.format(c);
        Log.i("Today's date : ", formattedDate);
        return formattedDate;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DBHelper dbhelper = new DBHelper(this);
        switch (item.getItemId()) {
            case R.id.mnuRefresh:
                loadValues();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void loadValues() {
        DBHelper db = new DBHelper(MainActivity.this);
        ArrayList<TODO> data = db.getDetails();
        ca = new CustomAdapter(MainActivity.this,  data);
        lv.setAdapter(ca);
        db.close();
    }
}
