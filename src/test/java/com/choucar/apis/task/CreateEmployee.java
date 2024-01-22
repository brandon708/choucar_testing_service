package com.choucar.apis.task;

import com.choucar.apis.endpoint.Endpoint;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateEmployee implements Task {

    private final String nombre;
    private final String salario;
    private final String edad;

    public CreateEmployee(String nombre, String salario, String edad) {
        this.nombre = nombre;
        this.salario = salario;
        this.edad = edad;
    }


    public static Performable withDetails(String nombre, String salario, String edad) {
        return instrumented(CreateEmployee.class, nombre,salario,edad);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Post.to(Endpoint.API_CREATE.getEndpoint())
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
