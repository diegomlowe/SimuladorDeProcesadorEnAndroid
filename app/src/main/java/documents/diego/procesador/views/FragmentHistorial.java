package documents.diego.procesador.views;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import documents.diego.procesador.Procesador;
import documents.diego.procesador.R;
import documents.diego.procesador.adapters.AdapterProcesadores;

public class FragmentHistorial extends Fragment {

    ArrayList<Procesador> procesadoresList;

    RecyclerView recyclerProcesadores;
    //ProcesadoresAdapter adapter;
    AdapterProcesadores pAdapter;

    LinearLayoutManager lm;

    Bundle args;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //args = getArguments();

        return inflater.inflate(R.layout.fragment_historial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerProcesadores = (RecyclerView)getView().findViewById(R.id.recycler_procesadores_historial);

        pAdapter = new AdapterProcesadores(MainActivity.getProcesadoresList());
        recyclerProcesadores.setLayoutManager( new LinearLayoutManager(getContext()));
        recyclerProcesadores.setAdapter(pAdapter);


/*
        procesadoresList = new ArrayList<>();

        for(Parcelable p: args.getParcelableArrayList("lista procesadores")){

            procesadoresList.add((Procesador) p);

        }


        lm = new LinearLayoutManager(getContext());
        lm.setSmoothScrollbarEnabled(false);
        lm.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerProcesadores = (RecyclerView)getView().findViewById(R.id.recycler_procesadores_historial);
        recyclerProcesadores.setLayoutManager(lm);
        adapter = new ProcesadoresAdapter(procesadoresList);
        recyclerProcesadores.setLongClickable(true);
        recyclerProcesadores.setAdapter(adapter);
        recyclerProcesadores.setNestedScrollingEnabled(false);
*/

    }
}
