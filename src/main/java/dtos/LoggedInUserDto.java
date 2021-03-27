package dtos;

import java.util.HashSet;
import java.util.Set;

public class LoggedInUserDto {

    private String id;
    private Set<AddressDto> addressSet;

    public LoggedInUserDto(){
        addressSet=new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<AddressDto> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<AddressDto> addressSet) {
        this.addressSet = addressSet;
    }
}
