package org.auditioner.services.production.member;


import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface ProductionMemberDAO {

    @SqlQuery("SELECT prodMember")
    @Mapper(ProductionMemberResultSetMapper.class)
    ProductionMember getProductionMemberByProductionId(@Bind("id") long productionMemberId);
}
