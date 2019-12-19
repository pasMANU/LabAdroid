package com.example.labo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EtudiantInformation extends AppCompatActivity {

    EditText nom,prenom,identifiant,pass;
    public dbWorker dbw ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_information);

        dbw= new dbWorker(this);


        nom=(EditText)findViewById(R.id.nom);
        prenom=(EditText)findViewById(R.id.prenom);
        identifiant=(EditText)findViewById(R.id.identifiant);
        pass=(EditText)findViewById(R.id.passwrd);

        Bundle b = getIntent().getExtras();
        nom.setText(b.getString("name"));
        prenom.setText(b.getString("prenom"));
        identifiant.setText(b.getString("identifiant"));
        pass.setText(b.getString("pw"));

    }

    public void Update(View v)
    {
        //String userN = this.textuser.getText().toString();
        Bundle b = getIntent().getExtras();

        int idval=b.getInt("id");
        String nomnw=this.nom.getText().toString();
        String prenomnw=this.prenom.getText().toString();
        String identifiantnw=this.identifiant.getText().toString();
        String passnw=this.pass.getText().toString();
        String etat="udpdate";

        dbWorker dbw = new dbWorker(this);
        dbw.execute(etat,idval,nomnw,prenomnw,identifiantnw,passnw);

        // Toast.makeText(getApplicationContext(),"You just selected: ",Toast.LENGTH_LONG).show();
    }
}
