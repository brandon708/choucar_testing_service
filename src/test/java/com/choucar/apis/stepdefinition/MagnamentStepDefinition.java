package com.choucar.apis.stepdefinition;


import com.choucar.apis.endpoint.BaseUrl;
import com.choucar.apis.question.EmployeeDataQuestions;
import com.choucar.apis.question.EmployeeListQuestions;
import com.choucar.apis.task.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;

import static com.choucar.apis.question.CommonQuestion.httpStatusCode;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class MagnamentStepDefinition {
    @Steps
    BaseUrl base;


    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^que la API del (.*) está disponible$")
    public void ApiEmpleadoDisponible(String user) {
        theActorCalled(user);
        //Obtengo la base de la url
        theActorInTheSpotlight().whoCan(CallAnApi.at(base.getUrlBase()));

    }

    @When("solicito todos los datos de los empleados")
    public void solicitoTodosLosDatosDeLosEmpleados() {
        theActorInTheSpotlight().attemptsTo(new Employee());

        theActorInTheSpotlight().should(seeThat("the http status code", httpStatusCode(), Matchers.equalTo(200)));

    }

    @Then("debería recibir una lista de empleados.")
    public void deberíaRecibirUnaListaDeEmpleados() {
        theActorInTheSpotlight().should(seeThat(EmployeeListQuestions.isNotEmpty()));
    }


    @When("^solicito datos para empleado con id (.*)$")
    public void solicitoDatosParaEmpleadoConIdId(int id) {
        theActorInTheSpotlight().attemptsTo(EmployeeId.withData(id));
        theActorInTheSpotlight().should(seeThat("the http status code", httpStatusCode(), Matchers.equalTo(200)));

    }

    @Then("debería recibir datos para el empleado con ID (.*)$")
    public void deberíaRecibirDatosParaElEmpleadoConIDId(int id) {
        theActorInTheSpotlight().should(seeThat("Datos del empleado", EmployeeDataQuestions.hasDataForEmployee(id), equalTo(true)));
    }


    @When("creo un nuevo registro de empleado con los siguientes detalles:")
    public void creoUnNuevoRegistroDeEmpleadoConLosSiguientesDetalles(DataTable employeeData) {
        List<Map<String, String>> data = employeeData.asMaps(String.class, String.class);
        Map<String, String> employeeDetails = data.get(0);
        String nombre = employeeDetails.get("nombre");
        String salario = employeeDetails.get("salario");
        String edad = employeeDetails.get("edad");
        theActorInTheSpotlight().attemptsTo(CreateEmployee.withDetails(nombre, salario, edad));

    }

    @Then("se debe crear un nuevo registro de empleado.")
    public void seDebeCrearUnNuevoRegistroDeEmpleado() {
        theActorInTheSpotlight().should(seeThat("Verificar que el empleado fue creado", httpStatusCode(), Matchers.equalTo(200)));

    }

    @And("^existe un empleado con id (.*)$")
    public void existeUnEmpleadoConIdId(int id) {
        theActorInTheSpotlight().attemptsTo(EmployeeId.withData(id));
        theActorInTheSpotlight().should(seeThat("the http status code", httpStatusCode(), Matchers.equalTo(200)));

    }

    @When("^actualizo el registro de empleado con id (.*), con los siguientes detalles:$")
    public void actualizoElRegistroDeEmpleadoConIdIdConLosSiguientesDetalles(int id, DataTable employeeData) {
        // Convertir la DataTable en una lista de mapas
        List<Map<String, String>> data = employeeData.asMaps(String.class, String.class);
        // Asumiendo que hay una sola fila en la DataTable
        Map<String, String> employeeDetails = data.get(0);
        // Extraer los detalles del empleado de la fila
        String nombre = employeeDetails.get("nombre");
        String salario = employeeDetails.get("salario");
        String edad = employeeDetails.get("edad");

        // Realizar la acción de actualizar el empleado
        theActorInTheSpotlight().attemptsTo(UpdateEmployee.withDetails(id, nombre, salario, edad));
    }

    @Then("se debe actualizar el registro de empleado")
    public void seDebeActualizarElRegistroDeEmpleadoConIDId() {
        theActorInTheSpotlight().should(seeThat("the http status code", httpStatusCode(), Matchers.equalTo(200)));

    }

    @When("^elimino el registro de empleado con id (.*)$")
    public void eliminoElRegistroDeEmpleadoConIdId(int id) {
        theActorInTheSpotlight().attemptsTo(DeleteEmployee.withId(id));

    }

    @Then("se debe eliminar el registro de empleado")
    public void seDebeEliminarElRegistroDeEmpleado() {
        theActorInTheSpotlight().should(seeThat("the http status code", httpStatusCode(), Matchers.equalTo(200)));
    }
}
