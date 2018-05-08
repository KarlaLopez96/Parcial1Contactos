package com.karla00058615.contactos;

import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by Karla on 4/5/2018.
 */
//clase interfaz que comunica fragmentos con la actvity
public interface ComunicationFragment {
    public void añadirFav(Contactos contactos);

    public void quitarFav(Contactos contactos);

    public void enviarContactoEditado(String id,String nombre,String direccion,String email,String telefono,String fecha);

    public void enviarContactoNuevo(String nombre,String direccion,String email,String telefono,String fecha);

    public void quitarFrameB();
}
