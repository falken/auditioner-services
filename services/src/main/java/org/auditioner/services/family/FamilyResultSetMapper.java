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
//        family.setEmail(resultSet.getString("Email"));
//        family.setAddress(resultSet.getString("Address"));
//        family.setPreferredContactMethod(resultSet.getString("PreferredContactMethod"));
        return family;
    }
}
