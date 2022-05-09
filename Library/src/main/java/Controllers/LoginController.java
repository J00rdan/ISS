package Controllers;

import Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController{
    private Service srv;

    private AdminMenuController adminMenuController;

    Parent mainMenuParent;

    @FXML
    public Button loginButton;
    @FXML
    public Button adminLoginButton;
    @FXML
    public TextField usernameField;
    @FXML
    public TextField passwordField;
    @FXML
    public Label wrongLogin;

    public void setService(Service service){
        srv = service;
    }

    public void setParent(Parent p){
        mainMenuParent = p;
    }

    public void setAdminMenuCtrl(AdminMenuController menuCtrl) {
        this.adminMenuController = menuCtrl;
    }

    public void adminLogin(ActionEvent actionEvent) {
        int id = Integer.parseInt(usernameField.getText());
        String pass = passwordField.getText().toString();

        if(srv.adminLogin(id, pass)) {
            adminMenuController.init();
            Stage stage = new Stage();
            stage.setTitle("Menu Window");
            stage.setScene(new Scene(mainMenuParent));

            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MPP chat");
            alert.setHeaderText("Authentication failure");
            alert.setContentText("Wrong username or password");
            alert.showAndWait();

        }
    }

    public void login(ActionEvent actionEvent){
        int id = Integer.parseInt(usernameField.getText());
        String pass = passwordField.getText().toString();
        if(srv.login(id, pass)) {
//            try {
//                gui.changeSceneToUserMenu();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        else{
            wrongLogin.setMaxWidth(Double.MAX_VALUE);
            AnchorPane.setLeftAnchor(wrongLogin, 0.0);
            AnchorPane.setRightAnchor(wrongLogin, 0.0);
            wrongLogin.setAlignment(Pos.CENTER);
            wrongLogin.setText("Wrong Login Credentials");

        }

    }
}
