package org.auditioner.services.production;

import org.auditioner.services.production.ProductionResultSetMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface ProductionDAO{
    @SqlUpdate("INSERT INTO Production (name, audition_date, season) "
            + " VALUES (:production.name, :production.auditionDate, :production.season)")
    @GetGeneratedKeys
    long addProduction(@BindBean("production") Production production);

    @SqlUpdate("DELETE FROM Production "
            + "WHERE id=:id")
    void deleteProduction(@Bind("id") long productionId);

    @SqlUpdate("UPDATE Production " +
            "  SET Name=:production.name, " +
            "      audition_date=:production.auditionDate, " +
            "      season=:production.season " +
            "WHERE id=:id")
    void updateProduction(@Bind("id") long productionId,@BindBean("production") Production production);

    @SqlQuery("SELECT id, name, audition_date, season " +
            "FROM Production " +
            "WHERE id=:id")
    @Mapper(ProductionResultSetMapper.class)
    Production getProduction(@Bind("id") long productionId);

    @SqlQuery("SELECT id, name, audition_date, season " +
            "FROM Production")
    @Mapper(ProductionResultSetMapper.class)
    List<Production> getProductions();

    String lastAuditionNumberFor(long productionId);
}