package com.pharmacy.martin.lab03c2016;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Martin on 08/09/2016.
 */
public class Adaptador extends BaseAdapter {
    /*Inflater*/
    LayoutInflater inflater;
    Adaptador(Context context ,List<Trabajo> items) {
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View fila=convertView;
        if(fila==null){
            fila = inflater.inflate(R.layout.oferta_laboral, parent, false);
        }
        ViewHolder holder = (ViewHolder)fila.getTag();
        if(fila==null){
            holder=new ViewHolder(fila);
            fila.setTag(holder);
        }
        //TODO: Hacer el getItem
        // holder.categoria.setText(this.getItem(position).get);
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

        ViewHolder(View base){
            this.categoria = (TextView)base.findViewById(R.id.tvCategoria);
            this.trabajo = (TextView)base.findViewById(R.id.tvTrabajo);
            this.horas = (TextView)base.findViewById(R.id.tvHoraDat);
            this.moneda = (TextView)base.findViewById(R.id.tvMoneda);
            this.precioHora = (TextView)base.findViewById(R.id.tvPrecioHoraDat);
            this.bandera = (ImageView)base.findViewById(R.id.ivBandera);
            this.fecha = (TextView)base.findViewById(R.id.tvFechaFinDat);
        }
    }
}
