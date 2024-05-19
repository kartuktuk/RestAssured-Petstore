package linkedin;

import models.Product;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MAMPHTTPReq {

    @Test
    public void getProducts() {

        String baseUri = "http://localhost:8888";
        String endpoint = "/api_testing/category/read.php";
        var response = given()
                .baseUri(baseUri)
                .when()
                .get(endpoint)
                .then().log().headers()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=UTF-8"))
                .body("records.size()", greaterThan(0))
                .body("records.id", everyItem(notNullValue()))
                .body("records.name", everyItem(notNullValue()))
                .body("records.description", everyItem(notNullValue()))
                .body("records.id[0]", equalTo(1))
                .log().all();
    }

    @Test
    public void getProduct() {

        String baseUri = "http://localhost:8888";
        String endpoint = "/api_testing/product/read_one.php";
        var response = given()
                .baseUri(baseUri)
                .queryParam("id",2)
                .when()
                .get(endpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo("2"))
                .body("name",equalTo("Cross-Back Training Tank"))
                .body("description", startsWith("The"))
                .body("price", equalTo("299.00"))
                .body("category_name", startsWith("Active"))
                .log().all();
    }


    @Test
    public void createProduct() {
        String baseUri = "http://localhost:8888";
        String endpoint = "/api_testing/product/create.php";
        String body ="\n" +
                "{\n" +
                "    \"name\": \"duh\",\n" +
                "        \"description\": \"du duh duh duh\",\n" +
                "        \"price\": 12,\n" +
                "        \"category_id\": 3\n" +
                "        }";
        var response = given()
                .baseUri(baseUri).body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct() {
        String baseUri = "http://localhost:8888";
        String endpoint = "/api_testing/product/update.php";
        String body ="\n" +
                "{\n" +
                "    \"name\": \"rugjh\",\n" +
                "        \"description\": \"budundiu\",\n" +
                "        \"price\": 100,\n" +
                "        \"category_id\": 3\n" +
                "        }";
        var response = given()
                .baseUri(baseUri).body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct() {
        String baseUri = "http://localhost:8888";
        String endpoint = "/api_testing/product/delete.php";
        Product product = new Product(23);
        var response = given()
                .baseUri(baseUri).body(product).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSerializedProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        Product product = new Product(
                "Water Bottle",
                "Blue water bottle. Holds 64 ounces",
                12,
                3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateSerializedProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        Product product = new Product(
                23,
                "ggkjgkjgkj mjkhkj",
                "lkhkjhls",
                17858567,
                786
        );
        var response = given().body(product).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void getDeserializedProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
        Product expectedProduct = new Product(
                2,
                "Cross-Back Training Tank",
                "The most awesome phone of 2013!",
                299.00,
                2,
                "Active wear - Women"
        );
        Product actualProduct = given()
                .queryParam("id", "2")
                .when().get(endpoint)
                .as(Product.class);

        assertThat(actualProduct, samePropertyValuesAs(expectedProduct));


    }
}
