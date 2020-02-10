package com.example.proyectoandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.models.Tag;

import java.util.List;

public class ReciclerViewAdapterTagSelector extends RecyclerView.Adapter<ReciclerViewAdapterTagSelector.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        Button borrar;

        public ViewHolder(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.item_tag_selector);
            borrar = itemView.findViewById(R.id.delete_tag_selector);
        }
    }

    public List<Tag> etiquetas;

    public ReciclerViewAdapterTagSelector(List<Tag> etiquetas){
        this.etiquetas = etiquetas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_selector,parent,false);
        ViewHolder holder  = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        holder.titulo.setText(etiquetas.get(position).getTitulo());

    }
    @Override
    public int getItemCount(){
        return etiquetas.size();
    }



}
