package Karthik;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class HTTPRequests {

    int id;

    @Test (priority = 1)
    public void getUser() {

        given()
                .when()
                .get("https://reqres.in/api/users/?page=2")
                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log()
                .all();
    }

    @Test (priority = 2)
    public void createUser() {
        HashMap hashMap = new HashMap<>();
        hashMap.put("name","vidya");
        hashMap.put("job","queenie");

        id = given()
                .contentType("application/json")
                .body(hashMap)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
                //.then()
                //.statusCode(201)
                //.log().all();
    }

    @Test (priority = 3, dependsOnMethods = {"createUser"})
    public void updateUser() {
        HashMap hashMap = new HashMap<>();
        hashMap.put("name","vidya");
        hashMap.put("job","muddu");

        given()
                .contentType("application/json")
                .body(hashMap)
                .when()
                .put("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test (priority = 4)
    public void deleteUser() {

        given()
                .when()
                .delete("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(204)
                .log().all();
    }
}