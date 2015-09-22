package br.grupointegrado.appmetaforadevenda.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.grupointegrado.appmetaforadevenda.Pedido.Pedido;
import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.interfaces.RecyclerViewOnClickListenerHack;

/**
 * Created by eli on 18/09/2015.
 */
public class PedidoFragment extends Fragment implements RecyclerViewOnClickListenerHack, View.OnClickListener{

        protected List<Pedido> mList;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.activity_cadastro_pedido, container, false);
        }




        @Override
        public void onDetach() {
            super.onDetach();

        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public void onClickListener(View view, int position) {

        }

        @Override
        public void onLongPressClickListener(View view, int position) {

        }

        /**
         * This interface must be implemented by activities that contain this
         * fragment to allow an interaction in this fragment to be communicated
         * to the activity and potentially other fragments contained in that
         * activity.
         * <p/>
         * See the Android Training lesson <a href=
         * "http://developer.android.com/training/basics/fragments/communicating.html"
         * >Communicating with Other Fragments</a> for more information.
         */
        public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            public void onFragmentInteraction(Uri uri);
        }

    }
