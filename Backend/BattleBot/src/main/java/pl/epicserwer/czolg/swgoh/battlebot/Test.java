package pl.epicserwer.czolg.swgoh.battlebot;

import org.commons.AllyCode;
import org.commons.NameId;
import org.commons.data.DataUserService;
import org.commons.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class Test {
    private final UserService userService;

    @Autowired
    public Test(UserService userService) {
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        NameId nameId = new NameId("dd");
        System.out.println(this.userService.getAllyCode(nameId));
        System.out.println(this.userService.getAllyCodes(nameId));

        System.out.println(this.userService.getAllyCode(nameId));
    }
}
