package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
EditText nom_txt,prenom_txt,email_txt,numero_tel_txt;
Button modifier,supprimer ,annuler;
String nom,prenom,email,numero_tel;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db=new DBHelper(this);
        nom_txt=findViewById(R.id.nomUpdate);
        prenom_txt=findViewById(R.id.prenomUpdate);
        email_txt=findViewById(R.id.emailUpdate);
        numero_tel_txt=findViewById(R.id.numero_telUpdate);
        modifier=findViewById(R.id.Modifier);
        supprimer=findViewById(R.id.supprimer);
        annuler=findViewById(R.id.AnnulerUpdate);
        GetIntentData();
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prenom=prenom_txt.getText().toString().trim();
                email=email_txt.getText().toString().trim();
                numero_tel=numero_tel_txt.getText().toString().trim();
                boolean result= db.UpdateData(nom,prenom,email,numero_tel);
                if(result==true){

                    Toast.makeText(UpdateActivity.this," contact Modifier ",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                }
                else {
                    Toast.makeText(UpdateActivity.this,"La modification a échoué ",Toast.LENGTH_LONG).show();
                }

            }
        });

supprimer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        confirmeDialog();
    }
});
annuler.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(UpdateActivity.this, MainActivity.class));
    }
});
    }

    void GetIntentData(){
        if(getIntent().hasExtra("nom")&& getIntent().hasExtra("prenom")&&getIntent().hasExtra("email")&&getIntent().hasExtra("numero_tel")){
            nom=getIntent().getStringExtra("nom");
            prenom=getIntent().getStringExtra("prenom");
            email=getIntent().getStringExtra("email");
            numero_tel=getIntent().getStringExtra("numero_tel");

            nom_txt.setText(nom);
            prenom_txt.setText(prenom);
            email_txt.setText(email);
            numero_tel_txt.setText(numero_tel);

        }else{
            Toast.makeText(this,"no data",Toast.LENGTH_LONG).show();
        }
    }
    void confirmeDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("supprimer "+ nom + " !!!");
        builder.setMessage("Etes-vous sûr que vous voulez supprimer"+nom +"!!");
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.DeleteData(nom);
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            }
        });
        builder.setNegativeButton("non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            }
        });
        builder.create().show();
    }
}

