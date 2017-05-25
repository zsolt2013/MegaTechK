package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.Json;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

   private final Logger slf4jLogger = LoggerFactory.getLogger(LoginController.class);



    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton signup;

    @FXML
    void makeLogin(ActionEvent event) throws IOException, JSONException {

        String username = user.getText();
        String pass = password.getText();


        BufferedReader streamReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("in.json"), "UTF-8"));

        Json Authenticator = new Json(streamReader);


        if ( Authenticator.authenticate(username, pass))
        //  if ( Json.readJsonq().equals();

        {
            this.slf4jLogger.info(String.format("%s has logged in.", username));


            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("feluletbe.fxml"));
            Stage main  = new  Stage();


            //  primaryStage.setTitle("Hello World");

            main.setScene(new Scene(root, 500, 775));
            main.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();

//Yamine Renri - Schwarzer regen
        }else {
            this.slf4jLogger.warn(String.format("Failed login for user %s.", username));
            System.out.println("Wrong Password ");
        }
    }



    @Override
    public   void initialize(URL url,ResourceBundle rb) {

    }
}
