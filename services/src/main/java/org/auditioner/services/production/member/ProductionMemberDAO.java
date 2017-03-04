package org.auditioner.services.production.member;

import org.auditioner.services.production.ProductionResultSetMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface ProductionMemberDAO {

    @SqlQuery("SELECT fm.first_name,fm.last_name,pm.requested_roles,pm.audition_number" +
            "FROM ProductionMember pm join FamilyMember fm on pm.family_member_id=fm.id "+
            "WHERE id=:id")
    @Mapper(ProductionMemberResultSetMapper.class)
    ProductionMember getProductionMember(@Bind("id") long productionMemberId);

    @SqlQuery("SELECT fm.first_name,fm.last_name,pm.requested_roles,pm.audition_number" +
            "FROM ProductionMember pm join FamilyMember fm on pm.family_member_id=fm.id")
    @Mapper(ProductionMemberResultSetMapper.class)
    List<ProductionMember> getProductionMembers();

    @SqlUpdate("UPDATE ProductionMember "+
            " SET audition_number=:productionMember.auditionNumber, "+
            "requested_roles=:productionMember.requestedRoles,"+
            "rehearsal_conflicts=:productionMember.rehearsalConflicts"+
            "WHERE id=:id")
    void updateProductionMember(@Bind("id") long productionMemberId, @BindBean("productionMember") ProductionMember productionMember);

    @SqlUpdate("INSERT INTO ProductionMember (id,family_member_id,production_id,requested_roles,rehearsal_conflicts,audition_number) "
            + " VALUES (:productionMember.id,:productionMember.familyMemberId,:productionMember.productionId,:productionMember.requestedRoles,:productionMember.rehearsalConflicts,:productionMember.auditionNumber)")
    @GetGeneratedKeys
    long addProductionMember(@BindBean("productionMember") ProductionMember productionMember);

    @SqlUpdate("DELETE FROM ProductionMember "
            + "WHERE id=:id")
    void deleteProductionMember(@Bind("id") long productionMemberId);
}
