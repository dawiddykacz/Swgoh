package pl.epicserwer.czolg.swgoh.battlebot.controllers.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.epicserwer.czolg.swgoh.battlebot.controllers.v1.dto.RegisterAllyCodeDto;
import pl.epicserwer.czolg.swgoh.battlebot.controllers.v1.dto.RegisterAllyCodeResponseDto;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterAllyCodeController {

    @PostMapping
    public ResponseEntity<RegisterAllyCodeResponseDto> addAllyCode(@RequestBody RegisterAllyCodeDto registerAllyCodeDto) {
        return null;
    }
}
