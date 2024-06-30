package org.vasanthgk02.freecodecamp_springboot.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;
import org.vasanthgk02.freecodecamp_springboot.FreecodecampSpringbootApplication;

import java.time.LocalDateTime;


public record Run(
    @NotNull @Id Integer id,
    @NotNull @NotEmpty String title,
    @NotNull LocalDateTime startedOn,
    @NotNull LocalDateTime endedOn,
    @NotNull @Positive Integer miles,
    @NotNull Location location) {

    static Logger logger = LoggerFactory.getLogger(FreecodecampSpringbootApplication.class);
    public Run {
        if(!endedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("Started on different time");
        }
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public LocalDateTime startedOn() {
        return startedOn;
    }

    @Override
    public LocalDateTime endedOn() {
        return endedOn;
    }

    @Override
    public Integer miles() {
        return miles;
    }

    @Override
    public Location location() {
        return location;
    }
}
