package org.auditioner.services.family;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyResultSetMapper implements ResultSetMapper<Family> {
    @Override
    public Family map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Family family = new Family();
        family.setLocation("/auditioner/families/" + resultSet.getLong("id"));
        family.setName(resultSet.getString("Name"));
        family.setEmail(resultSet.getString("Email"));
        family.setPreferredContactMethod(resultSet.getString("PreferredContactMethod"));
        family.setAddress01(resultSet.getString("address01"));
        family.setAddress02(resultSet.getString("address02"));
        family.setCity(resultSet.getString("City"));
        family.setState(resultSet.getString("State"));
        family.setZipCode(resultSet.getString("ZipCode"));
        return family;
    }
}
