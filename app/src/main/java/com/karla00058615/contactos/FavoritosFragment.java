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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavoritosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FavoritosFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        ArrayList<Contactos> contactosList;
        RecyclerView recyclerView;
        ContactsAdapter adapter = null;

        //filling the planet list
        contactosList = fillList();

        //setting the recyclerview
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
        int holder;
        Bundle bundle = getArguments();
        ArrayList<Contactos> l = new ArrayList<>();

        for (int i = 0;i < (bundle.size())/7;i++){
            if(bundle.getBoolean("fav"+cont)){
                l.add(new Contactos(bundle.getString("id"+cont),bundle.getString("name"+cont)
                        ,bundle.getString("enmail"+cont),bundle.getBoolean("fav"+cont)
                        ,bundle.getString("telefono"+cont),bundle.getString("direccion"+cont),
                        bundle.getString("fecha"+cont)));
            }
            cont++;
        }

        return l;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
