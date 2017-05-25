package view;
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

import controller.FixerIo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by zsolt on 5/18/17.
 */
public class FixerController implements Initializable {
    private Logger logger = LoggerFactory.getLogger(FixerController.class);

    ///explosion!!!!!



    @FXML
    private ComboBox fromCurrency;

    @FXML
    private ComboBox fromCurrency2;

    @FXML
    private TextField in;

    @FXML
    private Label out;

    private FixerIo fixer;



    public FixerController() throws IOException {



    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.fixer = new FixerIo("EUR");
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("currency.json"), "UTF-8"));
            if (HasNetConnection()) {
                this.fixer = new FixerIo("EUR");
//                try (PrintStream p = new PrintStream(getClass().getClassLoader().getResource("currency.json").getPath())) {
//                    this.fixer.Save(p);
//                }
            }else
                this.fixer = new FixerIo("EUR", streamReader);






            ObservableList<String> e = FXCollections.observableArrayList(this.fixer.GetCurrencies());
            this.fromCurrency.setItems(e);
            this.fromCurrency.getSelectionModel().select(0);

            ObservableList<String> ee = FXCollections.observableArrayList(this.fixer.GetCurrencies());
            this.fromCurrency2.setItems(ee);

        this.fromCurrency2.getSelectionModel().select(0);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void onKeyReleased(KeyEvent e) {
        this.UpdateResult();
    }

    public void onMouseClicked(ActionEvent e) {
        this.UpdateResult();
    }

    private void UpdateResult() {
        if (this.in.getText().isEmpty()) {
            this.out.setText("");
            return;
        }

        double input = 0;

        try {
            input = Double.parseDouble(this.in.getText());
        }
        catch (NumberFormatException ex) {
            this.out.setText("Do not use characters ! Thanks in advance");
            return;
        }


        this.logger.info(
                String.format(
                        "Converted %f %s to %s that is %f.",
                        input,
                        this.fromCurrency.getSelectionModel().getSelectedItem().toString(),
                        this.fromCurrency2.getSelectionModel().getSelectedItem().toString(),
                        this.fixer.ConvertCurrency(
                                input,
                                this.fromCurrency.getSelectionModel().getSelectedItem().toString(),
                                this.fromCurrency2.getSelectionModel().getSelectedItem().toString()
                        )
                )
        );

        this.out.setText(
                String.valueOf(
                        this.fixer.ConvertCurrency(
                                input,
                                this.fromCurrency.getSelectionModel().getSelectedItem().toString(),
                                this.fromCurrency2.getSelectionModel().getSelectedItem().toString()
                        )
                )
        );
    }


    private static boolean HasNetConnection() {
        try {
            URL u = new URL("http://google.com");
            // URL u = new URL("http://google.com");
            u.openStream().close();

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
