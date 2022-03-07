package utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UtilsMobile {

    private final Logger logger;
    public static List<String> ListDeviceSerialNumbers = null;

    public UtilsMobile() {
        logger = LogManager.getLogger(UtilsMobile.class);
    }

    public int randomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public String geraDataDeNascimento() {

        logger.info("Início da geração da data de nascimento.");

        Random random = new Random();
        String dia = Integer.toString(randomNumberInRange(1, 28));
        String mes = Integer.toString(randomNumberInRange(1, 12));
        String ano = Integer.toString(randomNumberInRange(1970, 2000));

        if (dia.length() == 1) {
            dia = "0" + dia;
        }

        if (mes.length() == 1) {
            mes = "0" + mes;
        }

        logger.debug("Fim da geração da data de nascimento.");

        return dia + mes + ano;
    }

    public String gerarRne() {

        Random random = new Random();
        String numeroAleatorio = "";
        for (int contador = 0; contador < 10; contador++) {
            numeroAleatorio += random.nextInt(9);
        }

        return numeroAleatorio;
    }

    public String gerarPassaporte() {

        Random randomSelecaoLetras = new Random();
        Random randomSelecaoNumeros = new Random();

        String[] vetorLetras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String letrasRetornadas = "";
        String numerosRetornados = "";

        // gerar letras do passaporte
        for (int i = 0; i < 2; i++) {
            int posicaoSorteada = randomSelecaoLetras.nextInt(26);
            letrasRetornadas += vetorLetras[posicaoSorteada].toString();
        }

        // gerar números do passaporte
        for (int contador = 0; contador < 6; contador++) {
            numerosRetornados += randomSelecaoNumeros.nextInt(9);
        }

        // return concatenando as letras com números
        return letrasRetornadas + numerosRetornados;

    }

    public String geraRG() throws Exception {
        String numeroGerado = "";
        Integer numero;
        for (int i = 0; i < 8; i++) {
            numero = Integer.valueOf((int) (Math.random() * 10));
            numeroGerado += numero.toString();
        }

        return numeroGerado;
    }

    public String gerarCPF() {

        int digito1 = 0, digito2 = 0, resto = 0;
        String nDigResult;
        String numerosContatenados;
        String numeroGerado;

        Random numeroAleatorio = new Random();

        // numeros gerados
        int n1 = numeroAleatorio.nextInt(10);
        int n2 = numeroAleatorio.nextInt(10);
        int n3 = numeroAleatorio.nextInt(10);
        int n4 = numeroAleatorio.nextInt(10);
        int n5 = numeroAleatorio.nextInt(10);
        int n6 = numeroAleatorio.nextInt(10);
        int n7 = numeroAleatorio.nextInt(10);
        int n8 = numeroAleatorio.nextInt(10);
        int n9 = numeroAleatorio.nextInt(10);

        int soma = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        int valor = (soma / 11) * 11;

        digito1 = soma - valor;

        // Primeiro resto da divisão por 11.
        resto = (digito1 % 11);

        if (digito1 < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        int soma2 = digito1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        int valor2 = (soma2 / 11) * 11;

        digito2 = soma2 - valor2;

        // Primeiro resto da divisão por 11.
        resto = (digito2 % 11);

        if (digito2 < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        // Conctenando os numeros
        numerosContatenados = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + String.valueOf(n4)
                + String.valueOf(n5) + String.valueOf(n6) + String.valueOf(n7) + String.valueOf(n8)
                + String.valueOf(n9);

        // Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        numeroGerado = numerosContatenados + nDigResult;

        System.out.println("CPF Gerado " + numeroGerado);

        // fim do metodo geraCPF

        return numeroGerado;

    }

    public String receberCPF() throws Exception {

        String resultadoCPF = gerarCPF();

        return resultadoCPF;
    }

    public static String gerarCNPJ() throws Exception {
        int digito1 = 0, digito2 = 0, resto = 0;
        String numeroGerado = "";
        Integer numero;
        for (int i = 0; i < 12; i++) {
            numero = Integer.valueOf((int) (Math.random() * 10));
            numeroGerado += numero.toString();
        }

        String arrNumeros[] = numeroGerado.split("");
        int soma = 0;
        int peso = 5;
        for (int i = 0; i < 4; i++) {
            soma += Integer.parseInt(arrNumeros[i]) * peso;
            peso--;
        }
        peso = 9;
        for (int i = 4; i < 12; i++) {
            soma += Integer.parseInt(arrNumeros[i]) * peso;
            peso--;
        }

        int valor = (soma / 11) * 11;
        digito1 = soma - valor;
        resto = (digito1 % 11);
        if (digito1 < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        numeroGerado += String.valueOf(digito1);

        arrNumeros = numeroGerado.split("");

        int soma2 = 0;
        int peso2 = 6;
        for (int i = 0; i < 5; i++) {
            soma2 += Integer.parseInt(arrNumeros[i]) * peso2;
            peso2--;
        }
        peso2 = 9;
        for (int i = 5; i < 13; i++) {
            soma2 += Integer.parseInt(arrNumeros[i]) * peso2;
            peso2--;
        }

        int valor2 = (soma2 / 11) * 11;
        digito2 = soma2 - valor2;

        resto = (digito2 % 11);
        if (digito2 < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        numeroGerado += String.valueOf(digito2);

        return numeroGerado;
    }

    public String sendTextByAdb(String text) {
        Scanner scanner = null;
        try {
            String os = System.getProperty("os.name");
            String textByAdb = "adb shell input text '" + text + "'";
            if (os.startsWith("Windows"))
                scanner = new Scanner(Runtime.getRuntime().exec("cmd /c " + textByAdb).getInputStream());
            else if (os.startsWith("Mac"))
                scanner = new Scanner(Runtime.getRuntime().exec(new String[]{"/bin/bash", "-l", "-c", textByAdb})
                        .getInputStream());
        } catch (IOException e) {
            System.err.println("Erro ao digitar texto pelo adb");
        }
        String deviceSerialNumver = (scanner != null && scanner.hasNext()) ? scanner.next() : "";
        return deviceSerialNumver;
    }

    public String sendEventByAdb(String text) {
        Scanner scanner = null;
        try {
            String os = System.getProperty("os.name");
            String textByAdb = "adb shell input keyevent '" + text + "'";
            if (os.startsWith("Windows"))
                scanner = new Scanner(Runtime.getRuntime().exec("cmd /c " + textByAdb).getInputStream());
            else if (os.startsWith("Mac"))
                scanner = new Scanner(Runtime.getRuntime().exec(new String[]{"/bin/bash", "-l", "-c", textByAdb})
                        .getInputStream());
        } catch (IOException e) {
            System.err.println("Erro ao criar evento pelo adb");
        }
        String deviceSerialNumver = (scanner != null && scanner.hasNext()) ? scanner.next() : "";
        return deviceSerialNumver;
    }

    public String getMonth(int month) {
        String[] monthNames = {"", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto",
                "Setembro", "Outubro", "Novembro", "Dezembro"};
        return monthNames[month];
    }

    public List<String> getListDeviceSerialNumbers() {
        if (ListDeviceSerialNumbers != null) {
            return ListDeviceSerialNumbers;
        }

        Scanner scanner = null;
        try {
            scanner = new Scanner(Runtime.getRuntime().exec("cmd /c adb devices").getInputStream());
            scanner.useDelimiter("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> deviceSerialNumbers = new ArrayList<String>();
        while (scanner != null && scanner.hasNext()) {
            String Linha = scanner.next();
            if (Linha.indexOf("device\r") > -1) {
                String arrParts[] = Linha.split("\\s+");

                deviceSerialNumbers.add(arrParts[0]);
            }
            ;
        }
        ListDeviceSerialNumbers = deviceSerialNumbers;
        return deviceSerialNumbers;
    }

}
