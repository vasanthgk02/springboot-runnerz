package org.vasanthgk02.runnerz.run;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private Logger logger = LoggerFactory.getLogger(RunController.class);
    RunRepository runRepository;

    @Autowired
    RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("/getAllRun")
    List<Run> getAll() {
        return runRepository.findAll();
    }

    @GetMapping("/findRunById/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> runObj =  runRepository.findById(id);
        if(runObj.isEmpty()) {
            throw new RunNotFoundException();
        }
        return runObj.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createRun")
    void create(@Valid @RequestBody Run run) {
        runRepository.create(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/updateRun/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(run);
    }

    @DeleteMapping("/deleteRun/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }

    @GetMapping("/getCount")
    int count() {
        return runRepository.count();
    }

    @PostMapping("/saveAllRun")
    void saveAll(@Valid @RequestBody List<Run> runs) {
        runRepository.saveAll(runs);
    }

    @GetMapping("/findRunByLocation/{location}")
    List<Run> findByLocation(@PathVariable String location) {
        return runRepository.findByLocation(location);
    }

}
