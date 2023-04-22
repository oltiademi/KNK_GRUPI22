package com.example.knk_grupi22;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
<<<<<<< Updated upstream
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    @FXML
=======
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupController {
    @FXML
    private TextField email;
    @FXML
>>>>>>> Stashed changes
    private Button hideBtn;
    @FXML
    private Button buttonShowPassword;
    @FXML
    private TextField textfieldPassword;
<<<<<<< Updated upstream
    @FXML
    private TextField password;
    @FXML
    private Button signUpBtn;
=======

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

    private ConnectionUtil connectionUtil;
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream

=======
    public void signUp(){
        String first = firstName.getText();
        String last = lastName.getText();
        String Email = email.getText();
        String user = username.getText();
        String pw = password.getText();
        String num = (phoneNum.getText());

        try {
            if(first.isEmpty() || last.isEmpty() || Email.isEmpty() || user.isEmpty() || pw.isEmpty() || num.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE");
                alert.setContentText("All fields must be filled!");
                alert.showAndWait();
            }else {
                conn = connectionUtil.getConnection();
                preparedStatement = conn.prepareStatement("INSERT into users(FirstName, LastName, email, password, phoneNum, username) " +
                        "VALUES(?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, first);
                preparedStatement.setString(2, last);
                preparedStatement.setString(3, Email);
                preparedStatement.setString(4, pw);
                preparedStatement.setString(5, String.valueOf(num));
                preparedStatement.setString(6, user);
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
>>>>>>> Stashed changes
    public void switchToLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 637, 425);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.hide();
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
