package org.auditioner.services.production.member;


import org.auditioner.services.family.member.FamilyMember;
import org.auditioner.services.production.Production;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductionMemberResultSetMapper implements ResultSetMapper<ProductionMember> {


    @Override
    public ProductionMember map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        ProductionMember productionMember = new ProductionMember();
        productionMember.setLocation("/auditioner/productions/"+resultSet.getLong("production_id")+ "/family_member/" + resultSet.getLong("id"));
        productionMember.setAuditionNumber(resultSet.getString("audition_number"));
        productionMember.setFamilyMemberFirstName(resultSet.getString("first_name"));
        productionMember.setFamilyMemberLastName(resultSet.getString("last_name"));
        productionMember.setRehearsalConflicts(resultSet.getString("rehearsal_conflicts"));
        productionMember.setRequestedRoles(resultSet.getString("requested_roles"));
        productionMember.setFamilyMemberId(resultSet.getLong("family_member_id"));

        return productionMember;
    }
}
