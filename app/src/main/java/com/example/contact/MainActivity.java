package com.example.contact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
    DBHelper db;
    ArrayList<String> nom,prenom,email,numero_tel;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DBHelper(this);
        recyclerView=findViewById(R.id.listView);
        nom=new ArrayList<>();
        prenom=new ArrayList<>();
        email=new ArrayList<>();
        numero_tel=new ArrayList<>();
        displayData();
        customAdapter=new CustomAdapter(MainActivity.this,this,nom,prenom,email,numero_tel);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        Button btnAjouter=findViewById(R.id.ajouter);
        btnAjouter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Ajouter.class));
            }

        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    void displayData(){
        Cursor cr= db.getData();
        if(cr.getCount()==0){
            Toast.makeText(this,"no data",Toast.LENGTH_LONG).show();

        }else {
            while (cr.moveToNext()){
               nom.add(cr.getString(0)) ;
               prenom.add(cr.getString(1));
               email.add(cr.getString(2));
               numero_tel.add(cr.getString(3));
            }
        }
    }
}