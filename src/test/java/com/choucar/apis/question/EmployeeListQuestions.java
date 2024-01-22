package com.choucar.apis.question;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;
import static org.hamcrest.Matchers.*;

public class EmployeeListQuestions implements Question<Boolean> {

    public static EmployeeListQuestions isNotEmpty() {
        return new EmployeeListQuestions();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        var response = SerenityRest.lastResponse();
        var dataNotEmpty = !response.jsonPath().getList("data").isEmpty();
        return response.statusCode() == 200 && dataNotEmpty;
    }
}
