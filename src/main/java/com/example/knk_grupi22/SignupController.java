package com.example.knk_grupi22;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    @FXML
    private Button hideBtn;
    @FXML
    private Button buttonShowPassword;
    @FXML
    private TextField textfieldPassword;
    @FXML
    private TextField password;
    @FXML
    private Button signUpBtn;
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


    public void switchToLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 637, 425);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.hide();
    }
}
