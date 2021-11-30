package com.ivanborodkin.controller;

import com.ivanborodkin.entity.Contract;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextField tfId;
    @FXML
    public TextField tfNumber;
    @FXML
    public TextField tfDateOfConclusion;
    @FXML
    public TextField tfDateOfLastUpdate;
    @FXML
    public TableView<Contract> tvContract;
    @FXML
    public TableColumn<Contract,Integer> colId;
    @FXML
    public TableColumn<Contract,Integer> colNumber;
    @FXML
    public TableColumn<Contract, Date> colDateOfConclusion;
    @FXML
    public TableColumn<Contract, Date> colDateOfLastUpdate;
    @FXML
    public TableColumn<Contract,Boolean> colCheckBox;
    @FXML
    public Button btnInsert;
    @FXML
    public Button btnUpdate;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnCheck;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showContract();
    }

    @FXML
    public void handleButtonAction(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnInsert) {
            insertRecord();
        } else if (actionEvent.getSource() == btnUpdate) {
            updateRecord();
        } else if (actionEvent.getSource() == btnDelete) {
            deleteRecord();
        } else if (actionEvent.getSource() == btnCheck) {
            checkBox();
        }
    }

    @FXML
    public void handleMouseAction(MouseEvent mouseEvent) {
        Contract contract = tvContract.getSelectionModel().getSelectedItem();
        tfId.setText("" + contract.getId());
        tfNumber.setText("" + contract.getNumber());
        tfDateOfConclusion.setText("" + contract.getDateOfConclusion());
        tfDateOfLastUpdate.setText("" + contract.getDateOfLastUpdate());
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "heroes123");
            return connection;
        } catch(Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }

    public ObservableList<Contract> getContractList() {
        ObservableList<Contract> contractList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM contract";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Contract contract;
            while (rs.next()) {
                contract = new Contract(
                        rs.getInt("id"),
                        rs.getInt("number"),
                        rs.getDate("date_of_conclusion"),
                        rs.getDate("date_of_last_update"),
                        rs.getBoolean("check_box"));
                contractList.add(contract);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contractList;
    }

    public void showContract() {
        ObservableList<Contract> contractList = getContractList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colDateOfConclusion.setCellValueFactory(new PropertyValueFactory<>("dateOfConclusion"));
        colDateOfLastUpdate.setCellValueFactory(new PropertyValueFactory<>("dateOfLastUpdate"));
        colCheckBox.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        tvContract.setItems(contractList);

    }

    private void insertRecord() {
        String query = "INSERT INTO contract VALUES (" +
                tfId.getText() + "," +
                tfNumber.getText() + ",'" +
                tfDateOfConclusion.getText() + "','" +
                tfDateOfLastUpdate.getText() + "');";
        executeQuery(query);
        showContract();
    }

    private void checkBox() {
        String query = "UPDATE contract SET check_box = false WHERE CURRENT_DATE - date_of_last_update > 60; ";
        executeQuery(query);
        showContract();
    }

    private void updateRecord() {
        String query = "UPDATE contract SET number = " +
                tfNumber.getText() + ", date_of_conclusion = '" +
                tfDateOfConclusion.getText() + "', date_of_last_update = '" +
                tfDateOfLastUpdate.getText() + "' WHERE id = " + tfId.getText() + " ;";
        executeQuery(query);
        showContract();
    }

    private void deleteRecord() {
        String query = "DELETE FROM contract WHERE id = " + tfId.getText() + ";";
        executeQuery(query);
        showContract();
    }

    private void executeQuery(String query) {
        Connection connection = getConnection();
        Statement st;
        try{
            st = connection.createStatement();
            st.executeUpdate(query);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
