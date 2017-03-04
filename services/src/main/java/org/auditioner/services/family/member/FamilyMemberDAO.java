package org.auditioner.services.family.member;

import org.auditioner.services.family.FamilyResultSetMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface FamilyMemberDAO {


    @SqlQuery("SELECT fm.Id,fm.family_id,fm.age,fm.first_name,fm.last_name, fm.weight, fm.height, fm.past_roles " +
            " fm.acro_exp, fm.ballet_exp, fm.jazz_exp, fm.tap.exp "+
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
            "  past_roles=:familyMember.pastRoles, " +
            "  acro_exp=:familyMember.acroExp, " +
            "  ballet_exp=:familyMember.balletExp, " +
            "  jazz_exp=:familyMember.jazzExp, " +
            "  tap_exp=:familyMember.tapExp " +
            "WHERE id=:id and family_id=:familyId")
    void updateFamilyMember(@Bind("familyId") long familyId, @Bind("id") long familyMemberId,@BindBean("familyMember") FamilyMember familyMember);

    @SqlUpdate("INSERT INTO FamilyMember (family_id, first_name,last_name,weight,height,past_roles,age,acro_exp,ballet_exp,jazz_exp,tap_exp) "
            + " VALUES (:familyId, :familyMember.firstName,:familyMember.lastName,:familyMember.weight," +
            ":familyMember.height,:familyMember.pastRoles,:familyMember.age,familyMember.acroExp,familyMember.balletExp,familyMember.jazzExp,familyMember.tapExp)")
    @GetGeneratedKeys
    long addFamilyMember(@Bind("familyId") long familyId, @BindBean("familyMember") FamilyMember familyMember);

    @SqlUpdate("DELETE FROM FamilyMember "
            + "WHERE id=:id and family_id=:familyId")
    void deleteFamilyMember(@Bind("familyId") long familyId, @Bind("id") long familyMemberId);

    @SqlQuery("SELECT  fm.family_id, fm.Id,fm.first_name,fm.last_name, fm.weight, fm.height, fm.past_roles, fm.age, fm.acro_exp, fm.ballet_exp, fm.jazz_exp, fm.tap_exp " +
            "FROM FamilyMember fm " +
            "WHERE fm.family_id = :familyId")
    @Mapper(FamilyMemberResultSetMapper.class)
    List<FamilyMember> getFamilyMembers(@Bind("familyId") long familyId);

    @SqlQuery("SELECT fm.Id,fm.family_id,fm.age,fm.first_name,fm.last_name, fm.weight, fm.height, fm.past_roles " +
            "FROM FamilyMember fm " +
            "WHERE fm.id=:familyMemberId")
    @Mapper(FamilyMemberResultSetMapper.class)
    FamilyMember getFamilyMember(@Bind("familyMemberId") long familyMemberId);
}
