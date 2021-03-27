package controller;

import dtos.AddressDto;
import dtos.DocumentDto;
import dtos.RequestDto;
import exceptions.CustomRequestExceptions;
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
import service.DocumentService;
import service.RequestService;
import utils.AlertMessages;

import java.io.IOException;
import java.util.List;


public class RequestsAdminFrameController {

    @FXML TableView <RequestDto> tableView;
    @FXML TableColumn <RequestDto,String> addressColumn;
    @FXML TableColumn <RequestDto,String> documentColumn;
    @FXML TableColumn <RequestDto,String> dateColumn;
    @FXML TableColumn <RequestDto,Integer> remRequestsColumn;
    @FXML TableColumn <RequestDto,String> approvedColumn;
    @FXML TableColumn <RequestDto,String> userColumn;

    @FXML TextField addressTextField;
    @FXML TextField documentTextField;

    private Alert alert;

    private RequestService requestService;
    private AddressService addressService;
    private DocumentService documentService;

    public void initData(){
        requestService=new RequestService();
        addressService=new AddressService();
        documentService=new DocumentService();
        alert=new Alert(Alert.AlertType.NONE);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("addressToString"));
        documentColumn.setCellValueFactory(new PropertyValueFactory<>("documentType"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("requestTimeString"));
        remRequestsColumn.setCellValueFactory(new PropertyValueFactory<>("remainingRequests"));
        approvedColumn.setCellValueFactory(new PropertyValueFactory<>("approved"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        tableView.setItems(getRequests());
    }

    public ObservableList<RequestDto> getRequests(){
        ObservableList<RequestDto> requestDtos= FXCollections.observableArrayList();
        List<RequestDto> requestDtoList=requestService.getAllRequests();
        requestDtos.addAll(requestDtoList);
        return requestDtos;
    }

    @FXML private void searchByAddress(ActionEvent event){
        String searchAddress=addressTextField.getText();
        if(searchAddress.equals("")){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(CustomRequestExceptions.NO_ADDRESS);
            alert.show();
        }
        else{
            ObservableList<RequestDto> requestDtos=tableView.getItems();
            ObservableList<RequestDto> requestDtos1=FXCollections.observableArrayList();
            for(RequestDto requestDto:requestDtos){
                if(requestDto.getAddressToString().contains(searchAddress)){
                    requestDtos1.add(requestDto);
                }
            }
            tableView.setItems(requestDtos1);
        }
    }

    @FXML private void searchByDocuments(ActionEvent event){
        String documentType=documentTextField.getText();
        if(documentType.equals("")){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(CustomRequestExceptions.NO_DOCUMENT);
            alert.show();
        }
        else{
            ObservableList<RequestDto> requestDtos=tableView.getItems();
            ObservableList<RequestDto> requestDtos1=FXCollections.observableArrayList();
            for(RequestDto requestDto:requestDtos){
                if(requestDto.getDocumentType().contains(documentType)){
                    requestDtos1.add(requestDto);
                }
            }
            tableView.setItems(requestDtos1);
        }
    }

    @FXML private void resetView(ActionEvent event){
        tableView.setItems(getRequests());
    }

    @FXML private void removeRequest(ActionEvent event){
        try{
            RequestDto requestDto=tableView.getSelectionModel().getSelectedItem();
            AddressDto addressDto=requestDto.getAddress();
            DocumentDto documentDto=requestDto.getDocument();
            addressDto = addressService.removeRequestFromAddress(addressDto,requestDto);
            documentDto=documentService.removeRequestFromDocument(documentDto,requestDto);
            addressService.updateAddress(addressDto);
            documentService.updateDocument(documentDto);
            requestService.deleteRequest(requestDto);
            tableView.setItems(getRequests());
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(AlertMessages.REQUEST_REMOVED);
            alert.show();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(CustomRequestExceptions.NO_REQUEST_SELECTED);
            alert.show();
        }
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

    @FXML private void approve(ActionEvent event){
        RequestDto requestDto=tableView.getSelectionModel().getSelectedItem();
        try{
            requestDto.setApproved("Approved");
            requestService.updateRequest(requestDto);
            tableView.setItems(getRequests());
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(AlertMessages.REQUEST_APPROVED);
            alert.show();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(CustomRequestExceptions.NO_REQUEST_SELECTED);
            alert.show();
        }
    }

    @FXML private void deny(ActionEvent event){
        RequestDto requestDto=tableView.getSelectionModel().getSelectedItem();
        try{
            requestDto.setApproved("Denied");
            requestService.updateRequest(requestDto);
            tableView.setItems(getRequests());
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(AlertMessages.REQUEST_DENIED);
            alert.show();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(CustomRequestExceptions.NO_REQUEST_SELECTED);
            alert.show();
        }
    }

}
