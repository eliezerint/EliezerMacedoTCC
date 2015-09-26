package br.grupointegrado.appmetaforadevenda.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import net.i2p.android.ext.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Listagem.AdapterTelefone;
import br.grupointegrado.appmetaforadevenda.Pessoa.Telefone;
import br.grupointegrado.appmetaforadevenda.R;

/**
 * Created by eli on 18/09/2015.
 */
public class TelefoneFragment extends Fragment {

    private MaterialEditText edit_telefone;
    private RecyclerView recyclerview_telefone;
    private RadioButton radiobt_comercial;
    private RadioButton radiobt_residencial;
    private RadioButton radiobt_celular;
    private FloatingActionButton bt_add_telefone;


    private String tipoTelefone;
    private String conteudoRadioButon;


    private List<Telefone> lista_telefone;
    private List<Telefone> lista_tel;

    private Telefone tel;


    private AdapterTelefone adaptertelefone;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_telefone, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerview_telefone = (RecyclerView) view.findViewById(R.id.recyclerview_telefone);
        bt_add_telefone = (FloatingActionButton) view.findViewById(R.id.bt_add_telefone);


        tel = new Telefone();
        lista_telefone = new ArrayList<>();


        final StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerview_telefone.setLayoutManager(llm);

        adaptertelefone = new AdapterTelefone(this.getActivity(), new ArrayList<Telefone>()) {
            @Override
            protected void onItemClickListener(int adapterPosition, int layoutPosition) {


            }

            @Override
            protected boolean onLongItemClickListener(int adapterPosition, int layoutPosition) {


                return true;
            }
        };


        recyclerview_telefone.setAdapter(adaptertelefone);

/*
        bt_add_telefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              lista_telefone.add(getTelefone(tel));



                editTelefone.setText(" ");


            }
        });*/
        bt_add_telefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTelefoneDialogs();
            }
        });

    }


    public void recyclerviewTelefone() {
        adaptertelefone.setItems(lista_telefone);
        adaptertelefone.notifyDataSetChanged();
    }


    public Telefone getTelefone(Telefone telefone) {
        return new Telefone(edit_telefone.getText().toString(), tipoTelefone

        );

    }

    public void AddTelefoneDialogs() {

       MaterialDialog dialog = new MaterialDialog.Builder(this.getActivity())
                .title("Telefone")
                .customView(R.layout.layout_dialogs_telefone, true)
                .positiveText("Salvar")
                .negativeText("Sair")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        edit_telefone = (MaterialEditText) dialog.findViewById(R.id.edit_telefone);


                        tipoTelefone = conteudoRadioButon;


                        lista_telefone.add(getTelefone(tel));
                        edit_telefone.setText(" ");

                        recyclerviewTelefone();

                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.dismiss();

                    }

                }).build();

        radiobt_residencial = (RadioButton) dialog.getCustomView().findViewById(R.id.radiobt_residencial);
        radiobt_comercial = (RadioButton) dialog.getCustomView().findViewById(R.id.radiobt_comercial);
        radiobt_celular = (RadioButton) dialog.getCustomView().findViewById(R.id.radiobt_celular);

        radiobt_residencial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     radiobt_comercial.setChecked(false);
                     radiobt_celular.setChecked(false);
                     conteudoRadioButon = "Residencial";
                 }
            }
        });
        radiobt_comercial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radiobt_residencial.setChecked(false);
                    radiobt_celular.setChecked(false);
                    conteudoRadioButon = "Comercial";
                }
            }
        });
        radiobt_celular.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radiobt_comercial.setChecked(false);
                    radiobt_residencial.setChecked(false);
                    conteudoRadioButon = "Celular";
                }
            }
        });
        dialog.show();

    }

    public void AlterarTelefoneDialog() {

        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this.getActivity())
                .title("Telefone")
                .items(R.array.Array_de_alterar)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                        if (text.equals("Editar")) {

                            dialog.dismiss();
                        } else if (text.equals("Excluir")) {

                            dialog.dismiss();
                        }


                    }

                })
                .show();
    }


}