package documents.diego.procesador.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import documents.diego.procesador.R;

public class ListFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    public void onCLickCreateTextGu(View view){

        Toast.makeText(getContext(), "Que onda", Toast.LENGTH_LONG);
    }
}
