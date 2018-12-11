package documents.diego.procesador.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import documents.diego.procesador.R;

public class FragmentProceso extends Fragment {

    Button button;
    EditText id;
    EditText ti;
    EditText ut;
    EditText es1;
    EditText es2;
    EditText es3;

    //float tI, uT, eS1, eS2, eS3;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_proceso, container, false);

         return view;


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = getView().findViewById(R.id.btn_proceso_fragment);

        id = getView().findViewById(R.id.et_id_p);
        ti = getView().findViewById(R.id.et_ti_p);
        ut = getView().findViewById(R.id.et_ut_p);
        es1 = getView().findViewById(R.id.et_ti_es1);
        es2 = getView().findViewById(R.id.et_ti_es2);
        es3 = getView().findViewById(R.id.et_ti_es3);


    }

    @Override
    public void onResume() {
        super.onResume();
        id.setText(String.valueOf(MainActivity.getNP()+1));

    }
}
