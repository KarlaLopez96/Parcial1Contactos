package com.karla00058615.contactos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karla00058615.contactos.ContactosFragment;
import com.karla00058615.contactos.FavoritosFragment;
import com.karla00058615.contactos.R;
import com.karla00058615.contactos.Contactos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ContactosFragment.OnFragmentInteractionListener,
FavoritosFragment.OnFragmentInteractionListener{
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 123;
    Button contacts,fav;
    ArrayList<Contactos> contactos = new ArrayList<>();
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Peticion de permisos

        // Should we show an explanation?
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

        } else {
            SecondMain();
        }

        /***************************************************************************************/
    }

    public void SecondMain(){
        contacts = (Button)findViewById(R.id.button_peliculas);
        fav = (Button) findViewById(R.id.button_favoritos);

        contactos = fillList();

        contacts.setOnClickListener(this);

        //Maneja los fragmentos.
        android.app.FragmentManager fragmentManager = getFragmentManager();

        //Crea una nueva trasacción.
        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

        //Crea un fragmento y lo añade.
        ContactosFragment fragment = new ContactosFragment();

        //se crea el bundle y se mandan todas las contactos
        Bundle bundle = new Bundle();
        for (int i = 0; i< contactos.size(); i++){
            bundle.putString("name"+cont, contactos.get(i).getNombre());
            bundle.putString("email"+cont, contactos.get(i).getEmail());
            bundle.putString("id"+cont, contactos.get(i).getId());
            bundle.putBoolean("fav"+cont, contactos.get(i).getFav());
            bundle.putString("telefono"+cont, contactos.get(i).getTelefono());
            cont++;
        }
        cont = 0;
        //se manda el bundle al fragment
        fragment.setArguments(bundle);

        transaction.add(R.id.fragmentC, fragment);

        //Realizando cambios.
        transaction.commit();
    }

    public void favoritos(View view){

        //Maneja los fragmentos.
        android.app.FragmentManager fragmentManager = getFragmentManager();

        //Crea una nueva trasacción.
        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

        //Crea un fragmento y lo añade.
        FavoritosFragment fragment = new FavoritosFragment();

        //se crea el bundle y se mandan todas las contactos
        Bundle bundle = new Bundle();
        for (int i = 0; i< contactos.size(); i++){
            bundle.putString("name"+cont, contactos.get(i).getNombre());
            bundle.putString("email"+cont, contactos.get(i).getEmail());
            bundle.putString("id"+cont, contactos.get(i).getId());
            bundle.putBoolean("fav"+cont, contactos.get(i).getFav());
            bundle.putString("telefono"+cont, contactos.get(i).getTelefono());
            cont++;
        }
        cont = 0;
        //se manda el bundle al fragment
        fragment.setArguments(bundle);

        transaction.add(R.id.fragmentC, fragment);

        //Realizando cambios.
        transaction.commit();
    }

    @Override
    public void onClick(View view) {

        //Maneja los fragmentos.
        android.app.FragmentManager fragmentManager = getFragmentManager();

        //Crea una nueva trasacción.
        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

        //Crea un fragmento y lo añade.
        ContactosFragment fragment = new ContactosFragment();

        //se crea el bundle y se mandan todas las contactos
        Bundle bundle = new Bundle();
        for (int i = 0; i< contactos.size(); i++){
            bundle.putString("name"+cont, contactos.get(i).getNombre());
            bundle.putString("email"+cont, contactos.get(i).getEmail());
            bundle.putString("id"+cont, contactos.get(i).getId());
            bundle.putBoolean("fav"+cont, contactos.get(i).getFav());
            bundle.putString("telefono"+cont, contactos.get(i).getTelefono());
            cont++;
        }
        cont = 0;
        //se manda el bundle al fragment
        fragment.setArguments(bundle);

        transaction.add(R.id.fragmentC, fragment);

        //Realizando cambios.
        transaction.commit();
    }

    private ArrayList<Contactos> fillList(){
        String id,nombre,email="vacio",telefonos = "vacio";
        ArrayList<Contactos> l = new ArrayList<>();

        Cursor phones = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            id = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));
            nombre=phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            Cursor PhoneC = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?",new String[]{id},null);

            while (PhoneC.moveToNext()){
                telefonos = PhoneC.getString(PhoneC.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                break;
            }

            Cursor PhoneE = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + "= ?",new String[]{id},null);

            while (PhoneE.moveToNext()){
                email = PhoneE.getString(PhoneE.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                break;
            }
            l.add(new Contactos(id,nombre,telefonos,email));
        }
        phones.close();
        //l.add(new Contactos(1, "Los Vengadores", desc,false/*getResources().getDrawable(R.drawable.avengers)*/));

        return l;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SecondMain();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


//pasar aqui mi lista porque maneja mis dos fragmentos.

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
