package de.lucahenn.academyfive.service;

import de.lucahenn.academyfive.model.Grade;
import de.lucahenn.academyfive.model.GradeList;
import org.springframework.stereotype.Service;

import de.lucahenn.academyfive.constant.GradeConstants;
import de.lucahenn.academyfive.model.Login;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import org.jsoup.*;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

@Service
public class GradeService {

    private Response returnLoginResponse(Login login) {
        try {
            return Jsoup.connect(GradeConstants.LOGIN_URL.getConstant())
                    .data("login-referrer", "")
                    .data("user", login.getUsername())
                    .data("password", login.getPassword())
                    .followRedirects(true)
                    .method(Connection.Method.POST)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Cacheable(value="grades",key="#login.username")
    public GradeList returnGrades(Login login) {
        try {
            Document doc = Jsoup.connect(GradeConstants.GRADE_URL.getConstant())
                    .cookies(Objects.requireNonNull(returnLoginResponse(login)).cookies())
                    .post();
            ArrayList<Grade> selectedObjects = new ArrayList<>();
            GradeList gradeList = new GradeList();
            doc.select(
                    "#tabs-content-0 > div > div > div > div > div.panel-body > div > table > tbody > tr")
                    .forEach(element -> {
                        ArrayList<String> list = new ArrayList<String>();
                        Grade grade = new Grade();
                        element.select("td").forEach(selected -> {
                            list.add(selected.text());
                            grade.setGrade(list);
                        });
                        selectedObjects.add(grade);
                    });
            gradeList.setGradeList(selectedObjects);
            return gradeList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
