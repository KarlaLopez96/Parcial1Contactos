package com.karla00058615.contactos;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karla on 21/4/2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>{
    Context context;
    ArrayList<Contactos> contactosList;
    View view;
    Activity activity;
    ComunicationFragment CF;
    int cont = 0;

    public ContactsAdapter(Context context, ArrayList<Contactos> contactosList, Activity activity) {
        this.context = context;
        this.view = view;
        this.contactosList = contactosList;
        this.activity = activity;
    }



    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.layout_list, parent, false);
        //se enlaza la interfaz
        CF = (ComunicationFragment) activity;

        return new ContactsViewHolder(v,context,contactosList);
    }

    @Override
    public void onBindViewHolder(final ContactsViewHolder holder, final int position) {
        holder.titleTxtView.setText(contactosList.get(position).getNombre());
        //al poner cada dato en la lista se pregunt si este es uno favorito
        //esto es necesario para setear la etrella marcada o no
        if(contactosList.get(position).getFav()){
            holder.fav.setImageDrawable(context.getResources().getDrawable(R.drawable.favo3));
        }else{
            holder.fav.setImageDrawable(context.getResources().getDrawable(R.drawable.favo2));
        }
        //listeenr de cada imgButon con forma de estrella
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //se pregunta si la etrella la que se le hace tab contiene un
                //contacto que es favorito o no y dependiendo marca estrella o no
                //ademas de enviar el contacto o quitarlos de la lista de favoritos
                if(!contactosList.get(position).getFav()){
                    contactosList.get(position).setFav(true);
                    CF.añadirFav(contactosList.get(position));
                    holder.fav.setImageDrawable(context.getResources().getDrawable(R.drawable.favo3));
                }else{
                    holder.fav.setImageDrawable(context.getResources().getDrawable(R.drawable.favo2));
                    contactosList.get(position).setFav(false);
                    CF.quitarFav(contactosList.get(position));
                }
            }});
        //holder.img.setImageDrawable(contactosList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return contactosList.size();
    }

    protected static class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView titleTxtView;
        ArrayList<Contactos> contactos = new ArrayList<>();
        Context ctx;
        ImageButton fav;

        public ContactsViewHolder(View itemView,Context ctx,ArrayList<Contactos> contactos) {
            super(itemView);
            this.contactos = contactos;
            this.ctx = ctx;
            itemView.setOnClickListener(this);
            //img = itemView.findViewById(R.id.peliculaImg);
            titleTxtView = itemView.findViewById(R.id.titleTxtView);
            fav = itemView.findViewById(R.id.button_fav);
        }

        //metodo para manejr el tab de un item de la lista
        //ademas de pasar ese item por un bundle y reemplazar el framento
        //por el de informacion
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Bundle bundle = new Bundle();
            bundle.putString("name",contactos.get(position).getNombre());
            bundle.putString("telefono",contactos.get(position).getTelefono());
            bundle.putString("email",contactos.get(position).getEmail());
            bundle.putString("id",contactos.get(position).getId());
            bundle.putString("direccion",contactos.get(position).getDirecion());
            bundle.putString("fecha",contactos.get(position).getFecha());

            //InfoFragment fragment = new InfoFragment();
            //Maneja los fragmentos.
            android.app.FragmentManager fragmentManager = ((MainActivity)view.getContext()).getFragmentManager();

            fragmentManager.executePendingTransactions();

            //Crea una nueva trasacción.
            android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

            //Crea un fragmento y lo añade.
            InfoFragment fragment = new InfoFragment();
            fragment.setArguments(bundle);

            if(ctx.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                transaction.replace(R.id.fragmentB, fragment);
            }else if (ctx.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                transaction.replace(R.id.fragmentC, fragment);
            }
            //Realizando cambios.
            transaction.commit();

            /*((FragmentActivity) view.getContext()).getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentC, fragment).commit();*/
        }
    }
}
