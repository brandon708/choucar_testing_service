package com.choucar.apis.task;

import com.choucar.apis.endpoint.Endpoint;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteEmployee implements Task {

    private final int id;

    public DeleteEmployee(int id) {
        this.id = id;
    }

    public static DeleteEmployee withId(int id) {
        return instrumented(DeleteEmployee.class, id);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(Endpoint.API_DELETE.getEndpoint() + "/" + id)
        );
    }
}
