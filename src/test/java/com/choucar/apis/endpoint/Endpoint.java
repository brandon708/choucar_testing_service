package com.choucar.apis.endpoint;

/**
 * This class provides utility methods for common operations in Java.
 * It contains methods for loading properties, retrieving templates, queries, and variables,
 * generating random numbers and codes, converting lists to strings, and parsing data tables.
 * The methods in this class are static and can be accessed without creating an instance of the class.
 *
 * @author Joham Romucho
 */
public enum Endpoint {

    API_EMPLOYEE("/employees"),
    API_EMPLOYEE_ID("/employee"),
    API_CREATE("/create"),
    API_UPDATE("/update"),
    API_DELETE("/delete");


    private final String endpoint;

    Endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
