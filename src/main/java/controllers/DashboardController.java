package controllers;

import com.example.knk_grupi22.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Employed;
import service.ConnectionUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {
    @FXML
    private ComboBox<String> comboBox_Drejtimi;

    @FXML
    private ComboBox<String> comboBox_Titulli;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Button dashboard_Btn;

    @FXML
    private Button dashboard_Btn1;

    @FXML
    private Button fshijButoni;

    @FXML
    private Button logout_Btn;

    @FXML
    private Button logout_Btn1;

    @FXML
    private AnchorPane managePane;

    @FXML
    private Button manage_Btn;

    @FXML
    private Button manage_Btn1;

    @FXML
    private Button perditesoButoni;

    @FXML
    private RadioButton radio_Femer;

    @FXML
    private RadioButton radio_Mashkull;

    @FXML
    private RadioButton radio_Other;

    @FXML
    private Button shtoButoni;
    @FXML
    private TableView<Employed> tableView_Employed;

    @FXML
    private TableColumn<Employed, String> tableView_Drejtimi;

    @FXML
    private TableColumn<Employed, String> tableView_Emri;

    @FXML
    private TableColumn<Employed, String> tableView_Gjinia;

    @FXML
    private TableColumn<Employed, String> tableView_Kompania;

    @FXML
    private TableColumn<Employed, String> tableView_Mbiemri;

    @FXML
    private TableColumn<Employed, String> tableView_Profesioni;

    @FXML
    private TableColumn<Employed, String> tableView_Titulli;

    @FXML
    private TextField tf_Emri;

    @FXML
    private TextField tf_Kompania;

    @FXML
    private TextField tf_Mbiemri;

    @FXML
    private TextField tf_Profesioni;
    private String[] titujt= {"Baçelor(BSc)", "Master(MSc)", "Doktoraturë(PHD)"};
    private String[] drejtimetBSc = {"Inxhinieri Kompjuterike dhe Softuerike", "Elektronikë, Automatikë dhe Robotikë", "Teknologjite e Informacionit dhe Komunikimit", "Elektroenergjetike"};
    private String[] drejtimetMsc = {"Inxhinieri Kompjuterike dhe Softuerike", "Elektronikë, Automatikë dhe Robotikë", "Teknologjite e Informacionit dhe Komunikimit"};
    private String[] drejtimetPHD = {"Inxhinieri Kompjuterike dhe Softuerike"};
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void listaTitujve(){
        ArrayList<String> listaTitujv = new ArrayList<>();
        for(String i: titujt){
            listaTitujv.add(i);
        }
        ObservableList ob = FXCollections.observableArrayList(listaTitujv);
        comboBox_Titulli.setItems(ob);
    }
    public void listaDrejtimev(){
        ArrayList<String> listaDrejtimev = new ArrayList<>();
        comboBox_Titulli.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Baçelor(BSc)")) {
                comboBox_Drejtimi.getItems().clear();
                comboBox_Drejtimi.getItems().addAll(drejtimetBSc);
            } else if(newValue.equals("Master(MSc)")){
                comboBox_Drejtimi.getItems().clear();
                comboBox_Drejtimi.getItems().addAll(drejtimetMsc);
            }else{
                comboBox_Drejtimi.getItems().clear();
                comboBox_Drejtimi.getItems().addAll(drejtimetPHD);
            }
        });
    }
    public void logout() throws IOException {
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("Are you sure you want to log out?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                // Create a FXMLLoader for the dashboard.fxml file
                FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
                Parent dashboardRoot = fxmlLoader1.load();

                // Create a Scene for the dashboard
                Scene scene = new Scene(dashboardRoot, 637, 425);

                // Create a Stage for the dashboard
                Stage stage = new Stage();
                stage.setScene(scene);

                Node source = (Node) logout_Btn;
                // Hide the current window
                Stage currentStage = (Stage) source.getScene().getWindow();
                currentStage.hide();

                // Show the dashboard window
                stage.show();
            } else {
                return;
            }
        }
    }

    public void switchForm(ActionEvent actionEvent) {
        if (actionEvent.getSource() == dashboard_Btn) {
            dashboardPane.setVisible(true);
            managePane.setVisible(false);
        } else if (actionEvent.getSource() == manage_Btn) {
            dashboardPane.setVisible(false);
            managePane.setVisible(true);
            showEmployedListData();
        } else if (actionEvent.getSource() == dashboard_Btn1) {
            dashboardPane.setVisible(true);
            managePane.setVisible(false);
        } else if (actionEvent.getSource() == manage_Btn1) {
            dashboardPane.setVisible(false);
            managePane.setVisible(true);
            showEmployedListData();
        }
    }

    public ObservableList<Employed> showEmployedList() {
        ObservableList<Employed> employedList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM employed";


        try {
            Employed employed;
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employed = new Employed(resultSet.getString("emri"),
                        resultSet.getString("mbiemri"),
                        resultSet.getString("gjinia"),
                        resultSet.getString("titulli"),
                        resultSet.getString("drejtimi"),
                        resultSet.getString("profesioni"),
                        resultSet.getString("kompania"));

                employedList.add(employed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employedList;
    }

    private ObservableList<Employed> empList;

    public void showEmployedListData() {
        empList = showEmployedList();

        tableView_Emri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        tableView_Mbiemri.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        tableView_Gjinia.setCellValueFactory(new PropertyValueFactory<>("gjinia"));
        tableView_Titulli.setCellValueFactory(new PropertyValueFactory<>("titulli"));
        tableView_Drejtimi.setCellValueFactory(new PropertyValueFactory<>("drejtimi"));
        tableView_Profesioni.setCellValueFactory(new PropertyValueFactory<>("profesioni"));
        tableView_Kompania.setCellValueFactory(new PropertyValueFactory<>("kompania"));

        tableView_Employed.setItems(empList);
    }
    public void showDataOnTextfields(){
        Employed employed = tableView_Employed.getSelectionModel().getSelectedItem();
        int n = tableView_Employed.getSelectionModel().getSelectedIndex();

        if((n-1) < -1){
            return;
        }

        tf_Emri.setText(String.valueOf(employed.getEmri()));
        tf_Mbiemri.setText(String.valueOf(employed.getMbiemri()));
        tf_Kompania.setText(String.valueOf(employed.getKompania()));
        tf_Profesioni.setText(String.valueOf(employed.getProfesioni()));
        comboBox_Drejtimi.setValue(String.valueOf(employed.getDrejtimi()));
        comboBox_Titulli.setValue(String.valueOf(employed.getTitulli()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmployedListData();
        comboBox_Titulli.setStyle("-fx-font-size: 15px;");
        listaTitujve();
        listaDrejtimev();
    }
}
