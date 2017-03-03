package org.auditioner.services.production;

import java.util.Date;

public class Production{
    private String name;
    private Date auditionDate;
    private String season;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAuditionDate() {
        return auditionDate;
    }

    public void setAuditionDate(Date auditionDate) {
        this.auditionDate = auditionDate;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSeason() {
        return season;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}