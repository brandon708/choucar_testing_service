@Dummy
Feature: Employee Management API

  @GetAll
  Scenario: Obtener todos los datos de los empleados
    Given que la API del empleado está disponible
    When  solicito todos los datos de los empleados
    Then  debería recibir una lista de empleados.

  @GetId
  Scenario Outline: Obtener datos de un solo empleado
    Given que la API del empleado está disponible
    When  solicito datos para empleado con id <id>
    Then  debería recibir datos para el empleado con ID <id>

    Examples:
      | id |
      | 1  |

  @Post
  Scenario Outline: Crear un nuevo registro de empleado
    Given que la API del empleado está disponible
    When creo un nuevo registro de empleado con los siguientes detalles:
      | nombre   | salario   | edad   |
      | <nombre> | <salario> | <edad> |
    Then se debe crear un nuevo registro de empleado.

    Examples:
      | nombre | salario | edad |
      | Juan   | 50000   | 30   |

  @Put
  Scenario Outline: actualizar un registro de empleado
    Given que la API del empleado está disponible
    And   existe un empleado con id <id>
    When  actualizo el registro de empleado con id <id>, con los siguientes detalles:
        | nombre   | salario   | edad   |
        | <nombre> | <salario> | <edad> |
    Then  se debe actualizar el registro de empleado

    Examples:
      | id | nombre | salario | edad |
      | 1  | Juan   | 70000   | 35   |

    @Delete
  Scenario Outline: eliminar un registro de empleado
    Given que la API del empleado está disponible
    And existe un empleado con id <id>
    When elimino el registro de empleado con id <id>
    Then se debe eliminar el registro de empleado

    Examples:
      | id |
      | 2  |
      | 3  |
