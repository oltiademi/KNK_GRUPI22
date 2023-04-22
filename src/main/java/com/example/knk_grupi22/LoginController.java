package com.example.knk_grupi22;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button login_Btn;

    @FXML
    private Button signup_Btn;
    @FXML
    private Button ButtonshowPassword;
    @FXML
    private Button hideBtn;
    @FXML
    private TextField tf_Username;

    @FXML
    private TextField textfieldPassword;

    @FXML
    private PasswordField tf_Password;

    public void showPassword(){
            textfieldPassword.setText(tf_Password.getText());
            textfieldPassword.setVisible(true);
            tf_Password.setVisible(false);
            hideBtn.setVisible(true);

    }
    public void hidePassword(){
        tf_Password.setText(textfieldPassword.getText());
        tf_Password.setVisible(true);
        textfieldPassword.setVisible(false);
        hideBtn.setVisible(false);
    }
    public void Login(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == login_Btn){
                FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
                Parent dashboardRoot = fxmlLoader1.load();
                // Create a Scene for the dashboard
                Scene scene = new Scene(dashboardRoot, 1540, 790);
                // Create a Stage for the dashboard
                Stage stage = new Stage();
                stage.setScene(scene);

                // Hide the current window
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.hide();

                // Show the dashboard window
                stage.show();

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}