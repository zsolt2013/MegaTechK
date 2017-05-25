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

/**
 * Created by zsolt on 5/15/17.
 */
public class FeluletController implements Initializable {

        @FXML
        private JFXButton calculator;

        @FXML
        private JFXButton currency;

        @FXML
        void makeCalculator(ActionEvent event) throws IOException {

                //System.out.println("Calculator ");

                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ui.fxml"));
                Stage main  = new  Stage();


                //  primaryStage.setTitle("Hello World");
                Scene scene = new Scene(root);
             //   main.setScene(new Scene(root, 500, 775));
                main.show();

                ((Node)(event.getSource())).getScene().getWindow().hide();

        }

        @FXML
        void makeCurrency(ActionEvent event) throws IOException {
                System.out.println(" Currency ");

                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("bake.fxml"));
               // Stage main  = new  Stage();


                //  primaryStage.setTitle("Hello World");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
               // main.setScene(new Scene(root, 300, 275));
                stage.show();

                ((Node)(event.getSource())).getScene().getWindow().hide();


        }
        /*

        @Override
        public void initialize(URL location, ResourceBundle resources) {

*/
                @Override
                public   void initialize(URL url,ResourceBundle rb) {

                }
        }

