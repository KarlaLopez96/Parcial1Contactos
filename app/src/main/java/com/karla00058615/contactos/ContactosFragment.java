package com.karla00058615.contactos;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ContactosFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ContactosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        ArrayList<Contactos> contactosList = new ArrayList<>();
        RecyclerView recyclerView;
        ContactsAdapter adapter= null;

            //filling the planet list
            contactosList = fillList();

            //setting the recyclerview
            recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            linearLayoutManager = new LinearLayoutManager(getContext());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            adapter = new ContactsAdapter(getContext(), contactosList,getActivity());
        }

        //recyclerview
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private ArrayList<Contactos> fillList(){
        int cont = 0;
        Bundle bundle = getArguments();
        ArrayList<Contactos> l = new ArrayList<>();

        for (int i = 0;i < (bundle.size())/7;i++){
            l.add(new Contactos(bundle.getString("id"+cont),bundle.getString("name"+cont)
                    ,bundle.getString("email"+cont),bundle.getBoolean("fav"+cont)
                    ,bundle.getString("telefono"+cont),bundle.getString("direccion"+cont),
                    bundle.getString("fecha"+cont)));
            cont++;
        }

        return l;
    }


    /**
     *  public Contactos(String id, String nombre, String email, boolean fav, String direcion, String fecha) {
     this.id = id;
     this.nombre = nombre;
     this.email = email;
     this.fav = fav;
     this.direcion =direcion;
     this.fecha = fecha;
     }
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
