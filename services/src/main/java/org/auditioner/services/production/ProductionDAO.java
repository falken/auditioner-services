package org.auditioner.services.production;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface ProductionDAO{
    @SqlUpdate("INSERT INTO Production (Name) "
            + " VALUES (:production.name)")
    @GetGeneratedKeys
    long addFamily(@BindBean("production") Production production);

    @SqlUpdate("DELETE FROM Production "
            + "WHERE id=:id")
    void deleteFamily(@Bind("id") long productionId);

    @SqlUpdate("UPDATE Production " +
            "  SET Name=:production.name " +
            "WHERE id=:id")
    void updateFamily(@Bind("id") long productionId,@BindBean("production") Production production);

    @SqlQuery("SELECT id, Name " +
            "FROM Production " +
            "WHERE id=:id")
    @Mapper(ProductionResultSetMapper .class)
    Production getProduction(@Bind("id") long familyId);
}