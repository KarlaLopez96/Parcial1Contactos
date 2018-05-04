package com.karla00058615.contactos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Beta.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Beta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Beta extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Beta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Beta.
     */
    // TODO: Rename and change types and number of parameters
    public static Beta newInstance(String param1, String param2) {
        Beta fragment = new Beta();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Bundle bundle;
    private EditText nombre,id,direccion,telefono,email,fecha;
    private Button done;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beta, container, false);

        nombre = (EditText) view.findViewById(R.id.edit_Name);
        direccion = (EditText)view.findViewById(R.id.edit_Direccion);
        email =(EditText) view.findViewById(R.id.edit_Email);
        fecha = (EditText)view.findViewById(R.id.edit_Fecha) ;
        telefono = (EditText)view.findViewById(R.id.edit_Telefono);
        done = (Button) view.findViewById(R.id.buttonAccept);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Bundle cubeta = new Bundle();
                cubeta.putString("id",id.getText().toString());
                cubeta.putString("nombre",nombre.getText().toString());
                cubeta.putString("direccion",direccion.getText().toString());
                cubeta.putString("email",email.getText().toString());
                cubeta.putString("telefono",telefono.getText().toString());
                cubeta.putString("fecha",fecha.getText().toString());

                //Maneja los fragmentos.
                android.app.FragmentManager fragmentManager = getActivity().getFragmentManager();

                fragmentManager.executePendingTransactions();

                //Crea una nueva trasacción.
                android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

                //Crea un fragmento y lo añade.
                ContactosFragment fragment = new ContactosFragment();
                fragment.setArguments(cubeta);

                transaction.replace(R.id.fragmentC, fragment);

                //Realizando cambios.
                transaction.commit();*/
            }
        });

        bundle = getArguments();

        nombre.setText(bundle.getString("nombre"));
        direccion.setText(bundle.getString("direccion"));
        email.setText(bundle.getString("email"));
        telefono.setText(bundle.getString("telefono"));
        fecha.setText(bundle.getString("fecha"));

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
