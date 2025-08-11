package pl.epicserwer.czolg.swgoh.battlebot.register;

import org.commons.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterConfiguration {
    private final UserService userService;

    @Autowired
    public RegisterConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public AppRegisterService appRegisterService() {
        return new AppRegisterService(userService);
    }
}
