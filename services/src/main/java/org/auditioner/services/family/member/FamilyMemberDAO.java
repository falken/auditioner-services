package org.auditioner.services.family.member;

import org.auditioner.services.family.FamilyResultSetMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface FamilyMemberDAO {

    /*
    @SqlQuery("SELECT fm.Id,fm.family_id,fm.first_name,fm.last_name, fm.weight, fm.height, fm.past_roles, fm.age " +
            "FROM FamilyMember fm " +
            "WHERE id=:id")
            */
    @SqlQuery("SELECT fm.Id,fm.family_id,fm.age,fm.first_name,fm.last_name, fm.weight, fm.height, fm.past_roles " +
            "FROM FamilyMember fm " +
            "WHERE fm.id=:id and fm.family_id = :familyId")
    @Mapper(FamilyMemberResultSetMapper.class)
    FamilyMember getFamilyMember(@Bind("familyId") long familyId, @Bind("id") long id);

    @SqlUpdate("UPDATE FamilyMember " +
            "  SET first_name=:familyMember.firstName, " +
            "  last_name=:familyMember.lastName, " +
            "  age=:familyMember.age, " +
            "  weight=:familyMember.weight, " +
            "  height=:familyMember.height, " +
            "  past_roles=:familyMember.pastRoles " +
            "WHERE id=:id")
    void updateFamilyMember(@Bind("id") long familyMemberId,@BindBean("familyMember") FamilyMember familyMember);

    @SqlUpdate("INSERT INTO FamilyMember (family_id, first_name,last_name,weight,height,past_roles,age) "
            + " VALUES (:familyId, :familyMember.firstName,:familyMember.lastName,:familyMember.weight,:familyMember.height,:familyMember.pastRoles,:familyMember.age)")
    @GetGeneratedKeys
    long addFamilyMember(@Bind("familyId") long familyId, @BindBean("familyMember") FamilyMember familyMember);

    @SqlUpdate("DELETE FROM FamilyMember "
            + "WHERE id=:id")
    void deleteFamilyMember(@Bind("id") long familyMemberId);

    @SqlQuery("SELECT  fm.family_id, fm.Id,fm.first_name,fm.last_name, fm.weight, fm.height, fm.past_roles, fm.age " +
            "FROM FamilyMember fm " +
            "WHERE fm.family_id = :familyId")
    @Mapper(FamilyMemberResultSetMapper.class)
    List<FamilyMember> getFamilyMembers(@Bind("familyId") long familyId);
}
