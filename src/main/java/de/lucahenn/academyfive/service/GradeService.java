package de.lucahenn.academyfive.service;

import org.springframework.stereotype.Service;

import de.lucahenn.academyfive.constant.GradeConstants;
import de.lucahenn.academyfive.model.Login;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<List<String>> returnGrades(Login login) {
        try {
            Document doc = Jsoup.connect(GradeConstants.GRADE_URL.getConstant())
                    .cookies(returnLoginResponse(login).cookies())
                    .post();
            ArrayList<List<String>> selectedObjects = new ArrayList<>();
            doc.select(
                    "#tabs-content-0 > div > div > div > div > div.panel-body > div > table > tbody > tr[style=font-weight: bold]")
                    .forEach(element -> {
                        ArrayList<String> list = new ArrayList<String>();
                        element.select("td").forEach(selected -> {
                            list.add(selected.text());
                        });
                        selectedObjects.add(list);
                    });
            return selectedObjects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
