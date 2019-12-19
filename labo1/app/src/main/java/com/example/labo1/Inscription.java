package com.example.labo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Inscription extends AppCompatActivity {
    EditText nom,prenom,identifiant,pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        nom = (EditText)findViewById(R.id.nom);
        prenom = (EditText)findViewById(R.id.prenom);
        identifiant = (EditText)findViewById(R.id.identifiant);
        pw = (EditText)findViewById(R.id.passwrd);
    }


    public void Insert(View v)
    {
        String nomN = this.nom.getText().toString();
        String prenomP = this.prenom.getText().toString();
        String identifiantI = this.identifiant.getText().toString();
        String pwP = this.pw.getText().toString();
        String stat="insc";


        dbWorker dbw = new dbWorker(this);
        dbw.execute(stat,nomN,prenomP,identifiantI,pwP);
    }
}
