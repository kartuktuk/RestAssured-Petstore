package Petstore.api.enpoints;

/*
Swagger URI: https://petstore.swagger.io

Create user(POST): https://petstore.swagger.io/v2/user
Get user (GET): https://petstore.swagger.io/v2/user/{username}
Update user (PUT): https://petstore.swagger.io/v2/user/{username}
Delete user (DELETE): https://petstore.swagger.io/v2/user/{username}

 */
public class Routes {

    public static String base_url = "https://petstore.swagger.io/v2";

    //this is for user module

    public static String post_url = base_url+"/user";
    public static String get_url = base_url+"/user/{username}";
    public static String update_url = base_url+"/user/{username}";
    public static String delete_url = base_url+"/user/{username}";

    //Store Module URL







}
