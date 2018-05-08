package com.karla00058615.contactos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karla on 21/4/2018.
 */

//objeto contacos que tiene la descripcion basica de un contacto
public class Contactos implements Parcelable{

    private String id;
    //booleano que se usa para determinar si es un favorito o no
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

    protected Contactos(Parcel in) {
        id = in.readString();
        fav = in.readByte() != 1;
        nombre = in.readString();
        email = in.readString();
        telefono = in.readString();
        direcion = in.readString();
        fecha = in.readString();
    }

    public static final Creator<Contactos> CREATOR = new Creator<Contactos>() {
        @Override
        public Contactos createFromParcel(Parcel in) {
            return new Contactos(in);
        }

        @Override
        public Contactos[] newArray(int size) {
            return new Contactos[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(email);
        parcel.writeString(telefono);
        parcel.writeString(direcion);
        parcel.writeString(fecha);
        parcel.writeByte((byte)(fav ? 1:0));
    }
}
