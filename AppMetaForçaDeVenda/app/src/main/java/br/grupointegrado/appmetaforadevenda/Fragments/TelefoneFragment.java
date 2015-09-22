package br.grupointegrado.appmetaforadevenda.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.grupointegrado.appmetaforadevenda.R;

/**
 * Created by eli on 18/09/2015.
 */
public class TelefoneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_telefone, container, false);
        return view;
    }
}



