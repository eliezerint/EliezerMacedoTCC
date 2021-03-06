package br.grupointegrado.appmetaforadevenda.Util;

import android.support.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.RegexpValidator;

/**
 * Created by eli on 19/10/2015.
 */
public class ValidateCpf extends RegexpValidator {


    public ValidateCpf(String errorMessage, String regex) {
        super(errorMessage, regex);
    }

    public String validaCpf(@NonNull String errorMessage, String cpf){

       boolean cpfvalidate = ValidaCpf(Mask.unmask(cpf));

        if (cpfvalidate){
            return null;
        }

        return errorMessage;
    }


    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

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


    public static boolean ValidaCpf(String cpf) {
        if ((cpf == null) || (cpf.length() != 11) || (cpf.equals("00000000000")) ||
                (cpf.equals("11111111111")) || (cpf.equals("22222222222")) || (cpf.equals("33333333333")) ||
                (cpf.equals("44444444444")) || (cpf.equals("55555555555")) || (cpf.equals("66666666666")) ||
                (cpf.equals("77777777777")) || (cpf.equals("88888888888")) || (cpf.equals("99999999999")))
            return false;

        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    //Método para validar Cnpj
    public static boolean ValidaCnpj(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14) || (cnpj.equals("00000000000000")))
            return false;

        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

}
