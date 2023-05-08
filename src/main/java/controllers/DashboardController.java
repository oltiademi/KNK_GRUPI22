package controllers;

import com.example.knk_grupi22.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
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
    private ToggleGroup gjiniaToggleGroup;

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
    private Statement statement = null;
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
        comboBox_Titulli.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if(newValue.equals("Baçelor(BSc)")) {
                    comboBox_Drejtimi.getItems().clear();
                    comboBox_Drejtimi.getItems().addAll(drejtimetBSc);
                    comboBox_Drejtimi.setValue("Inxhinieri Kompjuterike dhe Softuerike");
                } else if(newValue.equals("Master(MSc)")){
                    comboBox_Drejtimi.getItems().clear();
                    comboBox_Drejtimi.getItems().addAll(drejtimetMsc);
                    comboBox_Drejtimi.setValue("Inxhinieri Kompjuterike dhe Softuerike");
                } else if(newValue.equals("Doktoraturë(PHD)")){
                    comboBox_Drejtimi.getItems().clear();
                    comboBox_Drejtimi.getItems().addAll(drejtimetPHD);
                    comboBox_Drejtimi.setValue("Inxhinieri Kompjuterike dhe Softuerike");
                } else {
                    clearEmployed();
                }
            }
            if(newValue == null){
                clearEmployed();
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
            }
        }
    }

    public void switchForm(ActionEvent actionEvent) {
        if (actionEvent.getSource() == dashboard_Btn) {
            dashboardPane.setVisible(true);
            managePane.setVisible(false);
            dashboard_Btn.setStyle("-fx-background-color:  linear-gradient(to left, #11998e, #38ef7d); -fx-cursor: hand;");
            dashboard_Btn1.setStyle("-fx-background-color:  linear-gradient(to left, #11998e, #38ef7d); -fx-cursor: hand;");
            manage_Btn.setStyle("-fx-background-color:  linear-gradient(to left, #373B44, #4286f4); -fx-cursor: hand;");
            manage_Btn1.setStyle("-fx-background-color:  linear-gradient(to left, #373B44, #4286f4); -fx-cursor: hand;");

        } else if (actionEvent.getSource() == manage_Btn) {
            dashboardPane.setVisible(false);
            managePane.setVisible(true);
            showEmployedListData();
            manage_Btn.setStyle("-fx-background-color:  linear-gradient(to left, #11998e, #38ef7d); -fx-cursor: hand;");
            manage_Btn1.setStyle("-fx-background-color:  linear-gradient(to left, #11998e, #38ef7d); -fx-cursor: hand;");
            dashboard_Btn.setStyle("    -fx-background-color:  linear-gradient(to left, #373B44, #4286f4); -fx-cursor: hand;");
            dashboard_Btn1.setStyle("    -fx-background-color:  linear-gradient(to left, #373B44, #4286f4); -fx-cursor: hand;");

        } else if (actionEvent.getSource() == dashboard_Btn1) {
            dashboardPane.setVisible(true);
            managePane.setVisible(false);
            dashboard_Btn.setStyle("-fx-background-color:  linear-gradient(to left, #11998e, #38ef7d); -fx-cursor: hand;");
            dashboard_Btn1.setStyle("-fx-background-color:  linear-gradient(to left, #11998e, #38ef7d); -fx-cursor: hand;");
            manage_Btn.setStyle("-fx-background-color:  linear-gradient(to left, #373B44, #4286f4); -fx-cursor: hand;");
            manage_Btn1.setStyle("-fx-background-color:  linear-gradient(to left, #373B44, #4286f4); -fx-cursor: hand;");

        } else if (actionEvent.getSource() == manage_Btn1) {
            dashboardPane.setVisible(false);
            managePane.setVisible(true);
            showEmployedListData();
            manage_Btn.setStyle("-fx-background-color:  linear-gradient(to left, #11998e, #38ef7d); -fx-cursor: hand;");
            manage_Btn1.setStyle("-fx-background-color:  linear-gradient(to left, #11998e, #38ef7d); -fx-cursor: hand;");
            dashboard_Btn.setStyle("    -fx-background-color:  linear-gradient(to left, #373B44, #4286f4); -fx-cursor: hand;");
            dashboard_Btn1.setStyle("    -fx-background-color:  linear-gradient(to left, #373B44, #4286f4); -fx-cursor: hand;");


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

        if (employed.getGjinia().equals("Mashkull")) {
            radio_Mashkull.setSelected(true);
        } else if (employed.getGjinia().equals("Femër")) {
            radio_Femer.setSelected(true);
        } else {
            radio_Other.setSelected(true);
        }
    }
    public void addEmployed() throws SQLException {
        String sql = "INSERT INTO employed (emri,mbiemri,gjinia,titulli,drejtimi,profesioni,kompania) VALUES (?,?,?,?,?,?,?)";
        String ekziston = "SELECT emri, mbiemri, gjinia, titulli, drejtimi, profesioni, kompania FROM employed WHERE emri = '" + tf_Emri.getText() + "' AND mbiemri = '" + tf_Mbiemri.getText()+"'";
        Alert alert;
        connection = ConnectionUtil.getConnection();

        try{
            if(tf_Emri.getText().isEmpty()
                    || tf_Mbiemri.getText().isEmpty()
                    || tf_Profesioni.getText().isEmpty()
                    || tf_Kompania.getText().isEmpty()
                    || comboBox_Titulli.getSelectionModel().getSelectedItem() == null
                    || comboBox_Drejtimi.getSelectionModel().getSelectedItem() == null
                    || radio_Mashkull == null
                    || radio_Femer == null
                    || radio_Other == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Fill all blank fields!");
                alert.showAndWait();
            }
            else {
                preparedStatement = connection.prepareStatement(ekziston);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("User exists!");
                    alert.showAndWait();
                }else {
                    String gjinia = "";
                    RadioButton selectedRadioButton = (RadioButton) gjiniaToggleGroup.getSelectedToggle();
                    if (selectedRadioButton != null) {
                        gjinia = selectedRadioButton.getText();

                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, tf_Emri.getText());
                        preparedStatement.setString(2, tf_Mbiemri.getText());
                        preparedStatement.setString(3, gjinia);
                        preparedStatement.setString(4, comboBox_Titulli.getSelectionModel().getSelectedItem());
                        preparedStatement.setString(5, comboBox_Drejtimi.getSelectionModel().getSelectedItem());
                        preparedStatement.setString(6, tf_Profesioni.getText());
                        preparedStatement.setString(7, tf_Kompania.getText());
                        preparedStatement.executeUpdate();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Added succesfully!");
                        alert.showAndWait();
                        showEmployedListData();
                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void clearEmployed(){
        tf_Emri.setText("");
        tf_Mbiemri.setText("");
        gjiniaToggleGroup.selectToggle(null);

        comboBox_Drejtimi.setValue(null);
        comboBox_Titulli.setValue(null);
        tf_Profesioni.setText("");
        tf_Kompania.setText("");
    }
    public void deleteEmployed() throws SQLException {
        String sql = "DELETE FROM employed WHERE emri = '" + tf_Emri.getText() + "' AND mbiemri = '" + tf_Mbiemri.getText() + "'";
        connection = ConnectionUtil.getConnection();

        RadioButton selectedRadioButton = (RadioButton) gjiniaToggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            if(tf_Emri.getText().isEmpty()
                    || tf_Mbiemri.getText().isEmpty()
                    || tf_Profesioni.getText().isEmpty()
                    || tf_Kompania.getText().isEmpty()
                    || comboBox_Titulli.getSelectionModel().getSelectedItem() == null
                    || comboBox_Drejtimi.getSelectionModel().getSelectedItem() == null
                    || selectedRadioButton.equals(null)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Fill all blank fields!");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMATION");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this user?");
                Optional<ButtonType> optionalButtonType = alert.showAndWait();
                if(optionalButtonType.get().equals(ButtonType.OK)){
                    statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("INFORMATION");
                    alert1.setHeaderText(null);
                    alert1.setContentText("User deleted succesfully!");
                    alert1.showAndWait();
                    showEmployedListData();
                    clearEmployed();
                }
            }
        }

    }
    public void updateEmployed() throws SQLException {
        String gjinia;
        RadioButton selectedRadioButton = (RadioButton) gjiniaToggleGroup.getSelectedToggle();
        gjinia = selectedRadioButton.getText();

        if (selectedRadioButton != null) {
            String sql = "UPDATE employed SET "
                    + " gjinia = '" + gjinia
                    + "', titulli = '" + comboBox_Titulli.getSelectionModel().getSelectedItem()
                    + "', drejtimi = '" + comboBox_Drejtimi.getSelectionModel().getSelectedItem()
                    + "', profesioni = '" + tf_Profesioni.getText()
                    + "', kompania = '" + tf_Kompania.getText() + "' WHERE emri = '" + tf_Emri.getText()
                    + "' AND mbiemri = '" + tf_Mbiemri.getText() + "'";
            connection = ConnectionUtil.getConnection();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to update this user?");
            Optional<ButtonType> optionalButtonType = alert.showAndWait();
            if(optionalButtonType.get().equals(ButtonType.OK)) {

                statement = connection.createStatement();
                statement.executeUpdate(sql);

                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("INFORMATION");
                alert1.setHeaderText(null);
                alert1.setContentText("User updated succesfully!");
                alert1.showAndWait();
                showEmployedListData();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmployedListData();
        comboBox_Titulli.setStyle("-fx-font-size: 15px;");
        listaTitujve();
        listaDrejtimev();
        gjiniaToggleGroup = new ToggleGroup();
        radio_Femer.setToggleGroup(gjiniaToggleGroup);
        radio_Mashkull.setToggleGroup(gjiniaToggleGroup);
        radio_Other.setToggleGroup(gjiniaToggleGroup);
    }
}