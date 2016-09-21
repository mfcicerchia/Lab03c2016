package com.pharmacy.martin.lab03c2016;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static int TRABAJO = 1;

    ListView ofertaLaboral;
    Context context = MainActivity.this;
    ArrayList<Trabajo> lista = new ArrayList<Trabajo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLista(lista);
        setListView(lista, context);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mCrearOferta:
                int idJob = lista.size();
                Intent i = new Intent(this, Oferta.class);
                i.putExtra("idTrabajo",idJob+1);
                startActivityForResult(i, TRABAJO);


                break;
            /*case R.id.mAbout:
                DO STUFF
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }

    private void setLista(ArrayList<Trabajo> lista){
        Trabajo trabajo = new Trabajo();
        for (int i=0; i<18;i++){
            lista.add(trabajo.TRABAJOS_MOCK[i]);
        }
    }

    private void addLista(ArrayList<Trabajo> lista, Trabajo trabajo){
        lista.add(trabajo);
    }

    private void setListView(ArrayList<Trabajo> lista, Context context){
        ofertaLaboral = (ListView) findViewById(R.id.lvOfertaLaboral);
        ofertaLaboral.setAdapter(new Adaptador(context,lista));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Trabajo job =(Trabajo) data.getExtras().get("Resultado");
            addLista(lista, job);
            setListView(lista, context);

        }
        else{
            Log.d("AVISO--->","ALGO SALIÓ MAL");
        }
    }
}
