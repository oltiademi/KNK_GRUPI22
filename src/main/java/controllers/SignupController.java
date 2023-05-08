package controllers;

import com.example.knk_grupi22.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import service.ConnectionUtil;
import service.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private Button hideBtn;
    @FXML
    private Button buttonShowPassword;
    @FXML
    private TextField textfieldPassword;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField username;

    @FXML
    private TextField password;
    @FXML
    private Button signUpBtn;
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 637, 425);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}