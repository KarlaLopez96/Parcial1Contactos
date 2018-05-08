package com.karla00058615.contactos;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
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

    ComunicationFragment CF;
    private Bundle bundle;
    private EditText nombre,direccion,telefono,email,fecha;
    private Button done;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        nombre = (EditText) view.findViewById(R.id.edit_Name);
        direccion = (EditText)view.findViewById(R.id.edit_Direccion);
        email =(EditText) view.findViewById(R.id.edit_Email);
        fecha = (EditText)view.findViewById(R.id.edit_Fecha) ;
        telefono = (EditText)view.findViewById(R.id.edit_Telefono);
        done = (Button) view.findViewById(R.id.buttonAccept);

        //listener del boton que representa que el usuario termino de editar a su contacto
        //y mandarlo al main por medio de la interfaz
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CF.enviarContactoEditado(bundle.getString("id"),nombre.getText().toString(),
                        direccion.getText().toString(),email.getText().toString(),
                        telefono.getText().toString(),fecha.getText().toString());
            }
        });

        //obtencion de datos por medio del bundle
        bundle = getArguments();
        //se insertan los datos en los editText para que el usuario pueda cambiarlos
        if(!bundle.getString("nombre").equals("")){
            nombre.setText(bundle.getString("nombre"));
        }
        if(!bundle.getString("direccion").equals("")){
            direccion.setText(bundle.getString("direccion"));
        }
        if(!bundle.getString("email").equals("")){
            email.setText(bundle.getString("email"));
        }
        if(!bundle.getString("telefono").equals("")){
            telefono.setText(bundle.getString("telefono"));
        }
        if(!bundle.getString("fecha").equals("")){
            fecha.setText(bundle.getString("fecha"));
        }

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

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
        //vincla la interfaz
        CF = (ComunicationFragment) getActivity();
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

    @SuppressLint("ValidFragment")
    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //al crear el dialog de la fecha esta se pone en la fecha actual
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            //se encarga de cambiar el dalog con forme el usuario prefiera
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
            //se setea la fecha seleccionada en el campo de fecha
            fecha.setText(month+"/"+day+"/"+year);
        }

    }
}
