package br.grupointegrado.appmetaforadevenda.TelaCadastro;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.R;

public class CadastroPessoaActivity extends ActionBarActivity {

    private Toolbar atoolbar;
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private TextView tvCPF;
    private TextView tvNome;
    private TextView tvApelido;
    private TextView tvRG;
    private EditText EditCpf;
    private RadioButton radioFisica;
    private RadioButton radioJuridica;
    private EditText EditRG;
    private EditText EditRua;
    private EditText EditBairro;
    private Spinner  SpinnerCidade;
    private EditText EditDataCadastro;
    private EditText EditDataUltima;
    private EditText EditEndereco;
    private EditText EditNumero;
    private EditText EditNome;
    private EditText EditValorUtimacompra;
    private Spinner SpinnerTelefone;





    private boolean cpf;
    private boolean cnpj;
    private Integer cdCidade;
    private String Cidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        atoolbar = (Toolbar) findViewById(R.id.tb_main);
        atoolbar.setTitle("Cadastrar Cliente");
        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        atoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent it = null;

                switch (menuItem.getItemId()) {
                    case R.drawable.ic_save_black_18dp:
                        Toast.makeText(getBaseContext(), " Salvo com susseso ", Toast.LENGTH_LONG).show();

                        break;

                }

                startActivity(it);
                return true;
            }
        });
        atoolbar.inflateMenu(R.menu.menu_cadastro_pessoa);

        //Instancia do XML do Editext e spinner

        EditCpf = (EditText) findViewById(R.id.EditCpf);
        tvCPF = (TextView) findViewById(R.id.tvCPF);
        tvNome = (TextView) findViewById(R.id.tvNome);
        tvApelido = (TextView) findViewById(R.id.tvApelido);
        tvRG = (TextView) findViewById(R.id.tvRG);
        radioFisica = (RadioButton) findViewById(R.id.radioFisica);
        radioJuridica = (RadioButton) findViewById(R.id.radioJuridica);
        EditRG = (EditText)findViewById(R.id.EditRG);
        EditBairro = (EditText)findViewById(R.id.EditBairro);
        SpinnerCidade = (Spinner)findViewById(R.id.SpinnerCidade);
        EditDataCadastro = (EditText)findViewById(R.id.EditDataCadastro);
        EditNumero = (EditText)findViewById(R.id.EditNumero);
        SpinnerTelefone = (Spinner)findViewById(R.id.SpinnerTelefone);


        //click na spinner


        //CPF|CPNJ Validacao
        EditCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                                             public void onFocusChange(View v, boolean hasFocus) {
                                                 if (!hasFocus) {


                                                     if (radioFisica.isChecked()) {
                                                         cpf = ValidaCpf(EditCpf.getText().toString());
                                                         if (cpf == true) {
                                                             Toast.makeText(getBaseContext(), "CPF valido", Toast.LENGTH_SHORT).show();
                                                         } else
                                                             Toast.makeText(getBaseContext(), "CPF Invalido", Toast.LENGTH_SHORT).show();
                                                     }

                                                 } else if (radioJuridica.isChecked()) {
                                                     cnpj = ValidaCnpj(EditCpf.getText().toString());
                                                     if (cnpj == true) {
                                                         Toast.makeText(getBaseContext(), "CNPJ valido", Toast.LENGTH_SHORT).show();
                                                     } else {
                                                         Toast.makeText(getBaseContext(), "CNPJ Invalido", Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
                                             }
                                         }
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_pessoa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.home:
                finish();
                break;
            case R.id.Salvarpessoa:
                break;
        }



        return true;
    }

    //ChecBoxFisica
    public void RadioBoxFisica(View v) {

        if (radioFisica.isClickable()) {
            tvCPF.setText("CPF");
            tvNome.setText("Nome");
            tvApelido.setText("Apelido");
            tvRG.setText("RG");
            radioFisica.setChecked(true);
            radioJuridica.setChecked(false);

        }
    }

    //ChecBoxJuridica
    public void RadioBoxJuridica(View v) {

        if (radioJuridica.isClickable()) {
            tvCPF.setText("CNPJ");
            tvNome.setText("Razão Social");
            tvApelido.setText("Nome Fantasia");
            tvRG.setText("Inscrição Estadual");
            radioJuridica.setChecked(true);
            radioFisica.setChecked(false);

        }
    }

    //entrada de dados
    public void InputDados() {

    }

    //Cadastrar telefone
    public void CadastrarTelefone(View view){

        boolean wrapInScrollView = true;
        MaterialDialog app = new MaterialDialog.Builder(this)
                .title("Cadastrar")
                .customView(R.layout.activity_cadastro_telefone, wrapInScrollView)
                .positiveText("Salvar")
                .negativeText("Sair")
                .autoDismiss(false)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        EditText ed = (EditText) dialog.findViewById(R.id.EditNumero);
                        if (cadastrarTelefone(ed.getText().toString()))
                            dialog.dismiss();
                        else
                            Toast.makeText(getApplication(), "Falha aoi cadastrar telefone.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .show();

    }

    private boolean cadastrarTelefone(String numero) {
        return false;
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



}
