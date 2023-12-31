package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.Toast;

public class Ajouter extends AppCompatActivity {
    EditText editTextNom,editTextPrenom,editTextEmail,editTextNumeroTel;
Button insert,reset;

DBHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
        editTextNom=findViewById(R.id.nom);
        editTextPrenom=findViewById(R.id.prenom);
        editTextEmail=findViewById(R.id.email);
        editTextNumeroTel=findViewById(R.id.numero_tel);
insert=findViewById(R.id.sauvegarder);
        reset=findViewById(R.id.Annuler);

db=new DBHelper(this);

insert.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String nomText=editTextNom.getText().toString();
        String prenomText=editTextPrenom.getText().toString();
        String emailText=editTextEmail.getText().toString();
        String numeroText=editTextNumeroTel.getText().toString();
        Boolean checkInsertData=db.insertData(nomText,prenomText,emailText,numeroText);

        if(checkInsertData==true){
            Toast.makeText(Ajouter.this," contact Ajouter ",Toast.LENGTH_LONG).show();
            startActivity(new Intent(Ajouter.this, MainActivity.class));
        }else {
            Toast.makeText(Ajouter.this," erreur d'Ajouter ",Toast.LENGTH_LONG).show();
        }
    }
});

reset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(Ajouter.this, MainActivity.class));
    }
});


    }
}