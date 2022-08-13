package de.lucahenn.academyfive.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.lucahenn.academyfive.model.Login;
import de.lucahenn.academyfive.service.GradeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AcademyFiveController {

    private GradeService loginService;

    @PostMapping("/grades")
    private Object index(@RequestBody Login login) {
        return loginService.returnGrades(login);
    }
}
