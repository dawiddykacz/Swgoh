package pl.epicserwer.czolg.swgoh.battlebot.player;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class SwgohHero {
    private final String name;
    private final int level;
    private final int gear;
    private final int relic;
    private final long power;
}
