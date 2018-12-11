package documents.diego.procesador.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import documents.diego.procesador.Procesador;
import documents.diego.procesador.Proceso;
import documents.diego.procesador.R;
import documents.diego.procesador.viewholders.ProcesadoresViewHolder;
import documents.diego.procesador.viewholders.ProcesosViewHolder;
import documents.diego.procesador.views.MainActivity;

public class AdapterProcesadores extends ExpandableRecyclerViewAdapter <ProcesadoresViewHolder, ProcesosViewHolder>{


    public AdapterProcesadores(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public ProcesadoresViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_procesador, parent, false);
        return new ProcesadoresViewHolder(view);
    }

    @Override
    public ProcesosViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_proceso_historial, parent, false);
        return new ProcesosViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ProcesosViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {

        Proceso p = (Proceso)group.getItems().get(childIndex);

        holder.setProceso(p.getId(), p.getUt(), p.getTi(), p.gettFin(), p.gettBloq(), p.getTCarga());



    }

    @Override
    public void onBindGroupViewHolder(ProcesadoresViewHolder holder, int i, ExpandableGroup  group) {

        holder.setProcesador(group.getTitle());

    }



}
