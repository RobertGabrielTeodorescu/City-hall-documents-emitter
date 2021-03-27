package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMainFrameController {

    @FXML private void showUsers(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/users-frame.fxml"));
        Parent root=loader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root,750,400));
        stage.setTitle("Users page");
        UsersFrameController usersFrameController=loader.getController();
        usersFrameController.initData();
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML private void showDocuments(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/documents-frame.fxml"));
        Parent root=loader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root,480,480));
        stage.setTitle("Documents page");
        DocumentsFrameController documentsFrameController=loader.getController();
        documentsFrameController.initData();
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML private void showAllRequests(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/requests-admin-frame.fxml"));
        Parent root=loader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root,950,480));
        stage.setTitle("Requests page");
        RequestsAdminFrameController requestsAdminFrameController=loader.getController();
        requestsAdminFrameController.initData();
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML private void back(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/login-frame.fxml"));
        Parent root=loader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root,680,400));
        stage.setTitle("Sign up & Login");
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

}
