import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonPlaceHolderGetTest{
    @Test
    public void jsonplaceholderReadAllUsers(){


        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users");
        System.out.println(response.asString());

        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("name");
        Assertions.assertEquals( 10, names.size());

        names.stream()
                .forEach(System.out::println);

    }

    @Test
    public void jesonplaceholderReadOneUser(){
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1");
        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();

        Assertions.assertEquals("Leanne Graham", json.get("name"));
        Assertions.assertEquals("Bret", json.get("username"));
        Assertions.assertEquals("Sincere@april.biz", json.get("email"));
        Assertions.assertEquals("Kulas Light", json.get("address.street"));


     // System.out.println(response.asString());


    }
// PATH VARIABLE
    @Test
    public void jasonplaceholderReadOneUserWithPathVariables(){
        Response response = given()
                .pathParam("userId", 1)
                .when()
                .get("https://jsonplaceholder.typicode.com/users/{userId}");

        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals("Leanne Graham", json.get("name"));
        Assertions.assertEquals("Bret", json.get("username"));
        Assertions.assertEquals("Sincere@april.biz", json.get("email"));
        Assertions.assertEquals("Kulas Light", json.get("address.street"));
    }
//QUERY PARAMS (wysyłane po znaku zapytania, odpowiedź w tabeli)/users?username=Bret

    @Test
    public void jasonplaceholderReadOneUserWithQueryParam(){
        Response response = given()
                .queryParam("username", "Bret")
                .when()
                .get("https://jsonplaceholder.typicode.com/users");

        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();

        Assertions.assertEquals("Leanne Graham", json.getList("name").get(0));
        Assertions.assertEquals("Bret", json.getList("username").get(0));
        Assertions.assertEquals("Sincere@april.biz", json.getList("email").get(0));
        Assertions.assertEquals("Kulas Light",json.getList("address.street").get(0));

    }

}