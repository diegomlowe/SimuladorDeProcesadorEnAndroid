package documents.diego.procesador.viewholders;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import documents.diego.procesador.Procesador;
import documents.diego.procesador.R;
import documents.diego.procesador.views.MainActivity;

public class ProcesadoresViewHolder extends GroupViewHolder {

    private TextView id, ut, utTotales, utO, so, utEs, soU, des;

    public ProcesadoresViewHolder(View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.txt_view_id_celda_procesador);
        utEs = itemView.findViewById(R.id.txt_view_utes_celda_procesador);
        utTotales = itemView.findViewById(R.id.txt_view_ut_totales_celda_procesador);
        //p = itemView.findViewById(R.id.txt_view_politica_celda_procesador);
        ut = itemView.findViewById(R.id.txt_view_ut_celda_procesador);
        soU = itemView.findViewById(R.id.txt_view_so_por_unidad_celda_procesador);
        so = itemView.findViewById(R.id.txt_view_so_celda_procesador);
        utO = itemView.findViewById(R.id.txt_view_osciosas_celda_procesador);
        des = itemView.findViewById(R.id.txt_view_desalojo_celda_procesador);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                MainActivity.getProcesadoresList().remove(getAdapterPosition());
                //notifyDataSetChanged();


                return true;
            }
        });
    }

    public void setProcesador(String title){

        List<Procesador> pList =  MainActivity.getProcesadoresList();
        Procesador procesador= null;

        for(Procesador p: pList){
            if(String.valueOf(p.getId()).equals(title)) procesador = p;

        }

        if(procesador.getPolitica().equals("FIFO")||procesador.getPolitica().equals("MCP")){

            des.setVisibility(View.INVISIBLE);

        } else {

            des.setText("Desalojo: "+String.valueOf(procesador.getDesalojo()));

        }

        id.setText("Procesador "+String.valueOf(title)+": "+procesador.getPolitica());
        utEs.setText("UT/ES: "+String.valueOf(procesador.getUtEs()));
        utTotales.setText("Total UT: "+String.valueOf(procesador.getCount()));
        //p.setText(procesador.getPolitica());
        ut.setText("Eficiencia: "+String.valueOf((procesador.getCount()*100)/(procesador.getCount()+procesador.getUtOsciosas())));
        soU.setText("SO Total:"+String.valueOf(procesador.getTotalSO()));
        so.setText("SO/R: "+String.valueOf(procesador.getUtRSO()));
        utO.setText("UT Osciosas: "+String.valueOf(procesador.getUtOsciosas()));



    }
}
