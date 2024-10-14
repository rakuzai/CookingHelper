package com.example.CookHelper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Controller
public class CookHelperController {

    private final String BASE_API_URL = "https://www.themealdb.com/api/json/v1/1/";

    @GetMapping("/")
    public String searchMeals(@RequestParam(name = "ingredient", required = false) String ingredient, Model model) {
        if (ingredient != null) {
            String url = BASE_API_URL + "filter.php?i=" + ingredient;
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            model.addAttribute("meals", response.get("meals"));
        }
        return "index";
    }

    @GetMapping("/meal")
    public String mealDetails(@RequestParam(name = "id") String mealId, Model model) {
        String url = BASE_API_URL + "lookup.php?i=" + mealId;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        model.addAttribute("meal", response.get("meals"));
        return "meal";
    }
}
