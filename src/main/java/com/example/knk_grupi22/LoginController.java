package com.example.knk_grupi22;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button login_Btn;

    @FXML
    private Button signup_Btn;

    @FXML
    private PasswordField tf_Password;

    @FXML
    private TextField tf_Username;

    public void switchForm(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == login_Btn){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
            Parent homeRoot = fxmlLoader.load();


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
    public void signUp(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign-up.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.hide();
    }
}