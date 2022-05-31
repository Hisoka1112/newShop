package com.example.shop1;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.shop1.DataBase.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class basketControll {

    ObservableList<Book> Books = FXCollections.observableArrayList();
    @FXML
    public TableView<Book> tableAdmin;
    @FXML
    public TableColumn<Book,Integer> idColumn;
    @FXML
    public TableColumn<Book,String> nameColumn;
    @FXML
    public TableColumn<Book, Integer> numColumn;
    @FXML
    public TableColumn<Book, BigDecimal> priceColumn;
    @FXML
    public TableColumn<Book,String> authorColumn;
    @FXML
    public TableColumn<Book,String> genreColumn;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backCode;

    @FXML
    private AnchorPane basketCode;
    @FXML
    private Button addMoneyCode;
    @FXML
    private TextField moneyCode;

    @FXML
    void initialize() throws SQLException {
        backCode.setOnAction(x->{
            backCode.getScene().getWindow().hide();
            check("Shop.fxml");
        });
        initData();
        addMoneyCode.setOnAction(x->{
           String a= moneyCode.getText();
           BigDecimal chel=new BigDecimal(a);
            try {
                DataBase.addMoney(chel,HelloController.id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }
    public void check(String url) {
        FXMLLoader chel = new FXMLLoader();

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
    public void initData() throws SQLException {
        Books.clear();
        Books.addAll(DataBase.GetBooksByUser(HelloController.id));
        idColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Book, BigDecimal>("price"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
        numColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("count"));
        tableAdmin.setItems(Books);
    }
}
