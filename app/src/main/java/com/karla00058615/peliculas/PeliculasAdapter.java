package com.karla00058615.peliculas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Karla on 21/4/2018.
 */

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.PeliculasViewHolder>{
    Context context;
    List<Contactos> contactosList;
    int cont = 0;

    public PeliculasAdapter(Context context, List<Contactos> contactosList) {
        this.context = context;
        this.contactosList = contactosList;
    }

    @Override
    public PeliculasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.layout_list, parent, false);

        return new PeliculasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PeliculasViewHolder holder, final int position) {
        holder.titleTxtView.setText(contactosList.get(position).getNombre());
        holder.descrTxtView.setText(contactosList.get(position).getEmail());
        //holder.img.setImageDrawable(contactosList.get(position).getImg());
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactosList.get(position).setFav(!contactosList.get(position).getFav());
                /*Bundle bundle = new Bundle();
                bundle.putString("name"+cont,contactosList.get(position).getNombre());
                bundle.putString("description"+cont,contactosList.get(position).getEmail());
                bundle.putInt("id"+cont,contactosList.get(position).getId());

                FavoritosFragment fragment = new FavoritosFragment();
                fragment.setArguments(bundle);
                cont++;*/
                Toast.makeText(context,"item: "+position + ", fav: " + contactosList.get(position).getFav(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactosList.size();
    }

    protected class PeliculasViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView titleTxtView, descrTxtView;
        Button fav;

        public PeliculasViewHolder(View itemView) {
            super(itemView);

            //img = itemView.findViewById(R.id.peliculaImg);
            titleTxtView = itemView.findViewById(R.id.titleTxtView);
            descrTxtView = itemView.findViewById(R.id.descTxtView);
            fav = itemView.findViewById(R.id.button_fav);
        }
    }
}
