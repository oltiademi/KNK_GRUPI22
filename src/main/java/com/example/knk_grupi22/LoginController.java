package com.example.knk_grupi22;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Button login_Btn;

    @FXML
    private Button signup_Btn;

    @FXML
    private PasswordField tf_Password;

    @FXML
    private TextField tf_Username;

    public void switchForm(ActionEvent actionEvent){
        if(actionEvent.getSource() == login_Btn){
        }
    }
}