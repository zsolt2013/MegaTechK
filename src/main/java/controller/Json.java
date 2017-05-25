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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

// H- 12 - 56 DBS


public class Json {
    private final JSONObject JSON;

    public Json(String Filename) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (Scanner sc = new Scanner(new FileReader(Filename))) {
            while (sc.hasNextLine())
                sb.append(sc.nextLine());
        }

        this.JSON = new JSONObject(sb.toString());
    }


    public Json(BufferedReader streamReader) throws IOException {
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        this.JSON = new JSONObject(responseStrBuilder.toString());
    }


    public boolean authenticate(String Username, String Password) {
        JSONObject UserInfo = null;
        JSONArray Users = this.JSON.getJSONArray("users");
        for (int Index = 0; Index < Users.length(); Index++) {
            Object e = Users.get(Index);

            if (!(e instanceof JSONObject))
                continue;

            JSONObject Entry = (JSONObject) e;

            if (Entry.getString("username").equals(Username)) {
                UserInfo = Entry;
                break;
            }
        }

        if (UserInfo == null)
            return false;

        try {
            return (this.hash(Password).equals(UserInfo.getString("password")));
        }
        catch (NoSuchAlgorithmException ex) {
            System.err.println("Hash error: " + ex.getMessage());
            return false;
        }
    }

    private String hash(String Input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(Input.getBytes());

        byte byteData[] = md.digest();

        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}