package documents.diego.procesador.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import documents.diego.procesador.Proceso;
import documents.diego.procesador.R;

public class ProcesosAdapter extends RecyclerView.Adapter<ProcesosAdapter.ViewHolderProcesos> {

    ArrayList<Proceso> pList;

    public ProcesosAdapter(ArrayList<Proceso> pList) {
        this.pList = pList;
    }

    @NonNull
    @Override
    public ViewHolderProcesos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.celda_proceso, viewGroup, false);
        return new ViewHolderProcesos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProcesos holder, int i) {

        holder.id.setText(String.valueOf(pList.get(i).getId()));
        holder.ut.setText(String.valueOf(pList.get(i).getUt()));
        holder.ti.setText(String.valueOf(pList.get(i).getTi()));


        try {
            holder.es1.setText("ES1:"+String.valueOf(pList.get(i).getEsList().get(0).getTi()));
        }catch(IndexOutOfBoundsException e){
            holder.es1.setText("ES1:/");
        }

        try {
            holder.es2.setText("ES2:"+String.valueOf(pList.get(i).getEsList().get(1).getTi()));
        }catch (IndexOutOfBoundsException e){

            holder.es2.setText("ES2:/");
        }

        try {
            holder.es3.setText("ES3:"+String.valueOf(pList.get(i).getEsList().get(2).getTi()));
        }catch(IndexOutOfBoundsException e){

            holder.es1.setText("ES3:/");
        }

    }

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public class ViewHolderProcesos extends RecyclerView.ViewHolder {

        TextView id, ut, ti, es1, es2, es3;


        public ViewHolderProcesos(@NonNull final View itemView) {
            super(itemView);

            id = (TextView)itemView.findViewById(R.id.txt_view_id_celda);
            ut = (TextView)itemView.findViewById(R.id.txt_view_ut_celda);
            ti = (TextView)itemView.findViewById(R.id.txt_view_ti_celda);
            es1 = (TextView)itemView.findViewById(R.id.txt_view_es1_celda);
            es2 = (TextView)itemView.findViewById(R.id.txt_view_es2_celda);
            es3 = (TextView)itemView.findViewById(R.id.txt_view_es3_celda);

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

