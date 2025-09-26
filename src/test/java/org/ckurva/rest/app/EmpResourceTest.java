package org.ckurva.rest.app;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class EmpResourceTest {
    @Test
    void testGetAllEmployees() {
        given()
            .header("Accept", "application/json")
          .when().get("/ssr/emp")
          .then()
             .statusCode(200);
    }

    @Test
    void testGetEmployeeById() {
        given()
                .header("Accept", "application/json")
                .pathParam("id", 3)
                .when().get("/ssr/emp/{id}")
                .then()
                .statusCode(200)
                .body("empId", is(3))
                .body("empName", is("brodha"));
    }

    @Test
    void testCreateEmployee() {
        String newEmployee = """
                {
                    "empId": 10,
                    "empName": "Portronics",
                    "joinDate": "2024-06-20"
                }
                """;

        given()
            .header("Content-Type", "application/json")
            .body(newEmployee)
          .when().post("/ssr/emp")
          .then()
             .statusCode(200)
             .body("empId", is(10))
             .body("empName", is("Portronics"));
    }

    @Test
    void testUpdateEmployee() {
        String updatedEmployee = """
                {
                    "empId": 3,
                    "empName": "brodha",
                    "joinDate": "2024-06-20"
                }
                """;

        given()
            .header("Content-Type", "application/json")
            .body(updatedEmployee)
            .when().put("/ssr/emp")
            .then()
            .statusCode(200)
            .body("empId", is(3))
            .body("empName", is("brodha"));
    }

//    @Test
//    void testDeleteEmployee() {
//        given()
//            .header("Content-Type", "application/json")
//            .pathParam("id", 1)
//            .when().delete("/ssr/emp/{id}")
//            .then()
//            .statusCode(200)
//            .body(is("Delete Emp: 1 is completed"));
//    }
}