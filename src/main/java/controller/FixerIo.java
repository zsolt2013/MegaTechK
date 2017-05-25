package controller;

/*

import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Scanner;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

*/

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Scanner;


/**
 * FixerIO - Currency - Zsoltisoft
 * 2017.05.25
 */
public class FixerIo {
    private JSONObject DataSource;
    private String JSON;

    public FixerIo(String BaseCurrency) throws JSONException, IOException  {
        String ApiUrl = String.format("http://api.fixer.io/latest?base=%s", BaseCurrency);
        this.JSON = this.DownloadData(ApiUrl);

        this.DataSource = new JSONObject(JSON);
        this.DataSource.getJSONObject("rates").put(BaseCurrency,1d);
    }

    public FixerIo(String BaseCurrency, String Filename) throws JSONException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new FileReader(Filename))) {
            while (sc.hasNextLine())
                sb.append(sc.nextLine());
        }

        this.DataSource = new JSONObject(sb.toString());
        this.DataSource.getJSONObject("rates").put(BaseCurrency,1d);

        //System.out.println(this.DataSource.toString(4));
    }

    public FixerIo(String BaseCurrency, BufferedReader streamReader) throws JSONException, IOException {
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        this.DataSource = new JSONObject(responseStrBuilder.toString());
        this.DataSource.getJSONObject("rates").put(BaseCurrency,1d);

        //System.out.println(this.DataSource.toString(4));
        //System.out.println(this.DataSource.toString(4));
    }

    public void Save(PrintStream p) {
        p.print(this.JSON);
        p.flush();
    }

    public Collection<String> GetCurrencies() throws JSONException {

        //  System.out.println(this.DataSource.getJSONObject("rates").keySet());

        return this.DataSource.getJSONObject("rates").keySet();
    }

    private String DownloadData(String ApiUrl) throws MalformedURLException, IOException {

        StringBuilder Result = new StringBuilder();

        URL u = new URL(ApiUrl);
        try (Scanner sc = new Scanner(u.openStream())) {
            while (sc.hasNextLine())
                Result.append(sc.nextLine());
        }

        return Result.toString();
    }

    public double ConvertCurrency(double Amount, String FromCurrency, String ToCurrency) {
        try {
            double From = this.GetCurrencyRate(FromCurrency);
            double To = this.GetCurrencyRate(ToCurrency);
            return Amount / From * To;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public double GetCurrencyRate(String Currency) throws JSONException{
        return this.DataSource.getJSONObject("rates").getDouble(Currency);
    }
}
