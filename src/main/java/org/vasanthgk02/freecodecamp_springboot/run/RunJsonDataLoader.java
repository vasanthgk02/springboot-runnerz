package org.vasanthgk02.freecodecamp_springboot.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class RunJsonDataLoader implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    RunRepository runRepository;
    ObjectMapper objectMapper;

    public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(runRepository.count() == 0) {
            Run run = new Run(1, "Vasanth", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS),10, Location.OUTDOOR);
            runRepository.create(run);
        }
        else {
            log.info("Not bootstrapping the data");
        }

    }

}
