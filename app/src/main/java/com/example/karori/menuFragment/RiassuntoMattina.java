package com.example.karori.menuFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.karori.R;
import com.example.karori.Room.Meal;
import com.example.karori.Room.MealViewModel;

import java.time.LocalDate;

public class RiassuntoMattina extends Fragment {
    private static final String KEY_GRASSI = "GRASSIM";
    private static final String KEY_SATURI = "SATURIM";
    private static final String KEY_CARBOIDRATI = "CARBOIDRATIM";
    private static final String KEY_CALORIE = "CALORIEM";

    private TextView tgrassi;
    private TextView tproteine;
    private TextView tcarboidrati;
    private TextView tcalorie;
    CardView card;
    Dialog myDialog;



    public RiassuntoMattina() {
        // Required empty public constructor
    }

    public static RiassuntoMattina newInstance() {
        return new RiassuntoMattina();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_riassunto_mattina, container, false);
        MealViewModel mealViewModel = new ViewModelProvider(this).get(MealViewModel.class);

        tgrassi= (TextView) view.findViewById(R.id.grassi);
        tproteine = (TextView) view.findViewById(R.id.Protein);
        tcalorie= (TextView) view.findViewById(R.id.Calorie);
        tcarboidrati= (TextView) view.findViewById(R.id.carboidrati);
        myDialog = new Dialog(getContext());

        //mattina, dovrà andare a prendere i valori nel database inizialmente
        setValoriRiassuntivi(savedInstanceState, mealViewModel);

        // Inflate the layout for this fragment
        return view;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_CALORIE, "40");
        savedInstanceState.putString(KEY_SATURI, "20");
        savedInstanceState.putString(KEY_CARBOIDRATI, "20");
        savedInstanceState.putString(KEY_GRASSI, "10");
    }

    private void setValoriRiassuntivi(Bundle savedInstanceState, MealViewModel mealViewModel){

        LocalDate currentTime = LocalDate.now();
        mealViewModel.getMealFromDate(currentTime, "colazione").observe(getActivity(), new Observer<Meal>() {
            @Override
            public void onChanged(Meal meal) {
                    if(meal != null){
                        tgrassi.setText(String.valueOf(meal.getGrassiTot()));
                        tproteine.setText(String.valueOf(meal.getProteineTot()));
                        tcarboidrati.setText(String.valueOf(meal.getCarboidratiTot()));
                        tcalorie.setText(String.valueOf(meal.getCalorieTot()));
                    }else {
                        tgrassi.setText("0");
                        tproteine.setText("0");
                        tcarboidrati.setText("0");
                        tcalorie.setText("0");
                    }
                }
        });

    }
}
