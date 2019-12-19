package com.example.labo1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class MainActivity extends AppCompatActivity {

    private EditText textuser;
    private EditText textpw;
    private Button btnloger;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textuser = (EditText)findViewById(R.id.user);
        textpw = (EditText)findViewById(R.id.pw);
        btnloger =(Button) findViewById(R.id.btnloge);


        btnloger.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LogIn(v);
            }
        });







    }

    public void LogIn(View v)
    {
        String userN = this.textuser.getText().toString();
        String userP = this.textpw.getText().toString();
        String stat="log";

        dbWorker dbw = new dbWorker(this);
        dbw.execute(stat,userN,userP);
    }
    public void Next(View v)
    {
        Intent Facteur = new Intent(this,Inscription.class);
        //Facteur.putExtra("mesage",msg.getText().toString());
        startActivity(Facteur);
    }
}
