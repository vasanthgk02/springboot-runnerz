package org.vasanthgk02.freecodecamp_springboot.run;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class RunRepository {
    List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return this.runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream().filter(run -> run.id() == id).findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }

    boolean update(Run run) {
        Optional<Run> isPresent= findById(run.id());
        if (isPresent.isPresent()) {
            runs.set(runs.indexOf(isPresent.get()), run);
            return true;
        }
        return false;
    }

    void delete(Integer id) {
        runs.removeIf(run -> run.id() == id);
    }


    @PostConstruct
    private void init() {
        runs.add(new Run(1, "Vasanth", LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.HOURS),10, Location.OUTDOOR));
        runs.add(new Run(2, "Suhan", LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.HOURS),10, Location.OUTDOOR));
    }
}
