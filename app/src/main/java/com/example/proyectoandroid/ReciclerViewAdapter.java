package com.example.proyectoandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.models.Question;

import java.util.List;

public class ReciclerViewAdapter extends RecyclerView.Adapter<ReciclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titulo,descripcion,usuario,visitas,votos,respuestas;

        public ViewHolder(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            visitas = itemView.findViewById(R.id.visitas);
            votos = itemView.findViewById(R.id.votos);
            respuestas = itemView.findViewById(R.id.respuestas);
        }
    }

    public List<Question> preguntas;

    public ReciclerViewAdapter(List<Question> preguntas){
        this.preguntas = preguntas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quest,parent,false);
        ViewHolder holder  = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        holder.titulo.setText(preguntas.get(position).getTitulo());
        holder.votos.setText(preguntas.get(position).getVotos()+" votos");
        holder.respuestas.setText(""+preguntas.get(position).getRespuestas()+" respuestas");
        holder.visitas.setText(""+preguntas.get(position).getVisitas()+" vistas");

    }

    @Override
    public int getItemCount(){
        return preguntas.size();
    }
}
