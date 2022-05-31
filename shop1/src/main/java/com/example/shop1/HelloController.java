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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
    static int id=0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button MainPageAuthorizationButton;

    @FXML
    private TextField MainPageLogin;

    @FXML
    private PasswordField MainPagePassword;

    @FXML
    private Button MainPageRegectrationButton;

    @FXML
    void initialize() {
        MainPageAuthorizationButton.setOnAction(x->{
            String role="";
            try {
                id=DataBase.getId(MainPageLogin.getText(),MainPagePassword.getText());
              role=  DataBase.checkUsersRole(MainPageLogin.getText(),MainPagePassword.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            boolean a = false;
            try {
                a = DataBase.AddBse(MainPageLogin.getText(), MainPagePassword.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (a==true&& role.equals("admin") ) {
                    MainPageAuthorizationButton.getScene().getWindow().hide();
                   changeScene("mainAdmin.fxml");
                }
            else if(a==true&& role.equals("user")){
                MainPageAuthorizationButton.getScene().getWindow().hide();
                changeScene("Shop.fxml");
            }
            });
        MainPageRegectrationButton.setOnAction(x->{
            MainPageRegectrationButton.getScene().getWindow().hide();
            changeScene("regestration.fxml");
        });

    }
    public  void changeScene(String url ){
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
