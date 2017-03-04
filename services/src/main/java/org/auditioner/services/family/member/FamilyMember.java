package org.auditioner.services.family.member;

public class FamilyMember {
    private String firstName;
    private String lastName;
    private String weight;
    private String pastRoles;
    private String yearsExperience;
    private String location;
    private String height;

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

    public String getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(String yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public void setLocation(String location) { this.location = location; }

    public void setHeight(String height) { this.height = height; }

    public void setPastRoles(String past_roles) { this.pastRoles = past_roles; }
}
