package com.choucar.apis.question;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;

public class EmployeeDataQuestions implements Question<Boolean> {

    private final int employeeId;

    private EmployeeDataQuestions(int employeeId) {
        this.employeeId = employeeId;
    }

    public static EmployeeDataQuestions hasDataForEmployee(int employeeId) {
        return new EmployeeDataQuestions(employeeId);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        var response = SerenityRest.lastResponse();
        var id = response.jsonPath().getInt("data.id");
        return response.statusCode() == 200 && id == this.employeeId;
    }
}
