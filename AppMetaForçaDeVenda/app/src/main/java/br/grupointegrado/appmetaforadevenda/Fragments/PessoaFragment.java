package br.grupointegrado.appmetaforadevenda.Fragments;


import android.content.DialogInterface;
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

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import br.grupointegrado.appmetaforadevenda.Dao.CidadeDao;
import br.grupointegrado.appmetaforadevenda.Dao.PessoaDao;
import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.Pessoa.Telefone;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.TelaConsulta.ConsultaCidadeActivity;
import br.grupointegrado.appmetaforadevenda.interfaces.RecyclerViewOnClickListenerHack;

import static br.grupointegrado.appmetaforadevenda.Util.ConvesorUtil.stringParaDate;
import static br.grupointegrado.appmetaforadevenda.Util.ConvesorUtil.stringParaDouble;


public class PessoaFragment extends Fragment implements DatePickerDialog.OnDateSetListener, DialogInterface.OnCancelListener {


    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public MaterialEditText editCpf;
    public RadioButton radioFisica;
    public RadioButton radioJuridica;
    public MaterialEditText edRG;
    public MaterialEditText edBairro;
    public MaterialEditText edDataCadastro;
    public MaterialEditText edDataUltima;
    public MaterialEditText edEndereco;
    public MaterialEditText edNumero;
    public MaterialEditText edNome;
    public MaterialEditText edValorUltimacompra;
    public MaterialEditText edCidade;
    public MaterialEditText edApelido;
    public MaterialEditText edEmail;
    public MaterialEditText edDataNascimento;
    public MaterialEditText edcomplemento;


    public boolean cpf;
    public boolean cnpj;
    public Integer cdCidade;
    public String Cidade;
    public Integer idpessoa;
    public List listatelefone;
    public Integer idCidade;


    public PessoaDao clientedao;
    public Pessoa pessoa;
    public Telefone telefone;
    public CidadeDao cidadedao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_pessoa, container, false);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Instancia do XML do Editext e spinner


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

        edNome.addValidator(new RegexpValidator("Nome Completo Inválido", "\\w+\\s+\\w+"));
        edNome.setValidateOnFocusLost(true);


        //colocando data de cadastro
        edDataCadastro.setText(getDateTime().toString());

        clientedao = new PessoaDao(this.getActivity());

        pessoa = new Pessoa();

        cidadedao = new CidadeDao(this.getActivity());


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

        edDataNascimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                dataPicker();

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
            idCidade = data.getIntExtra("cidade_id", 0);
            String nomeCidade;
            nomeCidade = cidadedao.ConsultaCidadeporid(Integer.toString(idCidade));
            edCidade.setText(nomeCidade);

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


    public void savePessoatela() {
        clientedao.savePessoa(getPessoa1());
       /* try {
            clientedao.savePessoa(getPessoa());
            Toast.makeText(getActivity(), "salvo com susseço", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
*/

    }

    public Pessoa getPessoa1() {
        return  new Pessoa();


    }


    public Pessoa getPessoa() {
        return new Pessoa(idCidade,
                editCpf.getText().toString(),
                edNome.getText().toString(),
                edApelido.getText().toString(),
                edRG.getText().toString(),
                edEndereco.getText().toString(),
                edNumero.getText().toString(),
                edBairro.getText().toString(),
                edcomplemento.getText().toString(),
                edCidade.getText().toString(),
                stringParaDate(edDataNascimento.getText().toString()),
                edEmail.getText().toString(),
                stringParaDate(edDataUltima.getText().toString()),
                Double.parseDouble(edValorUltimacompra.getText().toString()),
                stringParaDate(edDataCadastro.getText().toString())

        );
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }


    //Date PickerDialog
    private int ano, mes, day;

    public void dataPicker() {
        dateTimeData();
        Calendar datapadrao = Calendar.getInstance();
        datapadrao.set(ano, mes, day);

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                this,
                ano = datapadrao.get(Calendar.YEAR),
                mes = datapadrao.get(Calendar.MONTH),
                day = datapadrao.get(Calendar.DAY_OF_MONTH)

        );
        Calendar cMax = Calendar.getInstance();
        Calendar cMin = Calendar.getInstance();
        cMin.set(1900,0,1);
        cMax.set(cMax.get(Calendar.YEAR), mes, day );
        datePickerDialog.setMaxDate(cMax);
        datePickerDialog.setMinDate(cMin);

        datePickerDialog.setOnCancelListener(this);
        datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");

    }

    private void dateTimeData() {

        if (ano == 0) {
            Calendar c = Calendar.getInstance();
            ano = c.get(Calendar.YEAR);
            mes = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }
    }


    @Override
    public void onCancel(DialogInterface dialog) {

        ano = mes = day =  0;
        edDataNascimento.setText("");

    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {

        Calendar tDefault = Calendar.getInstance();
        tDefault.set(ano, mes, day);

        ano = i;
        mes = i1;
        day = i2;

        edDataNascimento.setText( (day < 10 ? "0"+day : day)+"/"+
                (mes+1 < 10 ? "0"+(mes+1) : mes+1)+"/"+
                ano);

    }


}





