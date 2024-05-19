package linkedin;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Multivitamin {
    @Test
    public void getProduct() {

        String baseUri = "http://localhost:8888";
        String endpoint = "/api_testing/product/read_one.php";
        var response = given()
                .baseUri(baseUri)
                .queryParam("id",18)
                .when()
                .get(endpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"))
                .body("id", equalTo("18"))
                .body("name",equalTo("Multi-Vitamin (90 capsules)"))
                .body("description", equalTo("A daily dose of our Multi-Vitamins fulfills a day’s nutritional needs for over 12 vitamins and minerals."))
                .body("price", equalTo("10.00"))
                .body("category_id", equalTo(4))
                .body("category_name", equalTo("Supplements"))
                .log().all();
    }
}
