package com.example.shop1;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.example.shop1.DataBase.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminPanel {
    @FXML
    public Button mainApplication;
    @FXML
    public TextField mainId;
    @FXML
    public TextField mainPrice;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminCreate;

    @FXML
    private Button adminback;

    @FXML
    private TextField pageAuthor;
    @FXML
    private ComboBox<String> mainPageGenre;
    @FXML
    private TextField pagename;

    @FXML
    private TextField pageprice;

    @FXML
    private TextField CountCode;
    @FXML
    private Label erorCode;
    @FXML
    private TextField CountCodBook;
    @FXML
    void initialize() {
        List<String>qwe= List.of("Anime","Drama","Action");
        mainPageGenre.getItems().addAll(qwe);
        adminback.setOnAction(x->{
            adminback.getScene().getWindow().hide();
            check("mainAdmin.fxml");
        });
        adminCreate.setOnAction(x->{
           String a= pagename.getText();
           BigDecimal s=new BigDecimal(pageprice.getText());
           String q=pageAuthor.getText();
           String w=mainPageGenre.getValue();
            Book masha=new Book(a,s,q,w,Integer.valueOf(CountCodBook.getText()));
            try {
                DataBase.AddBook(masha);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        mainApplication.setOnAction(x->{
            BigDecimal price = null;
            if(!mainId.getText().equals("")&&!CountCode.getText().equals("")) {
                if(mainPrice.getText().equals("")){
                    try {
                        price =DataBase.PriceRedact(Integer.parseInt(mainId.getText()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    if(mainPrice.getText().matches("\\d+")&&Integer.valueOf(mainPrice.getText())>0) {
                        price = new BigDecimal(mainPrice.getText());

                    }
                }
                int s = Integer.parseInt(mainId.getText());
                if(CountCode.getText().matches("\\d+")&&Integer.valueOf(CountCode.getText())>0) {
                    int count = Integer.valueOf(CountCode.getText());
                    try {
                        DataBase.updateBook(price, s, count);

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else{
                erorCode.setText("V TAVERNY");
            }
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

