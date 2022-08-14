package de.lucahenn.academyfive.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.lucahenn.academyfive.model.Login;
import de.lucahenn.academyfive.service.GradeService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AcademyFiveController {

    private GradeService loginService;

    @GetMapping
    public String index() {
        return "Welcome to the Community API for the ASW, the path you're searching for is POST /api/v1/grades :)";
    }

    @PostMapping("/api/v1/grades")
    public Object getGrades(@RequestBody Login login) {
        return loginService.returnGrades(login);
    }
}
