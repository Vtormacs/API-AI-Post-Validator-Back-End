package com.AI_Posts.Controller.AI;

import com.AI_Posts.Service.AI.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ia")
public class GeminiController {

    @Autowired
    private GeminiService geminiService;

    @GetMapping("/validate")
    public ResponseEntity<String> validateInput(@RequestParam String userInput) {

        return ResponseEntity.ok(geminiService.validadeAI(userInput));
    }
}
