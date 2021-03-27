package controller;

import dtos.UserDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.UserService;

import java.io.IOException;
import java.util.List;


public class UsersFrameController {

    @FXML TableView<UserDto> tableView;
    @FXML TableColumn<UserDto,String> firstName;
    @FXML TableColumn<UserDto,String> lastName;
    @FXML TableColumn<UserDto,String> email;
    @FXML TableColumn<UserDto,String> status;

    private UserService userService;

    public void initData(){
        userService=new UserService();
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(getUsers());
    }

    public ObservableList<UserDto> getUsers(){
        List<UserDto> users=userService.getAllUsers();
        ObservableList<UserDto> allusers= FXCollections.observableArrayList();
        allusers.addAll(users);
        return allusers;
    }

    @FXML private void back(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/admin-main.fxml"));
        Parent root=loader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root,400,280));
        stage.setTitle("Admin main page");
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

}
