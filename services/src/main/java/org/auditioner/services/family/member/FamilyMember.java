package org.auditioner.services.family.member;

public class FamilyMember {
    private String firstName;
    private String lastName;
    private String weight;
    private String pastRoles;
    private String location;
    private String height;
    private String birthDate;
    private int acroExp;
    private int balletExp;
    private int jazzExp;
    private int tapExp;

    public int getAcroExp() {
        return acroExp;
    }

    public void setAcroExp(int acroExp) {
        this.acroExp = acroExp;
    }

    public int getBalletExp() {
        return balletExp;
    }

    public void setBalletExp(int balletExp) {
        this.balletExp = balletExp;
    }

    public int getJazzExp() {
        return jazzExp;
    }

    public void setJazzExp(int jazzExp) {
        this.jazzExp = jazzExp;
    }

    public int getTapExp() {
        return tapExp;
    }

    public void setTapExp(int tapExp) {
        this.tapExp = tapExp;
    }

    public String getPastRoles() { return pastRoles; }

    public String getLocation() { return location; }

    public String getHeight() { return height; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setLocation(String location) { this.location = location; }

    public void setHeight(String height) { this.height = height; }

    public void setPastRoles(String past_roles) { this.pastRoles = past_roles; }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
