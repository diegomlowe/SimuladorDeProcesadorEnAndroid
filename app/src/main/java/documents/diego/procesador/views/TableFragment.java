package documents.diego.procesador.views;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;

import documents.diego.procesador.Procesador;
import documents.diego.procesador.R;

public class TableFragment extends Fragment {

    GridLayout gridLayout;

    Bundle args;

    Procesador procesador;

    int desalojo;

    ArrayList<ArrayList<String>> tabla = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        args = getArguments();

        //this.procesador = args.getParcelable("procesador");


        return inflater.inflate(R.layout.fragment_table, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        int currentOrientation = getResources().getConfiguration().orientation;

        gridLayout = (GridLayout) getView().findViewById(R.id.grid_layout_fragment_table);

        GridLayout.LayoutParams param = new GridLayout.LayoutParams();


        tabla.add(args.getStringArrayList("Tiempo"));
        tabla.add(args.getStringArrayList("Rutinas"));
        tabla.add(args.getStringArrayList("Ejecutando"));
        tabla.add(args.getStringArrayList("ColaListos"));
        tabla.add(args.getStringArrayList("ColaBloqueados"));


/*
        desalojo = args.getInt("desalojo");

        tabla=this.procesador.procesar(desalojo);*/

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            printHorizontal();
        }
        else {
            printVertical();
        }

    }



    public void printVertical(){


        gridLayout.setRowCount(tabla.get(1).size());
        gridLayout.setColumnCount(tabla.size());

        for(int i=0; i<tabla.get(1).size(); i++)
        {

            for(ArrayList<String> list:tabla){


                TextView txt = new TextView(getContext());

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                //params.setGravity(1);

                params.setMargins(0, 0, 25, 15);

                try{
                    txt.setText(list.get(i));
                }catch (IndexOutOfBoundsException e){txt.setText(" se fue");}

                gridLayout.addView(txt, params);

            }

        }

    }

    public void printHorizontal(){

        gridLayout.setRowCount(tabla.size());
        gridLayout.setColumnCount(tabla.get(1).size());


        for(ArrayList<String> list:tabla){

            for(int i=0; i<tabla.get(1).size(); i++){


                TextView txt = new TextView(getContext());

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                //params.setGravity(1);

                params.setMargins(0, 0, 25, 15);

                try{
                    txt.setText(list.get(i));
                }catch (IndexOutOfBoundsException e){txt.setText(" se fue");}

                gridLayout.addView(txt, params);

            }

        }




    }

}
