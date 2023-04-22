package com.example.knk_grupi22;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
<<<<<<< Updated upstream
=======
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
>>>>>>> Stashed changes
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;
import service.ConnectionUtil;

import java.io.IOException;
import java.net.URL;
<<<<<<< Updated upstream
=======
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
>>>>>>> Stashed changes
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button login_Btn;

    @FXML
    private Button signup_Btn;
<<<<<<< Updated upstream
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

=======
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
    private ConnectionUtil connectionUtil;
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void showPassword(){
        textfieldPassword.setText(tf_Password.getText());
        textfieldPassword.setVisible(true);
        tf_Password.setVisible(false);
        hideBtn.setVisible(true);

>>>>>>> Stashed changes
    }
    public void hidePassword(){
        tf_Password.setText(textfieldPassword.getText());
        tf_Password.setVisible(true);
        textfieldPassword.setVisible(false);
        hideBtn.setVisible(false);
    }
<<<<<<< Updated upstream
    public void Login(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == login_Btn){
=======
    public void Login(ActionEvent actionEvent) throws IOException, SQLException {
        if(actionEvent.getSource() == login_Btn){
            String user = tf_Username.getText();
            String pw = tf_Password.getText();
            conn = ConnectionUtil.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pw);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

=======
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE");
                alert.setContentText("Incorrect password/username");
                alert.showAndWait();
            }
>>>>>>> Stashed changes
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