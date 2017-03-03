package org.auditioner.services.production;

import org.auditioner.services.production.Production;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductionResultSetMapper implements ResultSetMapper<Production> {
    @Override
    public Production map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Production production = new Production();
        production.setLocation("/auditioner/productions/" + resultSet.getLong("id"));
        production.setName(resultSet.getString("name"));
        production.setAuditionDate(resultSet.getDate("audition_date"));
        production.setSeason(resultSet.getString("season"));
        return production;
    }
}
