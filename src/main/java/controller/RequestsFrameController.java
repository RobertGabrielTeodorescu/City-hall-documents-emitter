package controller;

import dtos.AddressDto;
import dtos.DocumentDto;
import dtos.LoggedInUserDto;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.AddressService;
import service.DocumentService;
import service.RequestService;
import service.UserService;
import utils.AddressUtils;
import utils.AlertMessages;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


public class RequestsFrameController {

    private LoggedInUserDto loggedInUser;

    Map<String, AddressDto> mapAddress;
    Map<String, DocumentDto> documentMap;

    private AddressService addressService;
    private DocumentService documentService;
    private RequestService requestService;
    private UserService userService;

    private Alert alert=new Alert(Alert.AlertType.NONE);

    @FXML TableView<RequestDto> tableView;
    @FXML TableColumn<RequestDto,String> addressColumn;
    @FXML TableColumn<RequestDto,String> documentColumn;
    @FXML TableColumn<RequestDto,String> dateColumn;
    @FXML TableColumn<RequestDto,Integer> remRequestsColumn;
    @FXML TableColumn<RequestDto,String> approvedColumn;

    @FXML ChoiceBox addressChoiceBox;
    @FXML ChoiceBox documentChoiceBox;

    public void initData(LoggedInUserDto loggedInUser){
        this.loggedInUser=loggedInUser;
        addressService=new AddressService();
        documentService=new DocumentService();
        requestService=new RequestService();
        userService=new UserService();
        mapAddress=new HashMap<>();
        documentMap=new HashMap<>();
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("addressToString"));
        documentColumn.setCellValueFactory(new PropertyValueFactory<>("documentType"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("requestTimeString"));
        remRequestsColumn.setCellValueFactory(new PropertyValueFactory<>("remainingRequests"));
        approvedColumn.setCellValueFactory(new PropertyValueFactory<>("approved"));
        populateAddressChoiceBox();
        populateDocumentChoiceBox();
        populateMaps();
        tableView.setItems(getRequests());
    }

    public ObservableList<RequestDto> getRequests(){
        List<RequestDto> requestDtos=requestService.getRequestsByLoggedUser(loggedInUser);
        ObservableList<RequestDto> observableList= FXCollections.observableArrayList();
        observableList.addAll(requestDtos);
        return observableList;
    }

    private void populateAddressChoiceBox(){
        Set<AddressDto> addressDtoSet= loggedInUser.getAddressSet();
        for(AddressDto addressDto:addressDtoSet){
            String addressString= AddressUtils.convertToString(addressDto);
            mapAddress.put(addressString,addressDto);
            addressChoiceBox.getItems().add(addressString);
        }
    }

    private void populateDocumentChoiceBox(){
        List<DocumentDto> documentDtoList=documentService.getAllDocuments();
        for(DocumentDto documentDto:documentDtoList){
            String documentType=documentDto.getTip();
            documentMap.put(documentType,documentDto);
            documentChoiceBox.getItems().add(documentType);
        }
    }

    private void populateMaps(){
        Set<AddressDto> addressDtoSet= loggedInUser.getAddressSet();
        for(AddressDto addressDto:addressDtoSet){
            String addressString= AddressUtils.convertToString(addressDto);
            mapAddress.put(addressString,addressDto);
        }
        List<DocumentDto> documentDtoList=documentService.getAllDocuments();
        for(DocumentDto documentDto:documentDtoList){
            String documentType=documentDto.getTip();
            documentMap.put(documentType,documentDto);
        }
    }

    @FXML private void makeRequest(ActionEvent event){
        try{
            AddressDto addressDto=mapAddress.getOrDefault(addressChoiceBox.getSelectionModel().getSelectedItem().toString(),null);
            DocumentDto documentDto=documentMap.getOrDefault(documentChoiceBox.getSelectionModel().getSelectedItem().toString(),null);
            int remRequests=requestService.getRequestsByAddressDocumentYear(addressDto,documentDto,LocalDateTime.now());
            RequestDto requestDto=new RequestDto();
            requestDto.setRequestTime(LocalDateTime.now());
            requestDto.setApproved("Pending");
            requestDto.setId(UUID.randomUUID().toString());
            requestDto.setRemainingRequests(2-remRequests);
            addressDto=addressService.addRequestToAddress(addressDto,requestDto);
            documentDto=documentService.addRequestToDocument(documentDto,requestDto);
            requestService.addRequest(requestDto);
            addressService.updateAddress(addressDto);
            documentService.updateDocument(documentDto);
            loggedInUser=userService.updateLoggedUser(loggedInUser.getId());
            populateMaps();
            tableView.setItems(getRequests());
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(AlertMessages.REQUEST_MADE);
            alert.show();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(CustomRequestExceptions.NO_DOCUMENT+" or "+CustomRequestExceptions.NO_ADDRESS);
            alert.show();
        }
    }

    @FXML private void deleteRequest(ActionEvent event){
        try{
            RequestDto requestDto=tableView.getSelectionModel().getSelectedItem();
            AddressDto addressDto=requestDto.getAddress();
            DocumentDto documentDto=requestDto.getDocument();
            addressDto=addressService.removeRequestFromAddress(addressDto,requestDto);
            documentDto=documentService.removeRequestFromDocument(documentDto,requestDto);
            addressService.updateAddress(addressDto);
            documentService.updateDocument(documentDto);
            requestService.deleteRequest(requestDto);
            loggedInUser=userService.updateLoggedUser(loggedInUser.getId()) ;
            tableView.setItems(getRequests());
            populateMaps();
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(AlertMessages.REQUEST_REMOVED);
            alert.show();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(CustomRequestExceptions.NO_REQUEST_SELECTED);
            alert.show();
        }
    }

    @FXML private void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/user-main.fxml"));
        Parent root=loader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root,330,200));
        stage.setTitle("Main page");
        UserMainFrameController userMainFrameController=loader.getController();
        userMainFrameController.initData(loggedInUser);
        stage.show();
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
    }

    @FXML private void updateRequest(ActionEvent event){
        RequestDto requestDto=tableView.getSelectionModel().getSelectedItem();
        AddressDto addressDto=requestDto.getAddress();
        DocumentDto documentDto=requestDto.getDocument();
        AddressDto addressDto1=mapAddress.getOrDefault(addressChoiceBox.getSelectionModel().getSelectedItem().toString(),null);
        DocumentDto documentDto1=documentMap.getOrDefault(documentChoiceBox.getSelectionModel().getSelectedItem().toString(),null);
        addressDto1=addressService.addRequestToAddress(addressDto1,requestDto);
        documentDto1=documentService.addRequestToDocument(documentDto1,requestDto);
        requestService.updateRequest(requestDto);
        addressService.updateAddress(addressDto1);
        documentService.updateDocument(documentDto1);
        addressService.updateAddress(addressDto);
        documentService.updateDocument(documentDto);
        loggedInUser=userService.updateLoggedUser(loggedInUser.getId());
        populateMaps();

        tableView.setItems(getRequests());
    }

}
