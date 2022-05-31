package com.example.shop1;

import com.example.shop1.DataBase.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class ShopContoller {
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
    private Button backCode;
    @FXML
    private Button PersonalCode;

    @FXML
    private Label MoneyCodeShop;


    public void initialize() throws SQLException {
       BigDecimal a= DataBase.shopMoney(HelloController.id);
       MoneyCodeShop.setText(a+" ");
        initData();
        addButtonToTable();
        initData();
        backCode.setOnAction(x->{
            backCode.getScene().getWindow().hide();
            changeScene("hello-view.fxml");
        });
        PersonalCode.setOnAction(x->{
            PersonalCode.getScene().getWindow().hide();
            changeScene("basketUser.fxml");
        });

    }
    public  void changeScene(String url ) {
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
    private void addButtonToTable() {
        TableColumn<Book, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<Book, Void>() {

                    private final Button btn = new Button("buy");


                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Book book = getTableView().getItems().get(getIndex());
                            if(book.getCount()>0) {
                                try {
                                    if(DataBase.shopMoney(HelloController.id).doubleValue()>=book.getPrice().doubleValue()){
                                        DataBase.BuyUser(book.getId(),HelloController.id);
                                        DataBase.UpdateBookCount(book.getId());
                                        DataBase.deleteMoney(book.getPrice(),HelloController.id);
                                    }
                                    initData();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        tableAdmin.getColumns().add(colBtn);

    }
    public void initData() throws SQLException {
        Books.clear();
        Books.addAll(DataBase.ListBook());
        idColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Book, BigDecimal>("price"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
        numColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("count"));
        BigDecimal a= DataBase.shopMoney(HelloController.id);
        MoneyCodeShop.setText(a+" ");
        tableAdmin.setItems(Books);
    }
}
