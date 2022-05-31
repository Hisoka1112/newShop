package com.example.shop1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminMainPageConntroller {

    @FXML
    private Button mainAdminBack;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminpagebooklist;

    @FXML
    private Button adminpagepanel;

    @FXML
    void initialize() {
        adminpagepanel.setOnAction(x->{
           adminpagepanel.getScene().getWindow().hide();
           check("adminPanel.fxml");
        });
        adminpagebooklist.setOnAction(x->{
            adminpagebooklist.getScene().getWindow().hide();
            check("adminBookList.fxml");
        });
        mainAdminBack.setOnAction(x->{
            mainAdminBack.getScene().getWindow().hide();
            check("hello-view.fxml");
        });
    }
    public void check(String url){
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

