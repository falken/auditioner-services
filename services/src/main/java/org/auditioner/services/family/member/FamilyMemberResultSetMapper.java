package org.auditioner.services.family.member;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyMemberResultSetMapper  implements ResultSetMapper<FamilyMember> {
    @Override
    public FamilyMember map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        FamilyMember familyMember = new FamilyMember();
        familyMember.setLocation("/auditioner/families/"+resultSet.getLong("family_id")+ "/family_member/" + resultSet.getLong("id"));
        familyMember.setFirstName(resultSet.getString("first_name"));
        familyMember.setLastName(resultSet.getString("last_name"));
        familyMember.setAge(resultSet.getString("age"));
        familyMember.setWeight(resultSet.getString("weight"));
        familyMember.setHeight(resultSet.getString("height"));
        familyMember.setPastRoles(resultSet.getString("past_roles"));
        familyMember.setAcroExp(resultSet.getInt("acro_exp"));
        familyMember.setBalletExp(resultSet.getInt("ballet_exp"));
        familyMember.setJazzExp(resultSet.getInt("jazz_exp"));
        familyMember.setTapExp(resultSet.getInt("tap_exp"));
        return familyMember;
    }
}
