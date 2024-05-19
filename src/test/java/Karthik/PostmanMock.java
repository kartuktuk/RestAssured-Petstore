package Karthik;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostmanMock {

    @Test
    public void getUserSuccess() {

        given()
                .when()
                .header("x-mock-response-code","200")
                .get("https://81b1629d-77ed-4dd8-92b6-be335b754390.mock.pstmn.io")
                .then()
                .assertThat()
                .statusCode(200)
                .log()
                .all();
    }
    @Test
    public void getForbiddenUser() {
        given()
                .when()
                .header("x-mock-response-code","403")
                .get("https://81b1629d-77ed-4dd8-92b6-be335b754390.mock.pstmn.io")
                .then()
                .assertThat()
                .statusCode(403)
                .log()
                .all();
    }
    @Test
    public void getUnavailableUser() {
        given()
                .when()
                .header("x-mock-response-code","503")
                .get("https://81b1629d-77ed-4dd8-92b6-be335b754390.mock.pstmn.io")
                .then()
                .assertThat()
                .statusCode(503)
                .log()
                .all();
    }
    @Test
    public void validate_status_code() {
        given().
                baseUri("https://reqres.in").
        when()
                .get("/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void validate_response_body() {
        given().
                baseUri("https://reqres.in").
                when()
                .get("/api/users/2")
                .then()
                .assertThat()
                .statusCode(200)
                .body("data[0].first_name", equalTo("Janet"))
                .log().all();

    }

}

