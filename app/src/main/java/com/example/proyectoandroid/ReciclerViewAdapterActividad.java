package com.example.proyectoandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.models.Question;

import java.util.List;

public class ReciclerViewAdapterActividad extends RecyclerView.Adapter<ReciclerViewAdapterActividad.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView votos,titulo,tipo;

        public ViewHolder(View itemView){
            super(itemView);
            votos = itemView.findViewById(R.id.item_actividad_votos);
            titulo = itemView.findViewById(R.id.item_actividad_titulo);
            tipo = itemView.findViewById(R.id.item_actividad_tipodeactividad);
        }
    }

    public List<Question> preguntas;

    public ReciclerViewAdapterActividad(List<Question> preguntas){
        this.preguntas = preguntas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actividad,parent,false);
        ViewHolder holder  = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        holder.titulo.setText(preguntas.get(position).getTitle());
        holder.votos.setText(preguntas.get(position).getVotes()+" votos");

        if (preguntas.get(position).getType().equals("question"))
            holder.tipo.setText("P");
    }

    @Override
    public int getItemCount(){
        return preguntas.size();
    }
}
