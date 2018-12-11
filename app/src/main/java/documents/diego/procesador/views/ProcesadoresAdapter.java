package documents.diego.procesador.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import documents.diego.procesador.Procesador;
import documents.diego.procesador.R;

public class ProcesadoresAdapter extends RecyclerView.Adapter<ProcesadoresAdapter.ViewHolderProcesadores> {

    ArrayList<Procesador> pList;
    ArrayList<String> stringList;

    public ProcesadoresAdapter(ArrayList<Procesador> pList) {
        this.pList = pList;
    }

    @NonNull
    @Override
    public ProcesadoresAdapter.ViewHolderProcesadores onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.celda_procesador, viewGroup, false);
        return new ProcesadoresAdapter.ViewHolderProcesadores(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcesadoresAdapter.ViewHolderProcesadores holder, int i) {

        try {
            holder.id.setText(String.valueOf(pList.get(i).getId()));
        }catch(IndexOutOfBoundsException e){
            holder.id.setText("Proceso: +ID");
        }
        holder.p.setText(pList.get(i).getPolitica());
        holder.ut.setText(String.valueOf(pList.get(i).getCount()));
        holder.so.setText(String.valueOf(pList.get(i).getTotalSO()));
        holder.utO.setText(String.valueOf(pList.get(i).getUtOsciosas()));

    }

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public class ViewHolderProcesadores extends RecyclerView.ViewHolder {

        TextView id, ut, utO, so,p;
        ListView listView;
        ArrayList<TextView> txtProcesos;
        LinearLayout linearLayout;

        public ViewHolderProcesadores(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txt_view_id_celda_procesador);
           // p = itemView.findViewById(R.id.txt_view_politica_celda_procesador);
            ut = itemView.findViewById(R.id.txt_view_ut_celda_procesador);
            so = itemView.findViewById(R.id.txt_view_so_celda_procesador);
            utO = itemView.findViewById(R.id.txt_view_osciosas_celda_procesador);
            //listView = itemView.findViewById(R.id.list_view_celda_procesador);
            //linearLayout = itemView.findViewById(R.id.linear_layout_celda_procesador);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    pList.remove(getAdapterPosition());
                    notifyDataSetChanged();


                    return true;
                }
            });

        }
    }


}
