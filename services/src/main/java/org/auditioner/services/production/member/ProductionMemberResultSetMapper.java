package org.auditioner.services.production.member;


import org.auditioner.services.family.member.FamilyMember;
import org.auditioner.services.production.Production;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductionMemberResultSetMapper implements ResultSetMapper<ProductionMember> {


    @Override
    public ProductionMember map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        ProductionMember productionMember = new ProductionMember();
        return productionMember;
    }
}
