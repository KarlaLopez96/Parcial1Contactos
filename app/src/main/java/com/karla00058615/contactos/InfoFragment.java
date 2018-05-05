package com.karla00058615.contactos;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class InfoFragment extends Fragment{

    private final int MY_PERMISSIONS_REQUEST_CALL = 123;
    private OnFragmentInteractionListener mListener;
    private TextView nombre,id,direccion,telefono,email,fecha;
    private Button buttonShare,buttonCall,buttonEdit;
    Bundle bundle;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        nombre = (TextView) view.findViewById(R.id.name);
        id = (TextView)view.findViewById(R.id.id);
        direccion = (TextView)view.findViewById(R.id.direccion);
        email =(TextView) view.findViewById(R.id.email);
        fecha = (TextView)view.findViewById(R.id.fecha) ;
        telefono = (TextView)view.findViewById(R.id.telefono);
        buttonShare = (Button)view.findViewById(R.id.buttonShare);
        buttonCall = (Button)view.findViewById(R.id.buttonCall);
        buttonEdit = (Button)view.findViewById(R.id.buttonEdit);
        buttonShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try{
                    final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(Intent.EXTRA_TEXT,
                            "Nombre contacto: "+bundle.getString("name")+"\n"+
                            "Dirección: "+bundle.getString("direccion") +"\n"+
                            "Email: "+bundle.getString("email") + "\n"+
                            "Teléfono: "+bundle.getString("telefono")+ "\n"+
                            "Fecha: "+bundle.getString("fecha"));
                    intent.setType("*/*");
                    startActivity(Intent.createChooser(intent, "Sharing contact"));
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle cubeta = new Bundle();
                cubeta.putString("id",bundle.getString("id"));
                cubeta.putString("nombre",bundle.getString("name"));
                cubeta.putString("direccion",bundle.getString("direccion"));
                cubeta.putString("email",bundle.getString("email"));
                cubeta.putString("telefono",bundle.getString("telefono"));
                cubeta.putString("fecha",bundle.getString("fecha"));

            //mListener.onFragmentInteraction();

                //Maneja los fragmentos.
                FragmentManager fragmentManager = getActivity().getFragmentManager() ;

                fragmentManager.executePendingTransactions();

                //Crea una nueva trasacción.
                android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

                //Crea un fragmento y lo añade.
                EditFragment fragment = new EditFragment();
                fragment.setArguments(cubeta);

                transaction.replace(R.id.fragmentC, fragment);

                //Realizando cambios.
                transaction.commit();
            }
        });

        bundle = getArguments();

        nombre.setText(bundle.getString("name"));
        id.setText(bundle.getString("id"));
        direccion.setText("Dirección: "+bundle.getString("direccion"));
        email.setText("Email: "+bundle.getString("email"));
        telefono.setText("Móvil: "+bundle.getString("telefono"));
        fecha.setText("Fecha: "+bundle.getString("fecha"));

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(getContext(),
                            Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL);

                    } else {
                        Call();
                    }
                }
            }
        });

        return view;
    }

    public void Call(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+bundle.getString("telefono")));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Call();
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
