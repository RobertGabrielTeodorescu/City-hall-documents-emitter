package controller;

import dtos.LoggedInUserDto;
import dtos.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;
import utils.AlertMessages;


public class LoginFrameController {

    @FXML private TextField signUpEmailTextField;
    @FXML private TextField signUpFirstNameTextField;
    @FXML private TextField signUpLastNameTextField;
    @FXML private TextField signUpPasswordTextField;
    @FXML private TextField loginEmailTextField;
    @FXML private TextField loginPasswordTextField;

    private Alert alert=new Alert(Alert.AlertType.NONE);
    private UserService userService=new UserService();

    @FXML private void doSignUp(ActionEvent event){

        String email=signUpEmailTextField.getText();
        String firstName=signUpFirstNameTextField.getText();
        String lastName=signUpLastNameTextField.getText();
        String password=signUpPasswordTextField.getText();

        UserDto userDto=new UserDto();
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setStatus("user");

        try{
            userService.addUser(userDto);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(AlertMessages.SIGN_UP_CONFIRMATION);
            alert.show();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    @FXML private void doLogIn(ActionEvent event){

        String email=loginEmailTextField.getText();
        String password=loginPasswordTextField.getText();

        try{
            if(email.equals("admin@admin.com")&&password.equals("administrator")){
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/admin-main.fxml"));
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,400,280));
                stage.setTitle("Admin main page");
                stage.show();
            }
            else{
                LoggedInUserDto loggedInUser= userService.getLoggingUserByEmailAndPassword(email,password);
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/user-main.fxml"));
                Parent root=loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,330,200));
                stage.setTitle("Main page");
                UserMainFrameController userMainFrameController=loader.getController();
                userMainFrameController.initData(loggedInUser);
                stage.show();
            }
            ((Node)event.getSource()).getScene().getWindow().hide();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

}
