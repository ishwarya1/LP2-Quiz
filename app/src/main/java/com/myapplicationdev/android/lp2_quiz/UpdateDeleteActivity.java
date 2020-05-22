package com.myapplicationdev.android.lp2_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateDeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        final TODO todo= (TODO) getIntent().getSerializableExtra("listItem");

        TextView txtId=findViewById(R.id.txtId);
        TextView txtDate=findViewById(R.id.txtDate);
        TextView txtContent=findViewById(R.id.txtContent);
        final EditText edtContent=findViewById(R.id.edtContent);
        Button btnUpdate=findViewById(R.id.btnUpdate);
        Button btnDelete=findViewById(R.id.btnDelete);


        txtId.setText(String.valueOf(todo.getId()));
        txtDate.setText(todo.getDate());
        txtContent.setText(todo.getData());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtContent.getText().toString().trim().length()>0) {
                    MainActivity.loadFlag=1;
                    DBHelper db = new DBHelper(UpdateDeleteActivity.this);
                    todo.setData(edtContent.getText().toString().trim());
                    String msg = db.updatetodoDetails(todo);
                    Toast.makeText(UpdateDeleteActivity.this, msg, Toast.LENGTH_SHORT).show();
                    edtContent.setText("");
                    db.close();
                }else
                {
                    Toast.makeText(UpdateDeleteActivity.this, "Please fill the content", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.loadFlag=1;
                DBHelper db = new DBHelper(UpdateDeleteActivity.this);
                String msg=db.deletetodoDetails(todo);
                Toast.makeText(UpdateDeleteActivity.this,msg,Toast.LENGTH_SHORT).show();
                db.close();
            }
        });
    }


}
