package br.grupointegrado.appmetaforadevenda.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

import br.grupointegrado.appmetaforadevenda.Dao.PessoaDao;
import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.Pessoa.Telefone;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.TelaConsulta.ConsultaCidadeActivity;
import br.grupointegrado.appmetaforadevenda.interfaces.RecyclerViewOnClickListenerHack;

import static br.grupointegrado.appmetaforadevenda.Util.ConvesorUtil.stringParaDate;
import static br.grupointegrado.appmetaforadevenda.Util.ConvesorUtil.stringParaDouble;


public class PessoaFragment extends Fragment  {


    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private MaterialEditText editCpf;
    public RadioButton radioFisica;
    public RadioButton radioJuridica;
    private MaterialEditText edRG;
    private MaterialEditText edBairro;
    private MaterialEditText edDataCadastro;
    private MaterialEditText edDataUltima;
    private MaterialEditText edEndereco;
    private MaterialEditText edNumero;
    private MaterialEditText edNome;
    private MaterialEditText edValorUltimacompra;
    private MaterialEditText edCidade;
    private MaterialEditText edApelido;
    private MaterialEditText edEmail;
    private MaterialEditText edDataNascimento;
    private MaterialEditText edcomplemento;


    private boolean cpf;
    private boolean cnpj;
    private Integer cdCidade;
    private String Cidade;
    private Integer idpessoa;
    private List listatelefone;
    private Integer idCidade;


    private PessoaDao clientedao;
    private Pessoa pessoa;
    private Telefone telefone;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_pessoa, container, false);


        editCpf = (MaterialEditText) view.findViewById(R.id.edit_cpf);
        radioFisica = (RadioButton) view.findViewById(R.id.radiofisica);
        radioJuridica = (RadioButton) view.findViewById(R.id.radiojuridica);
        edRG = (MaterialEditText) view.findViewById(R.id.edit_rg);
        edBairro = (MaterialEditText) view.findViewById(R.id.edit_bairro);

        edDataCadastro = (MaterialEditText) view.findViewById(R.id.edit_data_cadastro);
        edNumero = (MaterialEditText) view.findViewById(R.id.edit_numero);
        edCidade = (MaterialEditText) view.findViewById(R.id.edit_cidade);

        edApelido = (MaterialEditText) view.findViewById(R.id.edit_apelido);
        edEmail = (MaterialEditText) view.findViewById(R.id.edit_email);
        edNome = (MaterialEditText) view.findViewById(R.id.edit_nome);
        edDataUltima = (MaterialEditText) view.findViewById(R.id.edit_data_ultima);
        edValorUltimacompra = (MaterialEditText) view.findViewById(R.id.edit_valor_ultima_compra);
        edEndereco = (MaterialEditText) view.findViewById(R.id.edit_endereco);
        edcomplemento = (MaterialEditText) view.findViewById(R.id.edit_complemento);
        edDataNascimento = (MaterialEditText) view.findViewById(R.id.edit_data_nascimento);


        //colocando data de cadastro
        edDataCadastro.setText(getDateTime().toString());

        clientedao = new PessoaDao(this.getActivity());



        //CPF|CPNJ Validacao
        editCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                                             public void onFocusChange(View v, boolean hasFocus) {
                                                 if (!hasFocus) {


                                                     if (radioFisica.isChecked()) {
                                                         cpf = ValidaCpf(editCpf.getText().toString());
                                                         if (cpf == true) {
                                                             Toast.makeText(getActivity(), "CPF valido", Toast.LENGTH_SHORT).show();
                                                         } else
                                                             Toast.makeText(getActivity(), "CPF Invalido", Toast.LENGTH_SHORT).show();
                                                     }

                                                 } else if (radioJuridica.isChecked()) {
                                                     cnpj = ValidaCnpj(editCpf.getText().toString());
                                                     if (cnpj == true) {
                                                         Toast.makeText(getActivity(), "CNPJ valido", Toast.LENGTH_SHORT).show();
                                                     } else {
                                                         Toast.makeText(getActivity(), "CNPJ Invalido", Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
                                             }
                                         }
        );




       return  view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Instancia do XML do Editext e spinner
        radioFisica.setChecked(true);

        edCidade = (MaterialEditText) view.findViewById(R.id.edit_cidade);
        edCidade.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    selecioarCidade();
            }
        });

        radioFisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioFisica.setChecked(true);
                RadioBoxFisica(v);
            }
        });
        radioJuridica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioJuridica.setChecked(true);
                RadioBoxJuridica(v);
            }
        });
    }



private static final int REQUEST_CONSULTA_CIDADE = 1001;

    private void selecioarCidade() {
        Intent intent = new Intent(getActivity(), ConsultaCidadeActivity.class);
        intent.putExtra("selecionar_cidade", true);
        startActivityForResult(intent, REQUEST_CONSULTA_CIDADE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CONSULTA_CIDADE == requestCode && resultCode == getActivity().RESULT_OK) {
            int idCidade = data.getIntExtra("cidade_id", 0);
            Toast.makeText(getActivity(), "Cidade selecionada: " + idCidade, Toast.LENGTH_SHORT).show();
            edCidade.setText(idCidade + "");
            // consulta a cidade e seta na pessoa
        }
    }



    //ChecBoxFisica
    public void RadioBoxFisica(View v) {

        if (radioFisica.isClickable()) {
            editCpf.setHint("CPF");
            edNome.setHint("Nome");
            edApelido.setHint("Apelido");
            edRG.setHint("RG");
            edDataNascimento.setHint("Data Nascimento");
            radioFisica.setChecked(true);
            radioJuridica.setChecked(false);

        }
    }

    //ChecBoxJuridica
    public void RadioBoxJuridica(View v) {

        if (radioJuridica.isClickable()) {
            editCpf.setHint("CNPJ");
            edNome.setHint("Razão Social");
            edApelido.setHint("Nome Fantasia");
            edRG.setHint("Inscrição Estadual");
            edDataNascimento.setHint("Data de Abertura da Empresa");
            radioJuridica.setChecked(true);
            radioFisica.setChecked(false);

        }
    }

    //entrada de dados
    public void InputDados() {

    }

    //chama a tela de consulta de cidade
    public void ConsultaCidade(View ciew) {

        Intent i = new Intent(this.getActivity(), ConsultaCidadeActivity.class);

        startActivity(i);


    }





    //Método para calcular digito verificador
    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    //Método para validar Cpf
    public static boolean ValidaCpf(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) return false;

        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    //Método para validar Cnpj
    public static boolean ValidaCnpj(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) return false;

        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    //  public Pessoa getpesso(){

    // return  new Pessoa(cdCidade,EditCpf.toString(),);


    // }


    public  void savePessoa(){

        try{
            clientedao.savePessoa(getPessoa());
            Toast.makeText(this.getActivity(), "salvo com susseço",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this.getActivity(), e.toString(),Toast.LENGTH_SHORT).show();
        }


    }

    public Pessoa getPessoa (){
        return new Pessoa  (idCidade,
                editCpf.getText().toString(),edNome.getText().toString(),edApelido.getText().toString(),
                edRG.getText().toString(),edEndereco.getText().toString(),edNumero.getText().toString(),
                edBairro.getText().toString(),edcomplemento.getText().toString(),edCidade.getText().toString(),
                edEmail.getText().toString(),stringParaDate(edDataUltima.getText().toString()),
                stringParaDouble(edValorUltimacompra.getText().toString()),
                stringParaDate(edDataCadastro.getText().toString())

        );
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(); return dateFormat.format(date);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.Salvarpessoa:
                savePessoa();
                break;


        }

        return true;
    }

}


