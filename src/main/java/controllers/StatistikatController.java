package controllers;

import com.example.knk_grupi22.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.ConnectionUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class StatistikatController implements Initializable {
    @FXML
    private Label bachelor;

    @FXML
    private Label bachelor_label;

    @FXML
    private Button dashboard_Btn;

    @FXML
    private Label doktorature;

    @FXML
    private PieChart drejtimiPieChart;

    @FXML
    private Label ear_bachelor;

    @FXML
    private Label ear_bsc_label;

    @FXML
    private Label ear_master;

    @FXML
    private Label ear_msc_label;

    @FXML
    private Label elektro_label;

    @FXML
    private Label elektroenergjetike_bachelor;

    @FXML
    private Label iks_bachelor;

    @FXML
    private Label iks_bsc_label;

    @FXML
    private Label iks_master;

    @FXML
    private Label iks_msc_label;

    @FXML
    private RadioButton language_AL_button;

    @FXML
    private RadioButton language_EN_button;

    @FXML
    private Button logout_Btn;

    @FXML
    private Button manage_Btn;

    @FXML
    private Label master;

    @FXML
    private Label master_label;

    @FXML
    private Label phd_label;

    @FXML
    private Button statistics_Btn;

    @FXML
    private Label tik_bachelor;

    @FXML
    private Label tik_bsc_label;

    @FXML
    private Label tik_master;

    @FXML
    private Label tik_msc_label;

    @FXML
    private PieChart titulliPieChart;

    @FXML
    private Label welcome_label;

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void logout() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("CONFIRMATION");
        alert.setContentText("Are you sure you want to log out?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            Parent dashboardRoot = fxmlLoader1.load();
            Scene scene = new Scene(dashboardRoot, 637, 425);
            Stage stage = new Stage();
            stage.setScene(scene);
            Node source = logout_Btn;
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.hide();
            stage.show();
        }
    }
    public void switchForm(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == dashboard_Btn) {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
            Parent dashboardRoot = fxmlLoader1.load();
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.getScene().setRoot(dashboardRoot);
            currentStage.show();
        } else if(actionEvent.getSource() == manage_Btn){
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/view/manage.fxml"));
            Parent dashboardRoot = fxmlLoader1.load();
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.getScene().setRoot(dashboardRoot);
            currentStage.show();
        }
    }
    public void bachelor() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Baçelor(BSc)'";
        connection = ConnectionUtil.getConnection();
        int totalStudents = 0;
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            totalStudents = resultSet.getInt("COUNT(id)");
        }
        bachelor.setText(String.valueOf(totalStudents));
    }
    public void master() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Master(MSc)'";
        connection = ConnectionUtil.getConnection();
        int totalStudents = 0;
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            totalStudents = resultSet.getInt("COUNT(id)");
        }
        master.setText(String.valueOf(totalStudents));
    }
    public void doktorature() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Doktoraturë(PHD)'";
        connection = ConnectionUtil.getConnection();
        int totalStudents = 0;
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            totalStudents = resultSet.getInt("COUNT(id)");
        }
        doktorature.setText(String.valueOf(totalStudents));
    }
    public void iks_bachelor() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Baçelor(BSc)' AND drejtimi = 'Inxhinieri Kompjuterike dhe Softuerike'";
        connection = ConnectionUtil.getConnection();
        int totalStudents = 0;
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            totalStudents = resultSet.getInt("COUNT(id)");
        }
        iks_bachelor.setText(String.valueOf(totalStudents));
    }
    public void iks_master() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Master(MSc)' AND drejtimi = 'Inxhinieri Kompjuterike dhe Softuerike'";
        connection = ConnectionUtil.getConnection();
        int totalStudents = 0;
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            totalStudents = resultSet.getInt("COUNT(id)");
        }
        iks_master.setText(String.valueOf(totalStudents));
    }
    public void ear_bachelor() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Baçelor(BSc)' AND drejtimi = 'Elektronikë, Automatikë dhe Robotikë'";
        connection = ConnectionUtil.getConnection();
        int totalStudents = 0;
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            totalStudents = resultSet.getInt("COUNT(id)");
        }
        ear_bachelor.setText(String.valueOf(totalStudents));
    }
    public void ear_master() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Master(MSc)' AND drejtimi = 'Elektronikë, Automatikë dhe Robotikë'";
        connection = ConnectionUtil.getConnection();
        int totalStudents = 0;
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            totalStudents = resultSet.getInt("COUNT(id)");
        }
        ear_master.setText(String.valueOf(totalStudents));
    }
    public void tik_bachelor() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Baçelor(BSc)' AND drejtimi = 'Teknologjite e Informacionit dhe Komunikimit'";
        connection = ConnectionUtil.getConnection();
        int totalStudents = 0;
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            totalStudents = resultSet.getInt("COUNT(id)");
        }
        tik_bachelor.setText(String.valueOf(totalStudents));
    }
    public void tik_master() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Master(MSc)' AND drejtimi = 'Teknologjite e Informacionit dhe Komunikimit'";
        connection = ConnectionUtil.getConnection();
        int totalStudents = 0;
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            totalStudents = resultSet.getInt("COUNT(id)");
        }
        tik_master.setText(String.valueOf(totalStudents));
    }
    public void elektronergjetike_bachelor() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employed WHERE titulli = 'Baçelor(BSc)' AND drejtimi = 'Elektroenergjetikë'";
        connection = ConnectionUtil.getConnection();
        int totalStudents = 0;
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            totalStudents = resultSet.getInt("COUNT(id)");
        }
        elektroenergjetike_bachelor.setText(String.valueOf(totalStudents));
    }
    public void titulliChart() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT titulli, COUNT(*) FROM employed GROUP BY titulli");
        titulliPieChart.getData().clear();
        while (resultSet.next()) {
            String titulli = resultSet.getString("titulli");
            int count = resultSet.getInt("COUNT(*)");
            PieChart.Data data = new PieChart.Data(titulli, count);
            titulliPieChart.getData().add(data);
        }
    }
    public void drejtimiChart() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT drejtimi, COUNT(*) FROM employed GROUP BY drejtimi");
        drejtimiPieChart.getData().clear();
        while (resultSet.next()) {
            String drejtimi = resultSet.getString("drejtimi");
            int count = resultSet.getInt("COUNT(*)");
            PieChart.Data data = new PieChart.Data(drejtimi, count);
            drejtimiPieChart.getData().add(data);
        }
    }
    public void changeLanguage(){
        ToggleGroup languageToggleGroup = new ToggleGroup();
        language_AL_button.setToggleGroup(languageToggleGroup);
        language_EN_button.setToggleGroup(languageToggleGroup);
        languageToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == language_AL_button) {
                Locale currentLocale = new Locale("sq", "AL");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.content_ks", currentLocale);
                dashboard_Btn.setText(bundle.getString("dashboard_btn"));
                manage_Btn.setText(bundle.getString("manage_btn"));
                welcome_label.setText(bundle.getString("welcomeLabel"));
                logout_Btn.setText(bundle.getString("signout_btn"));
                statistics_Btn.setText(bundle.getString("statistics"));
                bachelor_label.setText(bundle.getString("bachelor"));
                master_label.setText(bundle.getString("master"));
                phd_label.setText(bundle.getString("phd"));
                iks_bsc_label.setText(bundle.getString("iks") + "(BSc)");
                iks_msc_label.setText(bundle.getString("iks") + "(MSc)");
                ear_msc_label.setText(bundle.getString("ear") + "(BSc)");
                ear_bsc_label.setText(bundle.getString("ear") + "(MSc)");
                tik_bsc_label.setText(bundle.getString("tik") + "(BSc)");
                tik_msc_label.setText(bundle.getString("tik") + "(MSc)");
                elektro_label.setText(bundle.getString("elektro"));
            } else if (newToggle == language_EN_button) {
                Locale currentLocale = new Locale("sq", "US");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.content_en", currentLocale);
                dashboard_Btn.setText(bundle.getString("dashboard_btn"));
                manage_Btn.setText(bundle.getString("manage_btn"));
                welcome_label.setText(bundle.getString("welcomeLabel"));
                logout_Btn.setText(bundle.getString("signout_btn"));
                statistics_Btn.setText(bundle.getString("statistics"));
                bachelor_label.setText(bundle.getString("bachelor"));
                master_label.setText(bundle.getString("master"));
                phd_label.setText(bundle.getString("phd"));
                iks_bsc_label.setText(bundle.getString("iks") + "(BSc)");
                iks_msc_label.setText(bundle.getString("iks") + "(MSc)");
                ear_msc_label.setText(bundle.getString("ear") + "(BSc)");
                ear_bsc_label.setText(bundle.getString("ear") + "(MSc)");
                tik_bsc_label.setText(bundle.getString("tik") + "(BSc)");
                tik_msc_label.setText(bundle.getString("tik") + "(MSc)");
                elektro_label.setText(bundle.getString("elektro"));
            }
        });
        languageToggleGroup.selectToggle(language_AL_button);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            bachelor();
            master();
            doktorature();
            iks_bachelor();
            iks_master();
            ear_bachelor();
            ear_master();
            tik_bachelor();
            tik_master();
            titulliChart();
            drejtimiChart();
            elektronergjetike_bachelor();
        }catch (SQLException e){
            e.printStackTrace();
        }
        changeLanguage();
        statistics_Btn.setStyle("-fx-background-color: linear-gradient(to left, #11998e, #38ef7d); -fx-cursor: hand;");
    }
}
