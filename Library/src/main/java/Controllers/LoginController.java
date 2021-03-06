package Controllers;

import Model.Subscriber;
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
    private UserMenuController userMenuController;

    Parent mainMenuParent;
    Parent userMenuParent;

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

    public void setUserParent(Parent p){
        userMenuParent = p;
    }

    public void setAdminMenuCtrl(AdminMenuController menuCtrl) {
        this.adminMenuController = menuCtrl;
    }

    public void setUserMenuController(UserMenuController menuCtrl){
        this.userMenuController = menuCtrl;
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

        Subscriber s = srv.login(id, pass);

        if(s != null) {
            userMenuController.init();
            userMenuController.setUser(s);
            Stage stage = new Stage();
            stage.setTitle("Menu Window");
            stage.setScene(new Scene(userMenuParent));

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
}
