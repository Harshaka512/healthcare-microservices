package com.healthcare.user.config;

import com.healthcare.user.model.User;
import com.healthcare.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class SampleDataLoader {

    private static final Logger log = LoggerFactory.getLogger(SampleDataLoader.class);

    @Bean
    CommandLineRunner loadSampleUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() > 0) {
                return;
            }
            User u1 = new User();
            u1.setName("Alice Johnson");
            u1.setEmail("alice.johnson@example.com");
            User u2 = new User();
            u2.setName("Bob Smith");
            u2.setEmail("bob.smith@example.com");
            userRepository.save(u1);
            userRepository.save(u2);
            log.info("Loaded 2 sample users");
        };
    }
}
