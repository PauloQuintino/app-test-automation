package utils;

import Core.DriverFactory;
import beans.LoggerHelper;
import com.sun.javafx.scene.traversal.Direction;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UtilsMobile {

    public UtilsMobile() {
    }

    public static List<String> ListDeviceSerialNumbers = null;
    public LoggerHelper logger = new LoggerHelper(UtilsMobile.class);


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

    public void swipeScreen(Direction dir) {
        logger.info("swipeScreen(): Direction: '" + dir + "'");

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 1800; // ms

        final int PRESS_TIME = 900; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = DriverFactory.getDriver().manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(DriverFactory.getDriver())
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public void swipeScreen(int startX, int endX, int startY, int endY) {
        AndroidDriver<MobileElement> driver = DriverFactory.getDriver();
        new TouchAction(driver).press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(endX, endY))
                .release().perform();
    }

    public void swipeElement(AndroidElement elemento, Direction direction, long duration) {

        Dimension size = null;
        Point location = null;
        AndroidDriver<MobileElement> driver = null;
        size = elemento.getSize();
        location = elemento.getLocation();

        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;

        switch (direction.toString()) {
            case "LEFT":
                startY = (int) location.getY() + (int) (size.height / 2);
                startX = (int) location.getX() + (int) (size.width * 0.70);
                endX = (int) location.getX() + (int) (size.width * 0.20);
                endY = startY;
                break;

            case "RIGHT":
                startY = (int) location.getY() + (int) (size.height / 2);
                startX = (int) location.getX() + (int) (size.width * 0.25);
                endX = (int) location.getX() + (int) (size.width * 0.75);
                endY = startY;
                break;

            case "UP":
                endY = (int) location.getY() + (int) (size.height * 0.80);
                startY = (int) (size.height * 0.20);
                startX = (int) location.getX() + (size.width / 2);
                endX = startX;
                break;

            case "DOWN":
                startY = (int) location.getY() + (int) (size.height * 0.80);
                endY = (int) location.getY() + (int) (size.height * 0.20);
                startX = (int) location.getX() + (int) (size.width / 2);
                endX = startX;
                break;
        }

        TouchAction touchAction = new TouchAction(DriverFactory.getDriver());

        touchAction.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
    }

    public void swipeToElement(MobileElement element, Direction direction, int timeout) {
        boolean textFound = false;
        int counter = 0;

        while (!textFound && counter <= timeout) {

            if (elementExists(element, 5)) {
                textFound = true;
                break;
            } else {
                swipeScreen(direction);
                counter++;
            }
        }
    }

    public void swipeLongPress(AndroidElement element, String direction) {

        Dimension size = null;
        Point location = null;
        AndroidDriver<MobileElement> driver = null;
        size = element.getSize();
        location = element.getLocation();
        driver = DriverFactory.getDriver();

        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;

        switch (direction) {
            case "LEFT":
                startY = location.getY() + size.height / 2;
                startX = location.getX() + (int) ((double) size.width * 0.7);
                System.out.println("startX => " + startX);
                endX = 200;
                endY = startY;
                break;
            case "RIGHT":
                startY = location.getY() + size.height / 2;
                startX = location.getX() + (int) ((double) size.width * 0.25);
                //endX = location.getX() + (int)((double)size.width * 0.75);
                System.out.println("startX => " + startX);
                endX = location.getX() + 450;
                System.out.println("endX => " + endX);
                endY = startY;
                break;
            case "UP":
                endY = location.getY() + (int) ((double) size.height * 0.8);
                startY = (int) ((double) size.height * 0.2);
                startX = location.getX() + size.width / 2;
                endX = startX;
                break;
            case "DOWN":
                startY = location.getY() + (int) ((double) size.height * 0.8);
                endY = location.getY() + (int) ((double) size.height * 0.2);
                startX = location.getX() + size.width / 2;
                endX = startX;
        }

        new TouchAction(driver).longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();

    }


    public boolean waitUntilsExist(AndroidElement element) {

        boolean elementIsFound = false;
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 10);

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            elementIsFound = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return elementIsFound;
    }

    public static void zeraImplicityWaitsProperties() {

        try {
            DriverFactory.getDriver().manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static boolean elementExists(MobileElement element, long timeout) {

        boolean result = false;

        try {

            zeraImplicityWaitsProperties();

            WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeout, 0 );
            wait.until(ExpectedConditions.visibilityOf(element));

            result = true;

        } catch (TimeoutException e) {

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return result;

    }


}
