package com.csiszer.my_shop.data;

import com.csiszer.my_shop.model.User;
import com.csiszer.my_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createDefaultUserIfNotExists();

    }

    private void createDefaultUserIfNotExists() {
        if (!userRepository.existsByEmail("testUser@gmail.com")) {
            User user = new User();
            user.setEmail("testUser@gmail.com");
            user.setFirstName("Test");
            user.setLastName("User");
            user.setPassword("password123");
            userRepository.save(user);
        }

    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
