package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.ConnectionUtil;
import service.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    @FXML
    private Label alreadyHave_Label;

    @FXML
    private Button buttonShowPassword;

    @FXML
    private TextField email;

    @FXML
    private Label email_Label;

    @FXML
    private TextField firstName;

    @FXML
    private Label firstName_Label;

    @FXML
    private Button hideBtn;

    @FXML
    private RadioButton language_AL_button1;

    @FXML
    private RadioButton language_EN_button1;

    @FXML
    private TextField lastName;

    @FXML
    private Label lastName_Label;

    @FXML
    private PasswordField password;

    @FXML
    private Label password_Label;

    @FXML
    private Label phoneNumLabel;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button switchToLogin_Btn;

    @FXML
    private TextField textfieldPassword;

    @FXML
    private TextField username;

    @FXML
    private Label username_Label;
    UserService userService = new UserService();

    private ConnectionUtil connectionUtil;
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public void showPassword(){
        textfieldPassword.setText(password.getText());
        textfieldPassword.setVisible(true);
        password.setVisible(false);
        hideBtn.setVisible(true);
        buttonShowPassword.setVisible(false);
    }
    public void hidePassword(){
        password.setText(textfieldPassword.getText());
        password.setVisible(true);
        textfieldPassword.setVisible(false);
        hideBtn.setVisible(false);
        buttonShowPassword.setVisible(true);
    }

    public void signUp(){

        String first = firstName.getText();
        String last = lastName.getText();
        String Email = email.getText();
        String user = username.getText();
        String pw = password.getText();

        try {
            if(first.isEmpty() || last.isEmpty() || Email.isEmpty() || user.isEmpty() || pw.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("All fields must be filled!");
                alert.showAndWait();
            } else {
                userService.signUp(first, last, Email, user, pw);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Succesfully signed up");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void switchToLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 637, 425);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.hide();
    }
    public void changeLanguage() {
        ToggleGroup languageToggleGroup = new ToggleGroup();
        language_AL_button1.setToggleGroup(languageToggleGroup);
        language_EN_button1.setToggleGroup(languageToggleGroup);
        languageToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == language_AL_button1) {
                Locale currentLocale = new Locale("sq", "AL");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.content_ks", currentLocale);
                firstName_Label.setText(bundle.getString("name_label"));
                firstName_Label.setAlignment(Pos.CENTER_RIGHT);
                firstName.setPromptText(bundle.getString("name_label"));
                lastName_Label.setText(bundle.getString("lastname_label"));
                lastName.setPromptText(bundle.getString("lastname_label"));
                username_Label.setText(bundle.getString("username"));
                username.setPromptText(bundle.getString("username"));
                password_Label.setText(bundle.getString("password"));
                alreadyHave_Label.setText(bundle.getString("alreadyHave"));
                switchToLogin_Btn.setText(bundle.getString("button.login.name"));


            } else if (newToggle == language_EN_button1) {
                Locale currentLocale = new Locale("sq", "US");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.content_en", currentLocale);
                firstName_Label.setText(bundle.getString("name_label"));
                firstName_Label.setAlignment(Pos.CENTER);
                firstName.setPromptText(bundle.getString("name_label"));
                lastName_Label.setText(bundle.getString("lastname_label"));
                lastName.setPromptText(bundle.getString("lastname_label"));
                username_Label.setText(bundle.getString("username"));
                username.setPromptText(bundle.getString("username"));
                password_Label.setText(bundle.getString("password"));
                alreadyHave_Label.setText(bundle.getString("alreadyHave"));
                switchToLogin_Btn.setText(bundle.getString("button.login.name"));

            }
        });
        languageToggleGroup.selectToggle(language_AL_button1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeLanguage();
    }
}