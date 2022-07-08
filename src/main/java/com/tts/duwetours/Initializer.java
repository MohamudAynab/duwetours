package com.tts.duwetours;


import com.tts.duwetours.model.Event;
import com.tts.duwetours.model.Group;
import com.tts.duwetours.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {


    private final GroupRepository repository;
    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run (String... strings) {
        Stream.of("Seattle JUG", "Denver JUG", "Dubai JUG", "Doha JUG").forEach(name -> repository.save(new Group(name)));

        Group djug = repository.findByName("Seattle JUG");
        Event e = Event.builder().title("Micro FrontEnd for Java Developers")
                .description("JHipster now had microfrontend support!")
                .date(Instant.parse("2022-09-13T17:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
    }
}
