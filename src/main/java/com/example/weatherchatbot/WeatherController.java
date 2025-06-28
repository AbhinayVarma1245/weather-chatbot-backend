
package com.example.weatherchatbot;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class WeatherController {

    private final String API_KEY = System.getenv("API_KEY");

    @PostMapping("/chat")
    public String handleChat(@RequestBody String message) {
        String city = extractCityFromMessage(message);
        if (city == null) {
            return "Please specify a city like: What's the weather in Paris?";
        }

        try {
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                         "&appid=" + API_KEY + "&units=metric";

            RestTemplate restTemplate = new RestTemplate();
            Map response = restTemplate.getForObject(url, Map.class);

            Map<String, Object> main = (Map<String, Object>) response.get("main");
            List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
            Map<String, Object> weather = weatherList.get(0);
            Map<String, Object> wind = (Map<String, Object>) response.get("wind");

            return "In " + city + ", it's " + weather.get("main") + ", " +
                   main.get("temp") + "°C, " + main.get("humidity") + "% humidity.";
        } catch (Exception e) {
            return "Sorry, I couldn't get the weather for " + city + ".";
        }
    }

    private String extractCityFromMessage(String message) {
        message = message.toLowerCase();
        if (message.contains("weather in")) {
            return message.substring(message.indexOf("weather in") + 10).replaceAll("[^a-zA-Z ]", "").trim();
        }
        return null;
    }
}
