package com.karla00058615.peliculas;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,PeliculasFragment.OnFragmentInteractionListener,
FavoritosFragment.OnFragmentInteractionListener{

    Button pelis,fav;
    ArrayList<Contactos> peliculas = new ArrayList<>();
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pelis = (Button)findViewById(R.id.button_peliculas);
        fav = (Button) findViewById(R.id.button_favoritos);

        peliculas = fillList();

        pelis.setOnClickListener(this);

        //Maneja los fragmentos.
        android.app.FragmentManager fragmentManager = getFragmentManager();

        //Crea una nueva trasacción.
        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

        //Crea un fragmento y lo añade.
        PeliculasFragment fragment = new PeliculasFragment();

        //se crea el bundle y se mandan todas las peliculas
        Bundle bundle = new Bundle();
        for (int i = 0;i<peliculas.size();i++){
            bundle.putString("name"+cont,peliculas.get(i).getNombre());
            bundle.putString("description"+cont,peliculas.get(i).getEmail());
            bundle.putString("id"+cont,peliculas.get(i).getId());
            bundle.putBoolean("fav"+cont,peliculas.get(i).getFav());
            //bundle.putString("img"+cont,peliculas.get(i).getImg().toString());
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

        //se crea el bundle y se mandan todas las peliculas
        Bundle bundle = new Bundle();
        for (int i = 0;i<peliculas.size();i++){
            bundle.putString("name"+cont,peliculas.get(i).getNombre());
            bundle.putString("description"+cont,peliculas.get(i).getEmail());
            bundle.putString("id"+cont,peliculas.get(i).getId());
            bundle.putBoolean("fav"+cont,peliculas.get(i).getFav());
            //bundle.putString("img"+cont,peliculas.get(i).getImg().toString());
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
        PeliculasFragment fragment = new PeliculasFragment();

        //se crea el bundle y se mandan todas las peliculas
        Bundle bundle = new Bundle();
        for (int i = 0;i<peliculas.size();i++){
            bundle.putString("name"+cont,peliculas.get(i).getNombre());
            bundle.putString("description"+cont,peliculas.get(i).getEmail());
            bundle.putString("id"+cont,peliculas.get(i).getId());
            bundle.putBoolean("fav"+cont,peliculas.get(i).getFav());
            //bundle.putString("img"+cont,peliculas.get(i).getImg().toString());
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
        String id,nombre,telefono = "";
        ArrayList<Contactos> l = new ArrayList<>();

        Cursor phones = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            id = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));
            nombre=phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            telefono = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.NUMBER));
            //Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();
            l.add(new Contactos("1",nombre,telefono));
        }
        phones.close();
        //l.add(new Contactos(1, "Los Vengadores", desc,false/*getResources().getDrawable(R.drawable.avengers)*/));

        return l;
    }


//pasar aqui mi lista porque maneja mis dos fragmentos.

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
