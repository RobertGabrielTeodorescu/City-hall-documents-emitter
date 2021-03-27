package controller;

import dtos.AddressDto;
import dtos.LoggedInUserDto;
import exceptions.CustomAddressExceptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.AddressService;
import service.UserService;
import utils.AlertMessages;
import utils.MyStringConverter;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class AddressFrameController {

    private LoggedInUserDto loggedInUser;
    private UserService userService;
    private AddressService addressService;
    private Alert alert;

    @FXML private TableView<AddressDto> tableView;
    @FXML private TableColumn<AddressDto,String> cityColumn;
    @FXML private TableColumn<AddressDto,String> countyColumn;
    @FXML private TableColumn<AddressDto,String> streetColumn;
    @FXML private TableColumn<AddressDto,Integer> nrColumn;
    @FXML private TableColumn<AddressDto,Character> blockColumn;
    @FXML private TableColumn<AddressDto,Integer> floorColumn;
    @FXML private TableColumn<AddressDto,Integer> apartmentColumn;
    @FXML private TextField cityTextField;
    @FXML private TextField countyTextField;
    @FXML private TextField streetTextField;
    @FXML private TextField nrTextField;
    @FXML private TextField blockTextField;
    @FXML private TextField floorTextField;
    @FXML private TextField apartmentTextField;


    public void initData(LoggedInUserDto loggedInUser){
        this.loggedInUser=loggedInUser;
        userService=new UserService();
        addressService=new AddressService();
        alert=new Alert(Alert.AlertType.NONE);
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        countyColumn.setCellValueFactory(new PropertyValueFactory<>("county"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        nrColumn.setCellValueFactory(new PropertyValueFactory<>("nr"));
        blockColumn.setCellValueFactory(new PropertyValueFactory<>("block"));
        floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
        apartmentColumn.setCellValueFactory(new PropertyValueFactory<>("apartment"));
        tableView.setItems(getAddresses());
    }

    public ObservableList<AddressDto> getAddresses(){
        ObservableList<AddressDto> addressDtos= FXCollections.observableArrayList();
        Set<AddressDto> addressDtoSet=loggedInUser.getAddressSet();
        addressDtos.addAll(addressDtoSet);
        return addressDtos;
    }

    @FXML private void add(ActionEvent event){
        AddressDto addressDto=new AddressDto();
        try{
            addressDto.setNr(new MyStringConverter().fromString(nrTextField.getText()));
            addressDto.setStreet(streetTextField.getText());
            addressDto.setFloor(new MyStringConverter().fromString(floorTextField.getText()));
            addressDto.setCounty(countyTextField.getText());
            addressDto.setCity(cityTextField.getText());
            addressDto.setBlock(blockTextField.getText().charAt(0));
            addressDto.setApartment(new MyStringConverter().fromString(apartmentTextField.getText()));
            addressDto.setId(UUID.randomUUID().toString());
            userService.addAddressToUser(loggedInUser.getId(), addressDto);
            loggedInUser=userService.updateLoggedUser(loggedInUser.getId());

            tableView.setItems(getAddresses());
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(AlertMessages.ADD_ADDRESS_CONFIRMATION);
            alert.show();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(CustomAddressExceptions.INVALID_INPUTS);
            alert.show();
        }
    }

    @FXML private void delete(ActionEvent event){
        AddressDto addressDto=tableView.getSelectionModel().getSelectedItem();
        try{
            userService.removeAddressFromUser(loggedInUser.getId(), addressDto);
            loggedInUser=userService.updateLoggedUser(loggedInUser.getId());
            tableView.setItems(getAddresses());
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(AlertMessages.DELETE_ADDRESS_CONFIRMATION);
            alert.show();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(CustomAddressExceptions.NO_ADDRESS_SELECTED);
            alert.show();
        }
    }

    @FXML private void back(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/user-main.fxml"));
        Parent root=loader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root,330,200));
        stage.setTitle("Main page");
        UserMainFrameController userMainFrameController=loader.getController();
        userMainFrameController.initData(loggedInUser);
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }


}
