package com.example.weather;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class WeatherController {

    @GetMapping("/weather")
    public String getWeather(@RequestParam String location) {
        return "Weather in " + location + ": Sunny, 25°C";
    }
}
