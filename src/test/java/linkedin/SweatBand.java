package linkedin;

import models.Product;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SweatBand {

    @Test
    public void createSerializedProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        Product product = new Product(
                "SweatBand",
                "hbahhdbdhdbdhd ",
                5,
                3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }
    @Test
    public void updateSerializedProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        Product product = new Product(
                24,
                "SweatBand",
                "duh duh duhhhhhhhh",
                6,
                3
        );
        var response = given().body(product).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void getProductByQueryParam() {

        String baseUri = "http://localhost:8888";
        String endpoint = "/api_testing/product/read_one.php";
        var response = given()
                .baseUri(baseUri)
                .queryParam("id",24)
                .when()
                .get(endpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void deleteProduct() {
        String baseUri = "http://localhost:8888";
        String endpoint = "/api_testing/product/delete.php";
        Product product = new Product(24);
        var response = given()
                .baseUri(baseUri).body(product).when().delete(endpoint).then();
        response.log().body();
    }
}
