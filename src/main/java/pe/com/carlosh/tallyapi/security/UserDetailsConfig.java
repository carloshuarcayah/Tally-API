package pe.com.carlosh.tallyapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pe.com.carlosh.tallyapi.exception.ResourceNotFoundException;
import pe.com.carlosh.tallyapi.user.UserRepository;

@Configuration
@RequiredArgsConstructor
public class UserDetailsConfig {
    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}