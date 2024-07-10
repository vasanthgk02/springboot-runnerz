package org.vasanthgk02.runnerz.run;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class RunJsonDataLoader implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    RunRepository runRepository;
    ObjectMapper objectMapper;


    @Autowired
    public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(runRepository.count() == 0) {
            String filePath = "data/runs.json";
            Resource resource = new ClassPathResource(filePath);
            if (!resource.exists()) {
                throw new RuntimeException("Resource not found: " + filePath);
            }
            else log.info("File is present: " + filePath);
            try(InputStream in = resource.getInputStream()) {
                Runs allRuns = objectMapper.readValue(in, Runs.class);
                runRepository.saveAll(allRuns.runs());
            }
//            catch(Exception e) {
////                throw new RuntimeException("Failed to load runs.json", e);
//            }
        }
        else {
            log.info("Not bootstrapping the data");
        }

    }

}
