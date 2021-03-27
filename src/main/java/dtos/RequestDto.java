package dtos;


import entity.Address;
import entity.Document;

import java.time.LocalDateTime;

public class RequestDto {

    private String id;

    private LocalDateTime requestTime;

    private AddressDto address;

    private DocumentDto document;

    private String approved;

    private int remainingRequests;

    private String addressToString;

    private String documentType;

    private String requestTimeString;

    private String userName;

    public RequestDto(){
        address=new AddressDto();
        document=new DocumentDto();
    }

    public String getAddressToString() {
        return addressToString;
    }

    public void setAddressToString(String addressToString) {
        this.addressToString = addressToString;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public DocumentDto getDocument() {
        return document;
    }

    public void setDocument(DocumentDto document) {
        this.document = document;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public int getRemainingRequests() {
        return remainingRequests;
    }

    public void setRemainingRequests(int remainingRequests) {
        this.remainingRequests = remainingRequests;
    }

    public String getRequestTimeString() {
        return requestTimeString;
    }

    public void setRequestTimeString(String requestTimeString) {
        this.requestTimeString = requestTimeString;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
