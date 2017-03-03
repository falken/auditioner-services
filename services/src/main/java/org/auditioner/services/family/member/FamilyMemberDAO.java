package org.auditioner.services.family.member;

import org.auditioner.services.family.Family;
import org.auditioner.services.family.FamilyResultSetMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface FamilyMemberDAO {

    @SqlQuery("SELECT f.id,fm.Id,fm.first_name,fm.last_name, fm.weight, fm.height, fm.roles" +
            "FROM FamilyMember fm join Family f on f.id = fm.family_id,fed.dance_name,fed.experience" +
            " join FamilyExpDance fed on fed.family_member_id = fm.id " +
            "WHERE id=:id")
    @Mapper(FamilyResultSetMapper.class)
    FamilyMember getFamilyMemberByFamilyId(@Bind("id") long familyMemberId);

    @SqlUpdate("UPDATE FamilyMember " +
            "  SET Name=:family.name " +
            "WHERE id=:id")
    @Mapper(FamilyResultSetMapper.class)
    FamilyMember updateFamilyMember(@Bind("id") long familyMemberId,@BindBean("familyMember") FamilyMember familyMember);

    @SqlUpdate("INSERT INTO FamilyMember (Name) "
            + " VALUES (:family.name)")
    @GetGeneratedKeys
    long addFamilyMember(@BindBean("familyMember") FamilyMember familyMember);

    @SqlUpdate("DELETE FROM FamilyMember "
            + "WHERE id=:id")
    void deleteFamilyMember(@Bind("id") long familyMemberId);


}
