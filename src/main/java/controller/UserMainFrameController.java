package controller;

import dtos.LoggedInUserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserMainFrameController {


    private LoggedInUserDto loggedInUser;

    public void initData(LoggedInUserDto loggedInUser){
        this.loggedInUser=loggedInUser;
    }

    @FXML private void addressView(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/addresses-frame.fxml"));
        Parent root=loader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root,600,535));
        stage.setTitle("My addresses");
        AddressFrameController addressFrameController=loader.getController();
        addressFrameController.initData(loggedInUser);
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML private void requestsView(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/requests-frame.fxml"));
        Parent root=loader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root,810,535));
        stage.setTitle("My requests");
        RequestsFrameController requestsFrameController=loader.getController();
        requestsFrameController.initData(loggedInUser);
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML private void back(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/login-frame.fxml"));
        Parent root=loader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root,680,400));
        stage.setTitle("Sign Up & Login");
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

}
