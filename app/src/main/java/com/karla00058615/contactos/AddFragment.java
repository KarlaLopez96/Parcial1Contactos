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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AddFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    private OnFragmentInteractionListener mListener;

    public AddFragment() {
        // Required empty public constructor
    }

    EditText nombre,direccion,email,telefono,fecha;
    Button add;
    int day,month,year;
    ComunicationFragment CF;
    Calendar myCalendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //se enlazan las variables con los campos del activity
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        nombre = view.findViewById(R.id.add_Name);
        direccion= view.findViewById(R.id.add_Direccion);
        email = view.findViewById(R.id.add_Email);
        telefono = view.findViewById(R.id.add_Telefono);
        fecha = view.findViewById(R.id.add_Fecha);
        add = view.findViewById(R.id.buttonAdd);
        //listener que envia por medio de la interfaz el contacto a agregar
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CF.enviarContactoNuevo(nombre.getText().toString(),
                        direccion.getText().toString(),email.getText().toString(),
                        telefono.getText().toString(),fecha.getText().toString());
            }
        });
        //se sete la fecha actual en el campo de fecha
        myCalendar = Calendar.getInstance();
        day = myCalendar.get(Calendar.DAY_OF_MONTH);
        month = myCalendar.get(Calendar.MONTH);
        year = myCalendar.get(Calendar.YEAR);

        month++;

        fecha.setText(day+"/"+month+"/"+year);
        //////////////////////////////////////////////////////////////
        //listener que manda a llamar al dialog fragment que muestra el date picker
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
        //se vincula la inerfaz
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

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

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

    //framente dialog que esta configurado para mostrar y setear la fecha
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
