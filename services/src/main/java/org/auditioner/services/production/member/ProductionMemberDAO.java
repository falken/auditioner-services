package org.auditioner.services.production.member;

import org.auditioner.services.production.ProductionResultSetMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface ProductionMemberDAO{
    ProductionMember getProductionMember(@Bind("id") long productionMemberId);
}