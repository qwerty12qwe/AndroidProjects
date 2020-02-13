package com.example.proyectoandroid;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.models.Tag;

import java.util.ArrayList;
import java.util.List;

public class ReciclerViewAdapterTagSelector extends RecyclerView.Adapter<ReciclerViewAdapterTagSelector.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox titulo;

        public ViewHolder(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.item_tag_selector);
        }
    }

    public List<Tag> etiquetas;
    public List<Integer> idsseleccionados;

    public ReciclerViewAdapterTagSelector(List<Tag> etiquetas){
        this.etiquetas = etiquetas;
        idsseleccionados = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_selector,parent,false);
        ViewHolder holder  = new ViewHolder(view);
        return holder;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        holder.titulo.setText(etiquetas.get(position).getTitulo());
        holder.titulo.setOnClickListener(v -> {
            if (holder.titulo.isChecked())
                idsseleccionados.add(etiquetas.get(position).getId());
            else
                idsseleccionados.removeIf(x -> x==etiquetas.get(position).getId());

        });
    }
    @Override
    public int getItemCount(){
        return etiquetas.size();
    }

}