package com.choucar.apis.task;

import com.choucar.apis.endpoint.Endpoint;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class Employee implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {
        // Realizamos la solicitud POST
        actor.attemptsTo(Get.resource(Endpoint.API_EMPLOYEE.getEndpoint()));

    }


}
