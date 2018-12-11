package documents.diego.procesador.viewholders;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import documents.diego.procesador.R;


public class ProcesosViewHolder extends ChildViewHolder {

    TextView id, ut, ti, tFin, tBloq, tCarga;

    public ProcesosViewHolder(View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.txt_view_proceso_id_historial);
        ut = itemView.findViewById(R.id.txt_view_proceso_ut_historial);
        ti = itemView.findViewById(R.id.txt_view_proceso_ti_historial);
        tFin = itemView.findViewById(R.id.txt_view_proceso_salida_historial);
        tBloq = itemView.findViewById(R.id.txt_view_proceso_tbloq_historial);
        tCarga = itemView.findViewById(R.id.txt_view_proceso_carga_historial);

    }

    public void setProceso(int iD, int uT, int tI, int tFIn, int tBLoq, int tCArga){

        id.setText("ID: "+String.valueOf(iD));
        ut.setText("UT: "+String.valueOf(uT));
        ti.setText("TI: "+String.valueOf(tI));
        tFin.setText("Salida: "+String.valueOf(tFIn));
        tCarga.setText("Carga: "+String.valueOf(tCArga));
        tBloq.setText("T Bloq: "+tBLoq);



    }
}
