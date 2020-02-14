package com.example.proyectoandroid;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.models.Tag;

import java.util.List;

public class ReciclerViewAdapterTag extends RecyclerView.Adapter<ReciclerViewAdapterTag.ViewHolder> implements View.OnTouchListener{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView descripcion,titulo,cantidad;
        private ScrollView scroll;

        public ViewHolder(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.tituloetiqueta);
            descripcion = itemView.findViewById(R.id.descripcionetiqueta);
            cantidad = itemView.findViewById(R.id.cantpreguntasetiqueta);
            scroll = itemView.findViewById(R.id.scrollprueba);
        }
    }

    public List<Tag> etiquetas;

    public ReciclerViewAdapterTag(List<Tag> etiquetas){
        this.etiquetas = etiquetas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_etiquetas,parent,false);
        ViewHolder holder  = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        holder.titulo.setText(etiquetas.get(position).getTitulo());
        holder.descripcion.setText(etiquetas.get(position).getDescripcion());
        holder.cantidad.setText(etiquetas.get(position).getCantidad()+" respuestas");

        holder.scroll.setOnTouchListener(this);
    }

    @Override
    public int getItemCount(){
        return etiquetas.size();
    }


    public boolean onTouch(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }
}
