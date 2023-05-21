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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ConnectionUtil;
import service.PasswordHasher;

import java.io.IOException;
import java.sql.*;

public class ForgotPasswordController {
    @FXML
    private AnchorPane PasswordPane;

    @FXML
    private AnchorPane emailPane;

    @FXML
    private TextField email_tf;
    @FXML
    private PasswordField newPassword_tf;
    @FXML
    private PasswordField confirmPassword_tf;

    @FXML
    private Button verify_btn;
    @FXML
    private Button changePassword_btn;
    private Connection conn = null;
    private PreparedStatement statement = null;
    private Statement st = null;
    private ResultSet resultSet = null;
    @FXML
    public void verifyEmail(ActionEvent actionEvent) throws SQLException, IOException {
        if (actionEvent.getSource() == verify_btn) {
            String email = email_tf.getText();
            String checkEmail = "SELECT * FROM users WHERE email = ?";
            conn = ConnectionUtil.getConnection();
            statement = conn.prepareStatement(checkEmail);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                    String password = newPassword_tf.getText();
                    String verifyPassword = confirmPassword_tf.getText();
                    String salt = PasswordHasher.generateSalt();
                    String saltedHash = PasswordHasher.generateSaltedHash(password, salt);

                    if (password.equals(verifyPassword)) {
                        String updateSql = "UPDATE users SET salt = ?, saltedHash = ? WHERE email = ?";
                        PreparedStatement updateStatement = conn.prepareStatement(updateSql);
                        updateStatement.setString(1, salt);
                        updateStatement.setString(2, saltedHash);
                        updateStatement.setString(3, email);
                        int rowsAffected = updateStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("INFORMATION");
                            alert.setHeaderText(null);
                            alert.setContentText("Password changed succesfully");
                            alert.showAndWait();

                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                            stage.setTitle("Login");
                            stage.setScene(scene);

                            stage.show();
                            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            currentStage.close();
                        } else {
                            System.out.println("Update failed");
                        }
                    }else {
                        System.out.println("Passwords dont match");
                    }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Email not found in database");
                alert.showAndWait();
            }
        }
    }
}
