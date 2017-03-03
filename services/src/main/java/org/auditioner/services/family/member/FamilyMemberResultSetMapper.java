package org.auditioner.services.family.member;

import org.auditioner.services.family.Family;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyMemberResultSetMapper  implements ResultSetMapper<FamilyMember> {
    @Override
    public FamilyMember map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        FamilyMember familyMember = new FamilyMember();
        return familyMember;
    }
}
