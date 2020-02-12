package com.example.proyectoandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.models.User;

import java.util.List;

public class ReciclerViewAdapterUser extends RecyclerView.Adapter<ReciclerViewAdapterUser.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView usuario,reputacion;
        private LinearLayout tags;

        public ViewHolder(View itemView){
            super(itemView);
            usuario = itemView.findViewById(R.id.usuario_usuario);
            reputacion = itemView.findViewById(R.id.reputacion_usuario);
            tags = itemView.findViewById(R.id.tags_usuario);
        }
    }

    public List<User> usuarios;

    public ReciclerViewAdapterUser(List<User> usuarios){
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuarios,parent,false);
        ViewHolder holder  = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        holder.usuario.setText(usuarios.get(position).getName());
        holder.reputacion.setText(""+usuarios.get(position).getReputation());

        
    }

    @Override
    public int getItemCount(){
        return usuarios.size();
    }
}
