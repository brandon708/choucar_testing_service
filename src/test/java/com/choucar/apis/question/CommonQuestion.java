package com.choucar.apis.question;


import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class CommonQuestion {




    public static Question<Integer> httpStatusCode() {
        return Question.about("The http status code")
                .answeredBy(
                        actor -> SerenityRest.lastResponse().getStatusCode()
                );
    }


}
