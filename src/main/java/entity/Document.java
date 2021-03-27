package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="documents")

public class Document {

    @Id
    private String id;

    @Column
    private String tip;


    @OneToMany(mappedBy = "document",orphanRemoval = true)
    private Set<Request> requests;

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public void addRequest(Request request){
        requests.add(request);
        request.setDocument(this);
    }

    public void removeRequest(Request request){
        requests.remove(request);
        request.setDocument(null);
    }

    public Document(){
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

}
