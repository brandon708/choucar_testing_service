package com.choucar.apis.task;

import com.choucar.apis.endpoint.Endpoint;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EmployeeId implements Task {

private final int id;

    public EmployeeId(int id) {
        this.id = id;
    }

    public static Performable withData(int id) {
        return instrumented(EmployeeId.class, id);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        // Realizamos la solicitud POST
        actor.attemptsTo(Get.resource(Endpoint.API_EMPLOYEE_ID.getEndpoint() + "/" + id));

    }


}
