package com.example.proyectoandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.models.Tag;
import com.example.proyectoandroid.models.UsuarioTag;
import com.example.proyectoandroid.ui.perfil.PerfilFragment;
import com.example.proyectoandroid.ui.pregunta.Pregunta;
import com.example.proyectoandroid.ui.usuarios.UsuariosFragment;

import java.util.List;

public class ReciclerViewAdapterUser extends RecyclerView.Adapter<ReciclerViewAdapterUser.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView usuario,reputacion;
        private LinearLayout tags;

        public ViewHolder(View itemView){
            super(itemView);
            usuario = itemView.findViewById(R.id.usuario_usuario);
            reputacion = itemView.findViewById(R.id.reputacion_usuario);
            tags = itemView.findViewById(R.id.tags_usuario);
        }

    }

    public List<UsuarioTag> usuarios;
    public UsuariosFragment f;

    public ReciclerViewAdapterUser(List<UsuarioTag> usuarios, UsuariosFragment f){
        this.usuarios = usuarios;
        this.f = f;
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
        holder.usuario.setText(usuarios.get(position).getUser().getName());
        holder.reputacion.setText(""+usuarios.get(position).getUser().getReputation());

        for (Tag t: usuarios.get(position).getTags()) {
            TextView tag = new TextView(holder.usuario.getContext());
            tag.setText(t.getTitulo());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,5,10,5);
            tag.setLayoutParams(params);
            holder.tags.addView(tag);
        }

        holder.usuario.setOnClickListener(v ->{
            PerfilFragment secFrag = new PerfilFragment(usuarios.get(position).getUser().getEmail());
            FragmentTransaction fragTransaction = f.getFragmentManager().beginTransaction();
            fragTransaction.replace(R.id.nav_host_fragment,secFrag);
            fragTransaction.addToBackStack(null);
            fragTransaction.commit();

        });

    }

    @Override
    public int getItemCount(){
        return usuarios.size();
    }
}
