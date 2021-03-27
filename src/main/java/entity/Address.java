package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="addresses")

public class Address {

    @Id
    private String id;

    @Column
    private String city;

    @Column
    private String county;

    @Column
    private String street;

    @Column
    private int nr;

    @Column
    private char block;

    @Column
    private int floor;

    @Column
    private int apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;



    @OneToMany(mappedBy = "address",orphanRemoval = true)
    private Set<Request> requests;

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address(){
        this.user=new User();
        this.requests=new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public char getBlock() {
        return block;
    }

    public void setBlock(char block) {
        this.block = block;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public boolean equals(Object o){
        if(this==o)
            return true;
        if(!(o instanceof Address))
            return false;
        return id!=null && id.equals(((Address) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addRequest(Request request){
        requests.add(request);
        request.setAddress(this);
    }

    public void removeRequest(Request request){
        requests.remove(request);
        request.setAddress(null);
    }

}
