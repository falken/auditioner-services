package org.auditioner.services.production.member;


import org.auditioner.services.family.member.FamilyMember;
import org.auditioner.services.production.Production;

public class ProductionMember {
    private Production production;
    private FamilyMember familyMember;
    private String requestedRoles;
    private String auditionNumber;
    private String location;

    private String rehearsalConflicts;

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
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
