package Petstore.api.test;

import Petstore.api.enpoints.UserEndpoints;
import Petstore.api.payload.User;
import Petstore.api.utilities.DataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProvider.class)
    public void testPostUser(String userID, String userName, String firstName, String lastName, String email, String pwd, String phone) {
        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(pwd);
        userPayload.setPhone(phone);
        Response response = UserEndpoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProvider.class)
    public void testDeleteUserByName(String userName) {
        Response response = UserEndpoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
