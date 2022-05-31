package com.example.shop1;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.shop1.DataBase.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class regController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField CountryField;

    @FXML
    private CheckBox FemaleField;

    @FXML
    private CheckBox MaleField;

    @FXML
    private Button RegestrationField;

    @FXML
    private Button backField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordFieldd;

    @FXML
    void initialize() {
        RegestrationField.setOnAction(x->{
            try {
                if(passwordField.getText().equals(passwordFieldd.getText())&&loginField.getText().length()>0
                        &&passwordField.getText().length()>0&&emailField.getText().length()>0&&CountryField.getText().length()>0&&
                DataBase.checkBase(loginField.getText(),passwordField.getText())){

                    User vova = new User();
                    vova.setLogin(loginField.getText());
                    vova.setPassword(passwordField.getText());
                    vova.setEmail(emailField.getText());
                    vova.setCountry(CountryField.getText());
                    vova.setGender(Gender.MALE);
                    try {
                        DataBase.AddUser(vova);

                        RegestrationField.getScene().getWindow().hide();
                        changeScene("hello-view.fxml");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        backField.setOnAction(x->{
            backField.getScene().getWindow().hide();
            changeScene("hello-view.fxml");
        });

    }
    public void changeScene(String url){
        FXMLLoader chel=new FXMLLoader();

        chel.setLocation(getClass().getResource(url));
        try {
            chel.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = chel.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

