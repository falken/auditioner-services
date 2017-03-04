package org.auditioner.services.production.member;


public class ProductionMember {
    private String familyMemberFirstName;
    private String familyMemberLastName;
    private String requestedRoles;
    private String auditionNumber;
    private String location;

    private String rehearsalConflicts;
    public String getFamilyMemberFirstName() {
        return familyMemberFirstName;
    }

    public void setFamilyMemberFirstName(String familyMemberFirstName) {
        this.familyMemberFirstName = familyMemberFirstName;
    }

    public String getFamilyMemberLastName() {
        return familyMemberLastName;
    }

    public void setFamilyMemberLastName(String familyMemberLastName) {
        this.familyMemberLastName = familyMemberLastName;
    }

    public String getRequestedRoles() {
        return requestedRoles;
    }

    public void setRequestedRoles(String requestedRoles) {
        this.requestedRoles = requestedRoles;
    }

    public String getRehearsalConflicts() {
        return rehearsalConflicts;
    }

    public void setRehearsalConflicts(String rehearsalConflicts) {
        this.rehearsalConflicts = rehearsalConflicts;
    }

    public String getAuditionNumber() {
        return auditionNumber;
    }

    public void setAuditionNumber(String auditionNumber) {
        this.auditionNumber = auditionNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
