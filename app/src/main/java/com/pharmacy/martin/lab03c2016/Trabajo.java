package com.pharmacy.martin.lab03c2016;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * Created by mdominguez on 07/09/16.
 */
public class Trabajo implements Serializable{

    private Integer id;
    private String descripcion;
    private Integer horasPresupuestadas;
    private Categoria categoria;
    private Double precioMaximoHora;
    private Date fechaEntrega;
    private Integer monedaPago; //1 US$ 2Euro 3 AR$- 4 Libra 5 R$
    private Boolean requiereIngles;

    public Trabajo(){
        Random r =new Random();
        this.monedaPago=1+r.nextInt(4);
        this.requiereIngles=r.nextInt()%2==0;
        Integer dias = (7+r.nextInt(35));
        long ts =(long) (System.currentTimeMillis()+dias*1000*60*60*24);
        this.fechaEntrega = new Date(ts);
        this.precioMaximoHora=r.nextDouble()*(10+r.nextInt(100));
        this.horasPresupuestadas = dias/ 4+r.nextInt(6);
        this.categoria= Categoria.CATEGORIAS_MOCK[r.nextInt(5)];
    }

    public Trabajo(Integer id,String desc){
        this();
        this.id = id;
        this.descripcion = desc;
    }

    public Trabajo(Integer id,String desc,Categoria cat){
        this();
        this.id = id;
        this.descripcion = desc;
        this.categoria = cat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getHorasPresupuestadas() {
        return horasPresupuestadas;
    }

    public void setHorasPresupuestadas(Integer horasPresupuestadas) {
        this.horasPresupuestadas = horasPresupuestadas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPrecioMaximoHora() {
        return precioMaximoHora;
    }

    public void setPrecioMaximoHora(Double precioMaximoHora) {
        this.precioMaximoHora = precioMaximoHora;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getMonedaPago() {
        return monedaPago;
    }

    public void setMonedaPago(Integer monedaPago) {
        this.monedaPago = monedaPago;
    }

    public Boolean getRequiereIngles() {
        return requiereIngles;
    }

    public void setRequiereIngles(Boolean requiereIngles) {
        this.requiereIngles = requiereIngles;
    }

    public static final Trabajo[] TRABAJOS_MOCK= new Trabajo[]{
            new Trabajo(1,"Proyecto ABc"),
            new Trabajo(2,"Sistema de Gestion"),
            new Trabajo(3, "Aplicacion XYZ"),
            new Trabajo(4,"Modulo NN1"),
            new Trabajo(5,"Sistema de adminisracion TDF"),
            new Trabajo(6,"Aplicacion OYU 66"),
            new Trabajo(7,"Gestion de laboratorios"),
            new Trabajo(8,"Sistema Universidades"),
            new Trabajo(9,"Portal Compras"),
            new Trabajo(10,"Gestion RRHH"),
            new Trabajo(11,"Traductor Automatico"),
            new Trabajo(12,"Alquileres online"),
            new Trabajo(13,"Gestion financiera"),
            new Trabajo(14,"Modulo Seguridad"),
            new Trabajo(15,"consultoria performance"),
            new Trabajo(16,"Ecommerce Uah"),
            new Trabajo(17,"Portal Web Htz"),
            new Trabajo(18,"Sitio Coroporativo"),
            new Trabajo(19,"Aplicacion www1")
    };
}
