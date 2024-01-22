package com.choucar.apis.task;

import com.choucar.apis.endpoint.Endpoint;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateEmployee implements Task {

    private final int id;
    private final String nombre;
    private final String salario;
    private final String edad;

    public UpdateEmployee(int id, String nombre, String salario, String edad) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
        this.edad = edad;
    }


    public static UpdateEmployee withDetails(int id,String nombre, String salario, String edad) {
        return instrumented(UpdateEmployee.class, id, nombre, salario, edad);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(Endpoint.API_UPDATE.getEndpoint() + "/" + id)
                        .with(requestSpecification -> requestSpecification
                                .relaxedHTTPSValidation()
                                .contentType("application/json")
                                .body(requestBody())
                        )
        );
    }


    private String requestBody() {
        return String.format(
                "{\"name\":\"%s\",\"salary\":\"%s\",\"age\":\"%s\"}",
                nombre, salario, edad
        );
    }
}
