package org.auditioner.services.production.member;


import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface ProductionMemberDAO {

    @SqlQuery("")
    @Mapper(ProductionMemberResultSetMapper.class)
    ProductionMember getProductionMemberByProductionId(@Bind("id") long productionMemberId);

    @SqlUpdate("")
    void updateProductionMember(@Bind("id") long productionMemberId, @BindBean("productionMember") ProductionMember productionMember);

    @SqlUpdate("")
    @GetGeneratedKeys
    long addProductionMember(@BindBean("productionMember") ProductionMember productionMember);

    @SqlUpdate("")
    void deleteProductionMember(@Bind("id") long productionMemberId, @BindBean("productionMember") ProductionMember productionMember);
}

