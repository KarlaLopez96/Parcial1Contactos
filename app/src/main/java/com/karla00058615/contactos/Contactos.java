package com.karla00058615.contactos;

/**
 * Created by Karla on 21/4/2018.
 */

public class Contactos {

    private String id;
    private  boolean fav;
    private String nombre, email,telefono,direcion,fecha;

    public Contactos(String id, String nombre, String email, boolean fav,String telefono, String direcion, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fav = fav;
        this.direcion =direcion;
        this.fecha = fecha;
        this.telefono = telefono;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isFav() {
        return fav;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public boolean getFav() {
        return fav;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
