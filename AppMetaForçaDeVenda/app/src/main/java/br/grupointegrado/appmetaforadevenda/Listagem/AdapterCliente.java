package br.grupointegrado.appmetaforadevenda.Listagem;

import android.content.Context;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.Util.Mask;

/**
 * Created by eli on 15/09/2015.
 */
public class AdapterCliente extends AbstractAdapter<AdapterCliente.ViewHolder, Pessoa> {


    public AdapterCliente(Context context) {
        super(context);
    }

    public AdapterCliente(Context context,List<Pessoa> items) {
        super(context, items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.cliente_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, Pessoa item) {
        holder.tvNome.setText(item.getRazaoSocialNome());
        holder.tvApelido.setText(item.getFantasiaApelido());
        if (item.getCnpjCpf().length() == 11){
        holder.tvCPF.setText(item.getCnpjCpf());
            holder.tvCnpj.setVisibility(View.GONE);
            holder.tvCPF.setVisibility(View.VISIBLE);
        }
        if (item.getCnpjCpf().length() == 14) {
            holder.tvCnpj.setText(item.getCnpjCpf());
            holder.tvCnpj.setVisibility(View.VISIBLE);
            holder.tvCPF.setVisibility(View.GONE);
        }
        holder.tvCod.setText(item.getIdpessoa()+"");
    }



    public class ViewHolder extends AbstractAdapter.AbstractViewHolder {
        final TextView tvNome;
        final TextView tvApelido;
        final TextView tvCPF;
        final TextView tvCnpj;
        final TextView tvCod;


        public ViewHolder(View view) {
            super(view);
            tvCod = (TextView) view.findViewById(R.id.tv_cod);
            tvNome = (TextView) view.findViewById(R.id.tvnome);
            tvApelido = (TextView) view.findViewById(R.id.tvApelido);
            tvCPF = (TextView) view.findViewById(R.id.tvcpf);
            tvCnpj = (TextView) view.findViewById(R.id.tvcnpj);

            TextWatcher cpfmask = Mask.insert("###.###.###-##",tvCPF);

            tvCPF.addTextChangedListener(cpfmask);

            TextWatcher cnpjmask = Mask.insert("##.###.###/####-##", tvCnpj);

            tvCnpj.addTextChangedListener(cnpjmask);

        }
    }
}


