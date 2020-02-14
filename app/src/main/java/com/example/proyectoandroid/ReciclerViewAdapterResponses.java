package com.example.proyectoandroid;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.models.Question;
import com.example.proyectoandroid.ui.home.HomeFragment;
import com.example.proyectoandroid.ui.pregunta_respuestas.PreguntaRespuestas;

import java.util.List;

public class ReciclerViewAdapterResponses extends RecyclerView.Adapter<ReciclerViewAdapterResponses.ViewHolder> implements View.OnTouchListener {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView cuerpo,votos,usuario;
        private Button upvote,downvote;
        private ScrollView scroll;

        public ViewHolder(View itemView){
            super(itemView);
            cuerpo = itemView.findViewById(R.id.item_respuesta_cuerpo);
            votos = itemView.findViewById(R.id.item_respuesta_votos);
            usuario = itemView.findViewById(R.id.item_respuesta_usuario);
            upvote = itemView.findViewById(R.id.item_respuesta_upvote);
            downvote = itemView.findViewById(R.id.item_respuesta_downvote);
            scroll = itemView.findViewById(R.id.item_respuesta_scroll);
        }
    }

    public List<Question> respuestas;
    private PreguntaRespuestas h;

    public ReciclerViewAdapterResponses(List<Question> preguntas, PreguntaRespuestas h){
        this.respuestas = preguntas;
        this.h = h;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_respuesta,parent,false);
        ViewHolder holder  = new ViewHolder(view);

        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        holder.votos.setText(respuestas.get(position).getVotes()+"");
        holder.usuario.setText(respuestas.get(position).getUser());
        holder.cuerpo.setText(respuestas.get(position).getDescription());

        holder.scroll.setOnTouchListener(this);
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//                int meta = holder.cuerpo.getHeight();
//
//                int scr = (holder.scroll.getHeight()+holder.scroll.getScrollY());
//                int res = meta - scr;
//                Log.e("meta",""+meta);
//                Log.e("scroll",""+scr);
//                Log.e("resultado",""+res);
//
//                if (res <= 0){
//                    v.getParent().requestDisallowInterceptTouchEvent(true);
//                }
//                return false;
//            }
//        });
    }


    public boolean onTouch(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }

    @Override
    public int getItemCount(){
        return respuestas.size();
    }
}
