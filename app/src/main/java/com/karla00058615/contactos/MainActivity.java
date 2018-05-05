package com.karla00058615.contactos;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ContactosFragment.OnFragmentInteractionListener,
FavoritosFragment.OnFragmentInteractionListener, InfoFragment.OnFragmentInteractionListener, EditFragment.OnFragmentInteractionListener,
        ComunicationFragment,AddFragment.OnFragmentInteractionListener{
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 123;
    Button contacts,fav;
    ArrayList<Contactos> contactos = new ArrayList<>();
    ArrayList<Contactos> favoritos = new ArrayList<>();
    int cont = 0;
    int id = 0;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
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
            bundle.putString("direccion"+cont, contactos.get(i).getDirecion());
            bundle.putString("fecha"+cont, contactos.get(i).getFecha());
            cont++;
        }
        cont = 0;
        //se manda el bundle al fragment
        fragment.setArguments(bundle);

        transaction.add(R.id.fragmentC, fragment);

        SearchView searchView = findViewById(R.id.search);

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
        for (int i = 0; i< favoritos.size(); i++){
            bundle.putString("name"+cont, favoritos.get(i).getNombre());
            bundle.putString("email"+cont, favoritos.get(i).getEmail());
            bundle.putString("id"+cont, favoritos.get(i).getId());
            bundle.putBoolean("fav"+cont, favoritos.get(i).getFav());
            bundle.putString("telefono"+cont, favoritos.get(i).getTelefono());
            bundle.putString("direccion"+cont, favoritos.get(i).getDirecion());
            bundle.putString("fecha"+cont, favoritos.get(i).getFecha());
            cont++;
        }
        cont = 0;
        //se manda el bundle al fragment
        fragment.setArguments(bundle);

        transaction.replace(R.id.fragmentC, fragment);

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
            bundle.putString("direccion"+cont, contactos.get(i).getDirecion());
            bundle.putString("fecha"+cont, contactos.get(i).getFecha());
            cont++;
        }
        cont = 0;
        //se manda el bundle al fragment
        fragment.setArguments(bundle);

        transaction.replace(R.id.fragmentC, fragment);

        //Realizando cambios.
        transaction.commit();
    }

    private ArrayList<Contactos> fillList(){
        String id,nombre,email=" ",telefonos = " ";
        String direccion = " ",fecha = " ";
        ArrayList<Contactos> l = new ArrayList<>();


        Cursor phones = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            id = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));
            nombre=phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            String columns[] = {
                    ContactsContract.CommonDataKinds.Event.START_DATE,
                    ContactsContract.CommonDataKinds.Event.TYPE,
                    ContactsContract.CommonDataKinds.Event.MIMETYPE,
            };

            String where = ContactsContract.CommonDataKinds.Event.TYPE + "=" +
                    ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY +
                    " and " + ContactsContract.CommonDataKinds.Event.MIMETYPE + " = '" +
                    ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE + "' and "
                    + ContactsContract.Data.CONTACT_ID +  " = "+ id;

            String sortOrder = ContactsContract.Contacts.DISPLAY_NAME;

            Uri postal_uri = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;
            Cursor postal_cursor  = getContentResolver().query(postal_uri,null,
                    ContactsContract.Data.CONTACT_ID + "= ?",new String[]{id}, null,null);
            while(postal_cursor.moveToNext())
            {
                String Strt = postal_cursor.getString(postal_cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                String Cty = postal_cursor.getString(postal_cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                String cntry = postal_cursor.getString(postal_cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                postal_cursor.close();
                direccion = Strt + " " + Cty + " " + cntry;
                break;
            }
            postal_cursor.close();

            Cursor PhoneC = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?",new String[]{id},null);

            while (PhoneC.moveToNext()){
                telefonos = PhoneC.getString(PhoneC.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                PhoneC.close();
                break;
            }

            Cursor PhoneE = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + "= ?",new String[]{id},null);

            while (PhoneE.moveToNext()){
                email = PhoneE.getString(PhoneE.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                PhoneE.close();
                break;
            }
            Cursor birthdayCur = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    columns, where, null, sortOrder);
            while (birthdayCur.moveToNext()) {
                fecha = birthdayCur.getString(birthdayCur.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE));
                break;
            }
            birthdayCur.close();
            if(email.equals("null")){
                email = "";
            }
            l.add(new Contactos(id,nombre,email,false,telefonos,direccion,fecha));
            id = " ";
            nombre = "";
            email = " ";
            telefonos = " ";
            fecha = " ";
            direccion = " ";

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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //SearchManager searchManager = (SearchManager) getSystemService(this.SEARCH_SERVICE);
        //SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false; //do the default
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //NOTE: doing anything here is optional, onNewIntent is the important bit
                if (s.length() > 0) {
                    ArrayList<Contactos> contactosF = new ArrayList<>();
                    for (int i = 0; i<contactos.size();i++){
                        if(contactos.get(i).getNombre().toLowerCase().startsWith(s.toLowerCase())){
                            contactosF.add(contactos.get(i));
                        }
                    }
                    //Maneja los fragmentos.
                    android.app.FragmentManager fragmentManager = getFragmentManager();

                    //Crea una nueva trasacción.
                    android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

                    //Crea un fragmento y lo añade.
                    ContactosFragment fragment = new ContactosFragment();

                    //se crea el bundle y se mandan todas las contactos
                    Bundle bundle = new Bundle();
                    for (int i = 0; i< contactosF.size(); i++){
                        bundle.putString("name"+cont, contactosF.get(i).getNombre());
                        bundle.putString("email"+cont, contactosF.get(i).getEmail());
                        bundle.putString("id"+cont, contactosF.get(i).getId());
                        bundle.putBoolean("fav"+cont, contactosF.get(i).getFav());
                        bundle.putString("telefono"+cont, contactosF.get(i).getTelefono());
                        bundle.putString("direccion"+cont, contactosF.get(i).getDirecion());
                        bundle.putString("fecha"+cont, contactosF.get(i).getFecha());
                        cont++;
                    }
                    cont = 0;
                    //se manda el bundle al fragment
                    fragment.setArguments(bundle);

                    transaction.replace(R.id.fragmentC, fragment);

                    //Realizando cambios.
                    transaction.commit();
                } else if (s.length() == 0) {
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
                        bundle.putString("direccion"+cont, contactos.get(i).getDirecion());
                        bundle.putString("fecha"+cont, contactos.get(i).getFecha());
                        cont++;
                    }
                    cont = 0;
                    //se manda el bundle al fragment
                    fragment.setArguments(bundle);

                    transaction.replace(R.id.fragmentC, fragment);

                    //Realizando cambios.
                    transaction.commit();
                }
                return false;
            }

        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                //Maneja los fragmentos.
                android.app.FragmentManager fragmentManager = getFragmentManager();

                //Crea una nueva trasacción.
                android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

                //Crea un fragmento y lo añade.
                AddFragment fragment = new AddFragment();

                transaction.replace(R.id.fragmentC, fragment);

                //Realizando cambios.
                transaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final String query = intent.getStringExtra(SearchManager.QUERY);
            //TODO: actually do some filtering / set results
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void añadirFav(Contactos contacto) {
        boolean flag = false;
        for (int i = 0 ; i < favoritos.size();i++){
            if(favoritos.get(i).equals(contacto.getId())){
                flag = true;
            }
        }
        if(!flag){
            favoritos.add(contacto);
            for(int i = 0;i<contactos.size();i++){
                if(contactos.get(i).getId().equals(contacto.getId())){
                    contactos.get(i).setFav(true);
                }
            }
        }
    }

    @Override
    public void quitarFav(Contactos contacto) {
        for (int i = 0;i<favoritos.size();i++){
            if(favoritos.get(i).getId().equals(contacto.getId())){
                favoritos.remove(i);
            }
        }
        for(int i = 0;i<contactos.size();i++){
            if(contactos.get(i).getId().equals(contacto.getId())){
                contactos.get(i).setFav(false);
            }
        }
    }

    @Override
    public void enviarContactoEditado(String id,String nombre, String direccion, String email, String telefono, String fecha) {
        for(int i = 0;i<contactos.size();i++){
            if(contactos.get(i).getId().equals(id)){
                contactos.get(i).setNombre(nombre);
                contactos.get(i).setDirecion(direccion);
                contactos.get(i).setEmail(email);
                contactos.get(i).setTelefono(telefono);
                contactos.get(i).setFecha(fecha);
            }
        }
        for (int i = 0;i<favoritos.size();i++){
            if(favoritos.get(i).getId().equals(id)){
                favoritos.get(i).setNombre(nombre);
                favoritos.get(i).setDirecion(direccion);
                favoritos.get(i).setEmail(email);
                favoritos.get(i).setTelefono(telefono);
                favoritos.get(i).setFecha(fecha);
            }
        }
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
            bundle.putString("direccion"+cont, contactos.get(i).getDirecion());
            bundle.putString("fecha"+cont, contactos.get(i).getFecha());
            cont++;
        }
        cont = 0;
        //se manda el bundle al fragment
        fragment.setArguments(bundle);

        transaction.replace(R.id.fragmentC, fragment);

        //Realizando cambios.
        transaction.commit();
    }

    @Override
    public void enviarContactoNuevo(String nombre, String direccion, String email, String telefono, String fecha) {

        contactos.add(new Contactos(String.valueOf(id),nombre,email,false,telefono,direccion,fecha));

        id++;

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
            bundle.putString("direccion"+cont, contactos.get(i).getDirecion());
            bundle.putString("fecha"+cont, contactos.get(i).getFecha());
            cont++;
        }
        cont = 0;
        //se manda el bundle al fragment
        fragment.setArguments(bundle);

        transaction.replace(R.id.fragmentC, fragment);

        //Realizando cambios.
        transaction.commit();

        //String id, String nombre, String email, boolean fav,String telefono, String direcion, String fecha
    }
}
