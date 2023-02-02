package com.example.karori.menuFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karori.R;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHoleder> {
    private ArrayList<AlimentoSpecifico> alimentiList;
    private RecyclerViewClickListener listener;

    public recyclerAdapter(ArrayList<AlimentoSpecifico> alimento, RecyclerViewClickListener listener){
        this.alimentiList=alimento;
        this.listener=listener;
    }



    public class MyViewHoleder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nome;
        private TextView calorie;
        private TextView quantità;
        private TextView proteine;
        private CardView card;

        public MyViewHoleder(final View view){
            super(view);
            nome=view.findViewById(R.id.nomeCiboPopUp);
            calorie=view.findViewById(R.id.altreInfoPopUp);
            quantità=view.findViewById(R.id.quantitàPopUp);
            proteine=view.findViewById(R.id.CaloriePopUp);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new MyViewHoleder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHoleder holder, int position) {
         String nome=alimentiList.get(position).getNome();
         String calorie=alimentiList.get(position).getCalorie();
         String quantità=alimentiList.get(position).getQuantità();
         String proteine=alimentiList.get(position).getProteine();

         holder.nome.setText(nome);
         holder.calorie.setText(calorie);
         holder.quantità.setText(quantità);
         holder.proteine.setText(proteine);
    }

    @Override
    public int getItemCount() {
        return alimentiList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}