package dtos;

import entity.Address;
import entity.Request;

import java.util.HashSet;
import java.util.Set;

public class DocumentDto {

    private String id;

    private String tip;

    private Set<Address> addresses;

    private Set<Request> requests;

    public DocumentDto(){
        addresses=new HashSet<>();
        requests=new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }
}
