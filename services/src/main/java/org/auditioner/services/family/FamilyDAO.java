package org.auditioner.services.family;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface FamilyDAO {
/*
    private String name;
    private String email;
    private String phone;
    private String address01;
    private String address02;
    private String city;
    private String state;
    private String zipCode;
    private String preferredContactMethod;
 */
    @SqlUpdate("INSERT INTO Family (`Name`,Email,Phone,Address01,Address02,City,State,ZipCode,PreferredContactMethod) "
            + " VALUES (:family.name, :family.email, :family.phone, :family.address01, :family.address02, :family.city," +
              " :family.state, :family.zipCode, :family.preferredContactMethod )")
    @GetGeneratedKeys
    long addFamily(@BindBean("family") Family family);

    @SqlUpdate("DELETE FROM Family "
             + "WHERE id=:id")
    void deleteFamily(@Bind("id") long familyId);

    @SqlUpdate("UPDATE Family " +
            "  SET Name=:family.name, " +
            "      Email=:family.email, " +
            "      Phone=:family.phone, " +
            "      Address01=:family.address01, " +
            "      Address02=:family.address02, " +
            "      City=:family.city, " +
            "      State=:family.state, " +
            "      ZipCode=:family.zipCode, " +
            "      PreferredContactMethod=:family.preferredContactMethod " +
               "WHERE id=:id")
    void updateFamily(@Bind("id") long familyId,@BindBean("family") Family family);

    @SqlQuery("SELECT Id,Name,Email,Phone,Address01,Address02,City,State,ZipCode,PreferredContactMethod " +
              "FROM Family")
    @Mapper(FamilyResultSetMapper.class)
    List<Family> getFamilies();

    @SqlQuery("SELECT Id,Name,Email,Phone,Address01,Address02,City,State,ZipCode,PreferredContactMethod " +
              "FROM Family " +
              "WHERE id=:id")
    @Mapper(FamilyResultSetMapper.class)
    Family getFamily(@Bind("id") long familyId);
}

