package utils;

import dtos.AddressDto;


public class AddressUtils {

    public static String convertToString(AddressDto address){
        return "City: "+address.getCity()+" County: "+address.getCounty()+" Street: "+address.getStreet()+
                " Nr: "+address.getNr()+" Block: "+address.getBlock()+" Floor: "+address.getFloor()+
                " Apartment: "+address.getApartment();
    }

}
