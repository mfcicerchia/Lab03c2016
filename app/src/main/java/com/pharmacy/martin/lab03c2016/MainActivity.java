package com.pharmacy.martin.lab03c2016;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView ofertaLaboral;
    Context context = MainActivity.this;
    ArrayList<Trabajo> lista = new ArrayList<Trabajo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLista(lista);
        ofertaLaboral = (ListView) findViewById(R.id.lvOfertaLaboral);
        ofertaLaboral.setAdapter(new Adaptador(context,lista));
    }

    public void setLista(ArrayList<Trabajo> lista){
        Trabajo trabajo = new Trabajo();
        for (int i=0; i<18;i++){
            lista.add(trabajo.TRABAJOS_MOCK[i]);
        }
    }
}
