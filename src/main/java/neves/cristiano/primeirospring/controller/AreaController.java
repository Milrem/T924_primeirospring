package neves.cristiano.primeirospring.controller;

import neves.cristiano.primeirospring.dto.Area;
import neves.cristiano.primeirospring.dto.ComodoRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("area")
public class AreaController {
    @GetMapping
    public Area getArea(ComodoRequest comodoRequest) {
        Area area = new Area();
        area.setComodo(comodoRequest);
        return area;
    }
}
