package controller;

import dtos.DocumentDto;
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
import service.DocumentService;
import utils.AlertMessages;

import java.io.IOException;
import java.util.List;


public class DocumentsFrameController {

    @FXML TableView<DocumentDto> tableView;
    @FXML TableColumn<DocumentDto,String> type;
    @FXML TextField documentTypeTextField;

    private Alert alert;

    private DocumentService documentService;

    public void initData(){
        documentService=new DocumentService();
        alert=new Alert(Alert.AlertType.NONE);
        type.setCellValueFactory(new PropertyValueFactory<>("tip"));
        tableView.setItems(getDocuments());
    }

    private ObservableList<DocumentDto> getDocuments(){
        ObservableList<DocumentDto> documentDtoObservableList= FXCollections.observableArrayList();
        List<DocumentDto> documentDtoList=documentService.getAllDocuments();
        documentDtoObservableList.addAll(documentDtoList);
        return documentDtoObservableList;
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

    @FXML private void addDocument(ActionEvent event){
        DocumentDto documentDto=new DocumentDto();
        documentDto.setTip(documentTypeTextField.getText());
        try{
            documentService.addDocument(documentDto);
            tableView.setItems(getDocuments());
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(AlertMessages.DOCUMENT_ADDED);
            alert.show();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML private void removeDocument(ActionEvent event){
        DocumentDto documentDto=tableView.getSelectionModel().getSelectedItem();
        try{
            documentService.removeDocument(documentDto);
            tableView.setItems(getDocuments());
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(AlertMessages.DOCUMENT_REMOVED);
            alert.show();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

}
