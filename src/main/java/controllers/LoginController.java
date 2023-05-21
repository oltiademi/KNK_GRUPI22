package controllers;

import com.example.knk_grupi22.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.User;
import service.ConnectionUtil;
import service.PasswordHasher;
import service.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.io.*;
import java.util.Properties;
import java.io.*;
import java.util.Properties;


public class LoginController implements Initializable {
    @FXML
    private Button login_Btn;

    @FXML
    private Button signup_Btn;
    @FXML
    private Button forgotPassword_btn;
    @FXML
    private RadioButton language_AL_button;

    @FXML
    private RadioButton language_EN_button;
    @FXML
    private Button ButtonshowPassword;
    @FXML
    private Button hideBtn;

    @FXML
    private Label notRegisteredLabel = new Label();

    @FXML
    private CheckBox rememberMeCheckbox;
    @FXML
    private TextField tf_Username;

    @FXML
    private TextField textfieldPassword;

    @FXML
    private PasswordField tf_Password;
    private ConnectionUtil connectionUtil;
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    UserService userService = new UserService();
    private static final String CONFIG_FILE = "config.remember.properties";

    private void saveCredentials(String username, String password) {
        Properties properties = new Properties();
        properties.setProperty("username", username);
        properties.setProperty("password", password);

        try (OutputStream outputStream = new FileOutputStream(CONFIG_FILE)) {
            properties.store(outputStream, "Remember Me Configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadSavedCredentials() {
        try (InputStream inputStream = new FileInputStream(CONFIG_FILE)) {
            Properties properties = new Properties();
            properties.load(inputStream);

            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            if (username != null && password != null) {
                tf_Username.setText(username);
                tf_Password.setText(password);
                rememberMeCheckbox.setSelected(true);
            }
        } catch (IOException e) {
            // Configuration file doesn't exist or couldn't be loaded
        }
    }
    private void clearSavedCredentials() {
        File configFile = new File(CONFIG_FILE);
        if (configFile.exists()) {
            configFile.delete();
        }
    }

    public void showPassword() {
        textfieldPassword.setText(tf_Password.getText());
        textfieldPassword.setVisible(true);
        tf_Password.setVisible(false);
        hideBtn.setVisible(true);

    }

    public void hidePassword() {
        tf_Password.setText(textfieldPassword.getText());
        tf_Password.setVisible(true);
        textfieldPassword.setVisible(false);
        hideBtn.setVisible(false);
    }

    public void Login(ActionEvent actionEvent) throws IOException, SQLException {
        if (actionEvent.getSource() == login_Btn) {

            String user = tf_Username.getText();
            String pw = tf_Password.getText();
            if (user.isEmpty() || pw.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR!");
                alert.setHeaderText(null);
                alert.setContentText("Fill all required fields");
                alert.showAndWait();
            } else {
                if (userService.login(user, pw) == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR!");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username/password");
                    alert.showAndWait();
                } else {
                    if (rememberMeCheckbox.isSelected()) {
                        saveCredentials(user, pw);
                    } else {
                        clearSavedCredentials();
                    }
                    FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
                    Parent dashboardRoot = fxmlLoader1.load();
                    // Create a Scene for the dashboard
                    Scene scene = new Scene(dashboardRoot, 1300, 700);
                    // Create a Stage for the dashboard
                    Stage stage = new Stage();
                    stage.setTitle("E-Alumni");
                    stage.setScene(scene);

                    // Hide the current window
                    Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    currentStage.hide();

                    // Show the dashboard window
                    stage.show();
                }
            }
        }
    }



    public void switchToSignUp(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign-up.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.hide();
    }
    public void forgotPassword(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource()== forgotPassword_btn) {
            FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("forgotPassword.fxml"));
            Parent dashboardRoot = fxmlLoader1.load();

            Scene scene = new Scene(dashboardRoot, 521, 326);

            Stage stage = new Stage();
            stage.setTitle("Forgot your password?");
            stage.setScene(scene);

            // Hide the current window
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.hide();

            // Show the dashboard window
            stage.show();
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
                login_Btn.setText(bundle.getString("button.login.name"));
                forgotPassword_btn.setText(bundle.getString("forgot_password"));
                rememberMeCheckbox.setText(bundle.getString("remember_me"));
                notRegisteredLabel.setText(bundle.getString("not_registered"));
                signup_Btn.setText(bundle.getString("sign_up"));
                signup_Btn.setAlignment(Pos.CENTER_RIGHT);
                tf_Username.setPromptText(bundle.getString("username"));
                tf_Password.setPromptText(bundle.getString("password"));

            } else if (newToggle == language_EN_button) {
                Locale currentLocale = new Locale("sq", "US");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.content_en", currentLocale);
                login_Btn.setText(bundle.getString("button.login.name"));
                forgotPassword_btn.setText(bundle.getString("forgot_password"));
                rememberMeCheckbox.setText(bundle.getString("remember_me"));
                notRegisteredLabel.setText(bundle.getString("not_registered"));
                signup_Btn.setText(bundle.getString("sign_up"));
                signup_Btn.setAlignment(Pos.CENTER_LEFT);
                tf_Username.setPromptText(bundle.getString("username"));
                tf_Password.setPromptText(bundle.getString("password"));
            }
        });
        languageToggleGroup.selectToggle(language_AL_button);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSavedCredentials();
        changeLanguage();
    }
}
