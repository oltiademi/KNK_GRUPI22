package controllers;

import com.example.knk_grupi22.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.dto.UpdateUserDto;
import service.PasswordHasher;
import service.UserService;
import java.io.IOException;
import java.sql.*;

public class ForgotPasswordController {
    @FXML
    private TextField email_tf;
    @FXML
    private PasswordField newPassword_tf;
    @FXML
    private PasswordField confirmPassword_tf;
    @FXML
    private Button verify_btn;
    @FXML
    public void verifyEmail(ActionEvent actionEvent) throws SQLException, IOException {
        if (actionEvent.getSource() == verify_btn) {
            String email = email_tf.getText();
            String password = newPassword_tf.getText();
            String verifyPassword = confirmPassword_tf.getText();
            String salt = PasswordHasher.generateSalt();
            String saltedHash = PasswordHasher.generateSaltedHash(password, salt);

            if (password.equals(verifyPassword)) {
                UpdateUserDto updateUser = new UpdateUserDto(email, salt, saltedHash);
                boolean emailExists = UserService.checkEmail(updateUser);
                if (emailExists) {
                    UserService.updateUser(updateUser);
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/view/login.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    stage.setTitle("Login");
                    stage.setScene(scene);
                    stage.show();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully changed the password!");
                    alert.showAndWait();
                    Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    currentStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Email doesn't exist!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Passwords don't match!");
                alert.showAndWait();
            }
        }
    }
}

