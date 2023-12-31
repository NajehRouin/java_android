package com.example.contact;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
  private Context context;
  Activity activity;
   private ArrayList nom,prenom,email,numero_tel;

   CustomAdapter(Activity activity , Context context, ArrayList nom,ArrayList prenom,ArrayList email,ArrayList num_tel){
       this.activity=activity;
this.context=context;
this.nom=nom;
this.prenom=prenom;
this.email=email;
this.numero_tel=num_tel;
   }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
      View view=  inflater.inflate(R.layout.my_row,parent,false);


       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

holder.nom_txt.setText(String.valueOf(nom.get(position)));
holder.prenom_txt.setText(String.valueOf(prenom.get(position)));
holder.email_txt.setText(String.valueOf(email.get(position)));
holder.numero_tel_txt.setText(String.valueOf(numero_tel.get(position)));
holder.mainLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(context,UpdateActivity.class);
        intent.putExtra("nom",String.valueOf(nom.get(position)));
        intent.putExtra("prenom",String.valueOf(prenom.get(position)));
        intent.putExtra("email",String.valueOf(email.get(position)));
        intent.putExtra("numero_tel",String.valueOf(numero_tel.get(position)));
        activity.startActivityForResult(intent,1);

    }
});
    }

    @Override
    public int getItemCount() {
        return nom.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
TextView nom_txt,prenom_txt,email_txt,numero_tel_txt;
LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom_txt=itemView.findViewById(R.id.nom_txt);
            prenom_txt=itemView.findViewById(R.id.prenom_txt);
            email_txt=itemView.findViewById(R.id.email_txt);
            numero_tel_txt=itemView.findViewById(R.id.numero_tel_txt);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
