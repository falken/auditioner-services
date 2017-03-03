package org.auditioner.services.production;

import org.auditioner.services.production.ProductionResultSetMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface ProductionDAO{
    @SqlUpdate("INSERT INTO Production (Name) "
            + " VALUES (:production.name)")
    @GetGeneratedKeys
    long addProduction(@BindBean("production") Production production);

    @SqlUpdate("DELETE FROM Production "
            + "WHERE id=:id")
    void deleteProduction(@Bind("id") long productionId);

    @SqlUpdate("UPDATE Production " +
            "  SET Name=:production.name " +
            "WHERE id=:id")
    void updateProduction(@Bind("id") long productionId,@BindBean("production") Production production);
    
}