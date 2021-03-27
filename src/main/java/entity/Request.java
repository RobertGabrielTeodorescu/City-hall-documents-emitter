package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="requests")

public class Request {

    @Id
    private String id;

    @Column
    private LocalDateTime requestTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Document document;

    @Column
    private String approved;

    @Column
    private int remainingRequests;

    public Request(){
        this.address=new Address();
        this.document=new Document();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
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

    public boolean equals(Object o){
        if(this==o)
            return true;
        if(!(o instanceof Request))
            return false;
        return id!=null && id.equals(((Request) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
