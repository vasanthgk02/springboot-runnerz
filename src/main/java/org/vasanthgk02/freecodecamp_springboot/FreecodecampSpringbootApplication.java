package org.vasanthgk02.freecodecamp_springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.vasanthgk02.freecodecamp_springboot.run.Location;
import org.vasanthgk02.freecodecamp_springboot.run.Run;
import org.vasanthgk02.freecodecamp_springboot.run.RunRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@SpringBootApplication
public class FreecodecampSpringbootApplication {

    private static final Logger log = LoggerFactory.getLogger(FreecodecampSpringbootApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(FreecodecampSpringbootApplication.class, args);

    }


}
