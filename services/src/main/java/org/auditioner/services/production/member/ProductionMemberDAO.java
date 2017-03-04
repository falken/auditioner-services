package org.auditioner.services.production.member;

import org.auditioner.services.production.ProductionResultSetMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface ProductionMemberDAO {

    @SqlQuery("SELECT fm.id,fm.first_name,fm.last_name,pm.requested_roles,pm.rehearsal_conflicts,pm.audition_number,pm.production_id, pm.family_member_id " +
            "FROM ProductionMember pm join FamilyMember fm on (pm.family_member_id=fm.id) "+
            "WHERE pm.id=:id and pm.production_id=:productionId")
    @Mapper(ProductionMemberResultSetMapper.class)
    ProductionMember getProductionMember(@Bind("productionId") long productionId, @Bind("id") long productionMemberId);

    @SqlQuery("SELECT pm.id,p.Name,pm.requested_roles,pm.audition_number " +
            "FROM ProductionMember pm  "+
            "join Production p on pm.production_id=p.id "+
            "WHERE pm.family_member_id=:familyMemberId")
    @Mapper(ProductionMemberResultSetMapper.class)
    ProductionMember getMemberProductions(@Bind("familyMemberId") long familyMemberId);


    @SqlQuery("SELECT fm.id,fm.first_name,fm.last_name,pm.requested_roles,pm.rehearsal_conflicts,pm.audition_number,pm.production_id, pm.family_member_id " +
            "FROM ProductionMember pm join FamilyMember fm on (pm.family_member_id=fm.id) " +
            "WHERE pm.production_id=:productionId ORDER BY pm.audition_number")
    @Mapper(ProductionMemberResultSetMapper.class)
    List<ProductionMember> getProductionMembers(@Bind("productionId") long productionId);

    @SqlUpdate("UPDATE ProductionMember "+
            " SET audition_number=:productionMember.auditionNumber, "+
            "requested_roles=:productionMember.requestedRoles,"+
            "rehearsal_conflicts=:productionMember.rehearsalConflicts "+
            "WHERE id=:id")
    void updateProductionMember(@Bind("id") long productionMemberId, @BindBean("productionMember") ProductionMember productionMember);

    @SqlUpdate("INSERT INTO ProductionMember (family_member_id,production_id,requested_roles,rehearsal_conflicts,audition_number) "
            + " VALUES (:productionMember.familyMemberId,:productionId,:productionMember.requestedRoles,:productionMember.rehearsalConflicts,:productionMember.auditionNumber)")
    @GetGeneratedKeys
    long addProductionMember(@Bind("productionId") long productionId, @BindBean("productionMember") ProductionMember productionMember);

    @SqlUpdate("DELETE FROM ProductionMember "
            + "WHERE id=:id")
    void deleteProductionMember(@Bind("id") long productionMemberId);
}
