package controllers;

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
import service.DashboardService;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
public class DashboardController implements Initializable {

    @FXML
    private Label totalEmployedLabel;
    @FXML
    private Label totalMaleLabel;
    @FXML
    private Label totalFemaleLabel;
    @FXML
    private Label welcome_label;
    @FXML
    private Button statistics_Btn;
    @FXML
    private RadioButton language_AL_button;
    @FXML
    private RadioButton language_EN_button;
    @FXML
    private Button dashboard_Btn;
    @FXML
    private Button logout_Btn;
    @FXML
    private Button manage_Btn;
    @FXML
    private RadioButton radio_Femer;
    @FXML
    private RadioButton radio_Mashkull;
    @FXML
    private RadioButton radio_Other;
    private ToggleGroup gjiniaToggleGroup;
    @FXML
    private Label totalEmployed_count;
    @FXML
    private Label totalFemale_count;
    @FXML
    private Label totalMale_count;
    @FXML
    private PieChart totalEmployedChart;
    private DashboardService dashboardService = new DashboardService();

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

    public void switchForm(ActionEvent actionEvent)throws IOException {
         if (actionEvent.getSource() == manage_Btn) {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/view/manage.fxml"));
            Parent dashboardRoot = fxmlLoader1.load();
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.getScene().setRoot(dashboardRoot);
            currentStage.show();
        } else if(actionEvent.getSource() == statistics_Btn){
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/view/statistikat.fxml"));
            Parent dashboardRoot = fxmlLoader1.load();
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.getScene().setRoot(dashboardRoot);
            currentStage.show();
         }
    }
    public void totalEmployed() throws SQLException {
        int totalStudents = dashboardService.getTotalEmployed();
        totalEmployed_count.setText(String.valueOf(totalStudents));
    }
    public void totalMaleEmployed() throws SQLException {
        int totalMaleStudents = dashboardService.getTotalMaleEmployed();
        totalMale_count.setText(String.valueOf(totalMaleStudents));
    }
    public void totalFemaleEmployed() throws SQLException {
        int totalFemaleStudents = dashboardService.getTotalFemaleEmployed();
        totalFemale_count.setText(String.valueOf(totalFemaleStudents));
    }
    public void employedChart() throws SQLException {
        Map<String, Integer> chartData = dashboardService.getEmployedChartData();
        totalEmployedChart.getData().clear();
        for (Map.Entry<String, Integer> entry : chartData.entrySet()) {
            String gjinia = entry.getKey();
            int count = entry.getValue();
            PieChart.Data data = new PieChart.Data(gjinia, count);
            totalEmployedChart.getData().add(data);
        }
    }
    public void changeLanguage() {
        ToggleGroup languageToggleGroup = new ToggleGroup();
        language_AL_button.setToggleGroup(languageToggleGroup);
        language_EN_button.setToggleGroup(languageToggleGroup);
        languageToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == language_AL_button) {
                Locale currentLocale = new Locale("sq", "AL");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.content_ks", currentLocale);
                dashboard_Btn.setText(bundle.getString("dashboard_btn"));
                manage_Btn.setText(bundle.getString("manage_btn"));
                statistics_Btn.setText(bundle.getString("statistics"));
                welcome_label.setText(bundle.getString("welcomeLabel"));
                totalEmployedLabel.setText(bundle.getString("totalEmployed"));
                totalMaleLabel.setText(bundle.getString("totalMaleEmployed"));
                totalFemaleLabel.setText(bundle.getString("totalFemaleEmployed"));
                logout_Btn.setText(bundle.getString("signout_btn"));
            } else if (newToggle == language_EN_button) {
                Locale currentLocale = new Locale("sq", "US");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.content_en", currentLocale);
                dashboard_Btn.setText(bundle.getString("dashboard_btn"));
                manage_Btn.setText(bundle.getString("manage_btn"));
                statistics_Btn.setText(bundle.getString("statistics"));
                welcome_label.setText(bundle.getString("welcomeLabel"));
                totalEmployedLabel.setText(bundle.getString("totalEmployed"));
                totalMaleLabel.setText(bundle.getString("totalMaleEmployed"));
                totalFemaleLabel.setText(bundle.getString("totalFemaleEmployed"));
                logout_Btn.setText(bundle.getString("signout_btn"));
            }
        });
        languageToggleGroup.selectToggle(language_AL_button);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        changeLanguage();
        try {
            totalEmployed();
            totalMaleEmployed();
            totalFemaleEmployed();
            employedChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        gjiniaToggleGroup = new ToggleGroup();
        radio_Femer.setToggleGroup(gjiniaToggleGroup);
        radio_Mashkull.setToggleGroup(gjiniaToggleGroup);
        radio_Other.setToggleGroup(gjiniaToggleGroup);
        dashboard_Btn.setStyle("-fx-background-color: linear-gradient(to left, #11998e, #38ef7d); -fx-cursor: hand;");
    }
}