package org.auditioner.services.family;

import org.auditioner.services.family.member.FamilyMember;

public class Family {
    private String location;
    private String name;
    private String email;
    private String phone;
    private String address01;
    private String address02;
    private String city;
    private String state;
    private String zipCode;
    private String preferredContactMethod;

    public String getPreferredContactMethod() { return preferredContactMethod; }

    public void setPreferredContactMethod(String preferredContactMethod) {
        this.preferredContactMethod = preferredContactMethod;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress01() {
        return address01;
    }

    public void setAddress01(String address01) {
        this.address01 = address01;
    }

    public String getAddress02() {
        return address02;
    }

    public void setAddress02(String address02) {
        this.address02 = address02;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
