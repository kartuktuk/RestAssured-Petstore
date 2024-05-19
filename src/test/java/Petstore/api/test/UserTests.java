package Petstore.api.test;

import Petstore.api.enpoints.UserEndpoints;
import Petstore.api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User userPayload;

    public Logger logger;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("************ CREATING USER *******************");
        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("************ USER CREATED *******************");

    }

    @Test(priority = 2)
    public void testGetUserByName() {
        logger.info("************ GETTING USER BY NAME *******************");
        Response response = UserEndpoints.getUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {
        logger.info("************ UPDATING USER *******************");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response = UserEndpoints.updateUser(userPayload, this.userPayload.getUsername());
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("************  USER UPDATED *******************");

        Response responseAfterUpdate = UserEndpoints.getUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();

        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void testDeleteUser() {
        logger.info("************ DELETING USER *******************");
        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("************  USER DELETED *******************");

    }
}
