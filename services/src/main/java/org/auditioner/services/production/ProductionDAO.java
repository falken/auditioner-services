package org.auditioner.services.production;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface ProductionDAO{
    @SqlUpdate("INSERT INTO Production (name, audition_date, season, age_cutoff_date) "
            + " VALUES (:production.name, :production.auditionDate, :production.season,:production.ageCutoffDate)")
    @GetGeneratedKeys
    long addProduction(@BindBean("production") Production production);

    @SqlUpdate("DELETE FROM Production "
            + "WHERE id=:id")
    void deleteProduction(@Bind("id") long productionId);

    @SqlUpdate("UPDATE Production " +
            "  SET Name=:production.name, " +
            "      audition_date=:production.auditionDate, " +
            "      season=:production.season, " +
            "      age_cutoff_date=:production.ageCutoffDate " +
            "WHERE id=:id")
    void updateProduction(@Bind("id") long productionId,@BindBean("production") Production production);

    @SqlQuery("SELECT id, name, audition_date, season, age_cutoff_date " +
            "FROM Production " +
            "WHERE id=:id")
    @Mapper(ProductionResultSetMapper.class)
    Production getProduction(@Bind("id") long productionId);

    @SqlQuery("SELECT id, name, audition_date, season, age_cutoff_date " +
            "FROM Production")
    @Mapper(ProductionResultSetMapper.class)
    List<Production> getProductions();

    @SqlQuery("SELECT MAX(RIGHT(audition_number, 2)) " +
            "FROM ProductionMember " +
            "WHERE production_id = :id AND LEFT(RIGHT('0000'+audition_number,4),2) = :age")
    String lastAuditionNumberFor(@Bind("id") long productionId,@Bind("age") int age);
}