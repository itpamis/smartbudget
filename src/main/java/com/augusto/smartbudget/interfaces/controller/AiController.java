package com.augusto.smartbudget.interfaces.controller;

import com.augusto.smartbudget.infrastructure.ai.AiAssistantService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiAssistantService assistantService;

    public AiController(AiAssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @PostMapping("/chat")
    public AiResponse chat(@RequestBody ChatRequest request) {
        return new AiResponse(assistantService.ask(request.message()));
    }

    public record ChatRequest(@NotBlank String message) {}
    public record AiResponse(String response) {}
}
