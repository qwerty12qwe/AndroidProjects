package com.example.proyectoandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.models.QuestionTags;
import com.example.proyectoandroid.models.Tag;
import com.example.proyectoandroid.ui.home.HomeFragment;
import com.example.proyectoandroid.ui.pregunta_respuestas.PreguntaRespuestas;

import java.util.List;

public class ReciclerViewAdapterQuestion extends RecyclerView.Adapter<ReciclerViewAdapterQuestion.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titulo,visitas,votos,respuestas;
        private LinearLayout etiquetas;

        public ViewHolder(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            visitas = itemView.findViewById(R.id.visitas);
            votos = itemView.findViewById(R.id.votos);
            respuestas = itemView.findViewById(R.id.respuestas);
            etiquetas = itemView.findViewById(R.id.questions_tags);
        }
    }

    public List<QuestionTags> preguntas;
    public HomeFragment home;

    public ReciclerViewAdapterQuestion(List<QuestionTags> preguntas, HomeFragment home){
        this.preguntas = preguntas;
        this.home = home;
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
        holder.titulo.setText(preguntas.get(position).getQuest().getTitle());
        holder.votos.setText(preguntas.get(position).getQuest().getVotes()+" votos");
        holder.respuestas.setText(""+preguntas.get(position).getQuest().getResponses()+" respuestas");
        holder.visitas.setText(""+preguntas.get(position).getQuest().getVisites()+" vistas");

        holder.etiquetas.removeAllViews();
        for (Tag t : preguntas.get(position).getTags()) {
            TextView miTextView = new TextView(holder.titulo.getContext());
            miTextView.setText(t.getTitulo());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,0,10,0);
            miTextView.setLayoutParams(params);
            holder.etiquetas.addView(miTextView);
        }

        holder.titulo.setOnClickListener(v  -> {
            PreguntaRespuestas secFrag = new PreguntaRespuestas(preguntas.get(position).getQuest().getId());
            FragmentTransaction fragTransaction = home.getFragmentManager().beginTransaction();
            fragTransaction.replace(R.id.nav_host_fragment,secFrag);
            fragTransaction.addToBackStack(null);
            fragTransaction.commit();
        });
    }

    @Override
    public int getItemCount(){
        return preguntas.size();
    }
}
