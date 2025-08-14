package pl.epicserwer.czolg.swgoh.battlebot.player;

import org.commons.AllyCode;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YY {
    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        SwgohPlayerAPI wp = new SwgohPlayerAPI();
        List<SwgohHero> swgohHeroes = wp.getHeroes(new AllyCode("864927446"));
        System.out.println(swgohHeroes);
    }
}
