package org.vasanthgk02.runnerz.run;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class RunRepository {
    Logger logger = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbclient;

    RunRepository(JdbcClient jdbclient) {
        this.jdbclient = jdbclient;
    }

    List<Run> findAll() {
        return jdbclient.sql("SELECT * FROM Run")
                .query(Run.class)
                .list();
    }

    Optional<Run> findById(Integer id) {
        return jdbclient.sql("SELECT * FROM run WHERE run.id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        jdbclient.sql("INSERT INTO  Run (id, title, started_on, ended_on, miles, location) VALUES (?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.endedOn(), run.miles(), run.location().toString()))
                .update();
    }

    void update(Run run) {
        Optional<Run> existingRecord = this.findById(run.id());
        if(!existingRecord.isPresent()) return;
        logger.info(existingRecord.toString());
        logger.info(run.toString());
        jdbclient.sql("UPDATE Run SET title = ?, started_on = ?, ended_on = ?, miles = ?, location = ? WHERE id = ?")
                .params(List.of(
                        run.title() == null ? existingRecord.get().title() : run.title(),
                        run.startedOn() == null ? existingRecord.get().startedOn() : run.startedOn(),
                        run.endedOn() == null ? existingRecord.get().endedOn() : run.endedOn(),
                        run.miles() == null ? existingRecord.get().miles() : run.miles(),
                        run.location() == null ? existingRecord.get().location().toString() : run.location().toString(), run.id()))
                .update();
    }

    void delete(Integer id) {
        int deleteStats = jdbclient.sql("DELETE FROM run WHERE id = ?")
                .params(List.of(id)).update();
        Assert.state(deleteStats == 1, "Failed to delete record: " + id);
    }

    int count() {
        return jdbclient.sql("SELECT * from Run").query().listOfRows().size();
    }

    void saveAll(List<Run> runs) {
        runs.forEach(this::create);
    }

    List<Run> findByLocation(String location) {
        return jdbclient.sql("SELECT * FROM run WHERE location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }

}
