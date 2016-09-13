package com.pharmacy.martin.lab03c2016;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.graphics.Typeface.BOLD;

/**
 * Created by Martin on 08/09/2016.
 */
public class Adaptador extends BaseAdapter{
    /*Inflater*/
    ArrayList<Trabajo> items = new ArrayList<Trabajo>();
    Context context;
    LayoutInflater inflater;
    Adaptador(Context context ,ArrayList<Trabajo> items) {
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Trabajo getItem(int position) {

        return items.get(position);

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View fila=convertView;
        if(fila==null){
            fila = inflater.inflate(R.layout.oferta_laboral, parent, false);
        }
        ViewHolder holder = (ViewHolder)fila.getTag();
        if(holder==null){
            holder=new ViewHolder(fila);
            fila.setTag(holder);
            fila.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(context, getItem(position).getDescripcion(), Toast.LENGTH_LONG).show();
                    return false;
                }
            });
        }

        Format formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        holder.categoria.setText(this.getItem(position).getCategoria().getDescripcion());
        holder.trabajo.setText(this.getItem(position).getDescripcion());
        holder.trabajo.setTypeface(null, BOLD);
        holder.horas.setText(String.format(Locale.getDefault(),"%1$d ",this.getItem(position).getHorasPresupuestadas()));
        holder.precioHora.setText(String.format(Locale.getDefault(),"%1$.2f ",this.getItem(position).getPrecioMaximoHora()));
        holder.fecha.setText(formatter.format(this.getItem(position).getFechaEntrega()));
        switch (this.getItem(position).getMonedaPago()){
            case 1:
                holder.bandera.setImageResource(R.drawable.us);
                holder.moneda.setText(R.string.monedaUS);
                break;
            case 2:
                holder.bandera.setImageResource(R.drawable.eu);
                holder.moneda.setText(R.string.monedaUE);
                break;
            case 3:
                holder.bandera.setImageResource(R.drawable.ar);
                holder.moneda.setText(R.string.monedaAR);
                break;
            case 4:
                holder.bandera.setImageResource(R.drawable.uk);
                holder.moneda.setText(R.string.monedaUK);
                break;
            case 5:
                holder.bandera.setImageResource(R.drawable.br);
                holder.moneda.setText(R.string.monedaBR);
                break;
        }
        if(this.getItem(position).getRequiereIngles()){
            holder.ingles.setChecked(true);
        }
        else{
            holder.ingles.setChecked(false);
        }

        return (fila);
    }

    class ViewHolder{
        TextView categoria = null;
        TextView trabajo = null;
        TextView horas = null;
        TextView precioHora = null;
        ImageView bandera = null;
        TextView fecha = null;
        TextView moneda = null;
        CheckBox ingles = null;

        ViewHolder(View base){
            this.categoria = (TextView)base.findViewById(R.id.tvCategoria);
            this.trabajo = (TextView)base.findViewById(R.id.tvTrabajo);
            this.horas = (TextView)base.findViewById(R.id.tvHoraDat);
            this.moneda = (TextView)base.findViewById(R.id.tvMoneda);
            this.precioHora = (TextView)base.findViewById(R.id.tvPrecioHoraDat);
            this.bandera = (ImageView)base.findViewById(R.id.ivBandera);
            this.fecha = (TextView)base.findViewById(R.id.tvFechaFinDat);
            this.ingles = (CheckBox)base.findViewById(R.id.cbIngles);
        }
    }
}
