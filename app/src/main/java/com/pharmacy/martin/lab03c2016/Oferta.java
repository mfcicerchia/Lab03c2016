package com.pharmacy.martin.lab03c2016;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Oferta extends AppCompatActivity {

    private String oferta;
    private Integer moneda, idJob;
    private Categoria categoria;
    private Spinner spnrCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta);
        /**
         * Recibimos los parametros de la actividad que nos invoca
         */
        Bundle bundle = getIntent().getExtras();
        idJob = bundle.getInt("idTrabajo");
        /**
         * Inicializamos la lista con todas las categorías para el Spinner
         */
        ArrayList<Categoria> catList = new ArrayList<Categoria>();
        Categoria cat = new Categoria();
        for (int i=0; i< Categoria.CATEGORIAS_MOCK.length ; i++){
            catList.add(cat.CATEGORIAS_MOCK[i]);
        }
        /**
         *Seteamos el Spinner
         */
        spnrCategorias = (Spinner) findViewById(R.id.spnrCategorias);
        ArrayList<Categoria> catStrng = new ArrayList<Categoria>(catList);
        ArrayAdapter<Categoria> adaptador = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_item,catStrng);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnrCategorias.setAdapter(adaptador);
        spnrCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoria = (Categoria) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /**
         * Inicializamos e implementamos la logica del RadioGroup
         */
        final RadioGroup rgMoneda = (RadioGroup) findViewById(R.id.rgMonedas);
        rgMoneda.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rbUS:
                        moneda = getResources().getInteger(R.integer.US);
                        break;
                    case R.id.rbUE:
                        moneda = getResources().getInteger(R.integer.UE);
                        break;
                    case R.id.rbAR:
                        moneda = getResources().getInteger(R.integer.AR);
                        break;
                    case R.id.rbUK:
                        moneda = getResources().getInteger(R.integer.UK);
                        break;
                    case R.id.rbBR:
                        moneda = getResources().getInteger(R.integer.BR);
                        break;
                }
                Log.d("onCheckedChanged", String.valueOf(moneda));
            }
        });

        /**
         * Inicializamos el EditText y almacenamos su contenido
         */
        EditText etOferta = (EditText) findViewById(R.id.etOferta);
        etOferta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                oferta = ((EditText) findViewById(R.id.etOferta)).getText().toString();
                if (oferta.length()==0){
                    oferta = null;
                }
            }
        });
        /**
         * Inicializamos el boton e implementamos su logica
         */
        Button confirmar = (Button) findViewById(R.id.btnConfirmarOferta);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(categoria != null && oferta != null && moneda != null){
                    Trabajo job = new Trabajo(idJob,oferta,categoria);
                    job.setMonedaPago(moneda);
                    Intent i = getIntent();
                    i.putExtra("Resultado",job);
                    setResult(RESULT_OK,i);
                    finish();
                }
                else{
                    Toast.makeText(Oferta.this.getBaseContext(), "Ha ingresado algun dato incorrectamente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Metodos para conservar los datos al girar la pantalla
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("oferta",oferta);
        final RadioGroup rgMoneda = (RadioGroup) findViewById(R.id.rgMonedas);
        outState.putInt("moneda", rgMoneda.getCheckedRadioButtonId());
        outState.putInt("posicion", spnrCategorias.getSelectedItemPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText etOferta = (EditText) findViewById(R.id.etOferta);
        etOferta.setText(savedInstanceState.getString("oferta"));
        spnrCategorias.setSelection(savedInstanceState.getInt("posicion"));
        RadioGroup rgMoneda = (RadioGroup) findViewById(R.id.rgMonedas);
        rgMoneda.check(savedInstanceState.getInt("moneda"));

    }

}
