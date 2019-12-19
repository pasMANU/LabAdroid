package com.example.labo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Etudiant extends AppCompatActivity {
    TextView T;
    private ArrayList<String> listFonctionalite;
    private ArrayAdapter<String> listAdapter;
    private ListView MyListView;
    public dbWorker dbw ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant);
        dbw= new dbWorker(this);

        T=(TextView)findViewById(R.id.name);

        Bundle b = getIntent().getExtras();
        T.setText(b.getString("nom"));

        InitialiseArraylist();
        InitialiseArrayAdapter();



        MyListView = (ListView)findViewById(R.id.list1);
        MyListView.setAdapter(listAdapter);

        MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),"You just selected: " + ((TextView)view).getText().toString(),Toast.LENGTH_LONG).show();
                if( ((TextView)view).getText().toString()=="Mes Information")
                {
                    Bundle NomEtudiant = getIntent().getExtras();
                    // Bundle IdEtudiant = getIntent().getExtras();
                    String idE=NomEtudiant.getString("id");
                    int x = Integer.parseInt(idE);
                    String EtudNom = NomEtudiant.getString("nom");
                    Toast.makeText(getApplicationContext(),"You just selected: " + NomEtudiant.getString("nom"),Toast.LENGTH_LONG).show();
                    String Stat="selectall";
                    dbw.execute(Stat,x);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"You just selected: " + ((TextView)view).getText().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

        MyListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listFonctionalite.remove(position);
                listAdapter.notifyDataSetChanged();
                return  true;
            }
        });
    }

    private void InitialiseArraylist()
    {
        listFonctionalite = new ArrayList<>();
        listFonctionalite.add("Mes Information");
        listFonctionalite.add("Activité");
    }

    private void InitialiseArrayAdapter()
    {
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listFonctionalite);
    }

}
