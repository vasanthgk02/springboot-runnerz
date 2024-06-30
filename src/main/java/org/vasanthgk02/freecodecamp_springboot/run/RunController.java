package org.vasanthgk02.freecodecamp_springboot.run;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    RunRepository runRepository;

    @Autowired
    RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("/getAll")
    List<Run> getAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> runObj =  runRepository.findById(id);
        if(runObj.isEmpty()) {
            throw new RunNotFoundException();
        }
        return runObj.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    void create(@Valid @RequestBody Run run) {
        runRepository.create(run);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(run);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }

}
