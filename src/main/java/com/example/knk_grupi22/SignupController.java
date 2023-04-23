package com.example.knk_grupi22;

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
import javafx.stage.Stage;
import service.ConnectionUtil;

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
    private TextField phoneNum;

    @FXML
    private Button signUpBtn;

    @FXML
    private Label phoneNumLabel;

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
        String num = (phoneNum.getText());

        try {
            if(first.isEmpty() || last.isEmpty() || Email.isEmpty() || user.isEmpty() || pw.isEmpty() || num.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE");
                alert.setContentText("All fields must be filled!");
                alert.showAndWait();
            } else {
                conn = connectionUtil.getConnection();
                preparedStatement = conn.prepareStatement("INSERT into users(FirstName, LastName, email, password, phoneNum, username) " +
                        "VALUES(?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, first);
                preparedStatement.setString(2, last);
                preparedStatement.setString(3, Email);
                preparedStatement.setString(4, pw);
                preparedStatement.setString(5, num);
                preparedStatement.setString(6, user);
                phoneNum.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*")) { // Check if the entered value is not a number
                        phoneNumLabel.setText("Incorrect format");
                    } else {
                        phoneNumLabel.setText(""); // Clear the label if the entered value is a number
                    }
                });
                preparedStatement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information message");
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