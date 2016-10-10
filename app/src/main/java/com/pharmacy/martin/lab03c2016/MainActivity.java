package com.pharmacy.martin.lab03c2016;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final static int TRABAJO = 1;

    ListView ofertaLaboral;
    Context context = MainActivity.this;
    ArrayList<Trabajo> lista = new ArrayList<Trabajo>();
    private Adaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLista(lista);
        adapter = new Adaptador(context, lista);
        ofertaLaboral = (ListView) findViewById(R.id.lvOfertaLaboral);
        ofertaLaboral.setAdapter(adapter);
        registerForContextMenu(ofertaLaboral);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(((Trabajo) ofertaLaboral.getAdapter().getItem(info.position)).getDescripcion());
        inflater.inflate(R.menu.contextual_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.mcPostular:
                Toast.makeText(context, "Se registró a la postulación", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mcCompartir:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");

                String moneda = new String();
                switch (((Trabajo) ofertaLaboral.getAdapter().getItem(info.position)).getMonedaPago()){
                    case 1:
                        moneda = getResources().getString(R.string.monedaUS);
                        break;
                    case 2:
                        moneda = getResources().getString(R.string.monedaUE);
                        break;
                    case 3:
                        moneda = getResources().getString(R.string.monedaAR);
                        break;
                    case 4:
                        moneda = getResources().getString(R.string.monedaUK);
                        break;
                    case 5:
                        moneda = getResources().getString(R.string.monedaBR);
                        break;
                }

                String ingles = new String();
                if (((Trabajo) ofertaLaboral.getAdapter().getItem(info.position)).getRequiereIngles()){
                    ingles = "Si";
                }
                else{
                    ingles = "No";
                }

                Format formatterDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                String mensaje = new String();
                mensaje = "WORK FROM HOME\n"
                        +"Categoría: "+((Trabajo) ofertaLaboral.getAdapter().getItem(info.position)).getCategoria()+"\n"
                        +"Descripción: "+((Trabajo) ofertaLaboral.getAdapter().getItem(info.position)).getDescripcion()+"\n"
                        +"Horas: "+String.format(Locale.getDefault(),"%1$d ",((Trabajo) ofertaLaboral.getAdapter().getItem(info.position)).getHorasPresupuestadas())+"\n"
                        +"Precio horas: "+ String.format(Locale.getDefault(),"%1$.2f ",((Trabajo) ofertaLaboral.getAdapter().getItem(info.position)).getPrecioMaximoHora())+"\n"
                        +"Moneda: "+moneda+"\n"
                        +"Requiere ingles: "+ingles+"\n"
                        +"Fecha de entrega: "+formatterDate.format(((Trabajo) ofertaLaboral.getAdapter().getItem(info.position)).getFechaEntrega());
                intent.putExtra(Intent.EXTRA_TEXT, mensaje);
                startActivity(Intent.createChooser(intent, "Enviar por"));

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void setLista(ArrayList<Trabajo> lista){
        Trabajo trabajo = new Trabajo();
        for (int i=0; i<Trabajo.TRABAJOS_MOCK.length; i++){
            lista.add(trabajo.TRABAJOS_MOCK[i]);
        }
    }

    private void addLista(ArrayList<Trabajo> lista, Trabajo trabajo){
        lista.add(trabajo);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Trabajo job =(Trabajo) data.getExtras().get("Resultado");
            addLista(lista, job);
            adapter.notifyDataSetChanged();
        }
        else{
            Log.d("AVISO--->","ALGO SALIÓ MAL");
        }
    }
}
