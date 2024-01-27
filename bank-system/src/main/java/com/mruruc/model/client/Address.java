package com.mruruc.model.client;

public class Address {
    private Long addressID;
    private String country;
    private String city;
    private String street;
    private String zip;
    private Integer doorNo;

    public Address(String country, String city, String street, String zip, Integer doorNo) {

        this.country=country;
        this.city = city;
        this.street = street;
        this.zip = zip;
        this.doorNo = doorNo;
    }
    public Address(Long addressID, String country, String city, String street, String zip, Integer doorNo) {
        this.addressID = addressID;
        this.country=country;
        this.city = city;
        this.street = street;
        this.zip = zip;
        this.doorNo = doorNo;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Integer getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(Integer doorNo) {
        this.doorNo = doorNo;
    }

    @Override
    public String toString() {
        return "Address{" +
                "adrs_id=" + addressID +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", doorNo=" + doorNo +
                '}';
    }
}
