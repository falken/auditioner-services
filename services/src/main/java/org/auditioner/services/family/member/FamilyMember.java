package org.auditioner.services.family.member;

import java.util.List;

public class FamilyMember {
    private String firstName;
    private String lastName;
    private String weight;
    private List<String> previousRoles;
    private String yearsExperiance;

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

    public List<String> getPreviousRoles() {
        return previousRoles;
    }

    public void setPreviousRoles(List<String> previousRoles) {
        this.previousRoles = previousRoles;
    }

    public String getYearsExperiance() {
        return yearsExperiance;
    }

    public void setYearsExperiance(String yearsExperiance) {
        this.yearsExperiance = yearsExperiance;
    }
}
