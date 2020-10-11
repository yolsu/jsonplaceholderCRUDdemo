import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonPlaceHolderGetTwoTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/users";
    private final String USERS = "users";
    @Test
    public void jsonplaceholderReadAllUsers(){


        Response response = given()
                .when()
                .get(BASE_URL + "/" +USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();


         JsonPath json = response.jsonPath();
      List<String> names = json.getList("name");
      assertEquals( 10, names.size());

    }

    @Test
    public void jesonplaceholderReadOneUser(){
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // .body("name", equalTo("Leanne Graham"))
              // .body("username", equalTo("Bret"))
              // .body("email", equalTo("Sincere@april.biz"))
             //  .body("address.street", equalTo("Kulas Light"));


       JsonPath json = response.jsonPath();

       assertEquals("Leanne Graham", json.get("name"));
       assertEquals("Bret", json.get("username"));
       assertEquals("Sincere@april.biz", json.get("email"));
       assertEquals("Kulas Light", json.get("address.street"));


     // System.out.println(response.asString());


    }
// PATH VARIABLE
    @Test
    public void jasonplaceholderReadOneUserWithPathVariables(){
        Response response = given()
                .pathParam("userId", 1)
                .when()
                .get("https://jsonplaceholder.typicode.com/users/{userId}")
                .then()
                .statusCode(200)
                .extract()
                .response();


        JsonPath json = response.jsonPath();
        assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Bret", json.get("username"));
        assertEquals("Sincere@april.biz", json.get("email"));
        assertEquals("Kulas Light", json.get("address.street"));
    }
//QUERY PARAMS (wysyłane po znaku zapytania, odpowiedź w tabeli)/users?username=Bret

    @Test
    public void jasonplaceholderReadOneUserWithQueryParam(){
        Response response = given()
                .queryParam("username", "Bret")
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(200)
                .extract()
                .response();



        JsonPath json = response.jsonPath();

        assertEquals("Leanne Graham", json.getList("name").get(0));
        assertEquals("Bret", json.getList("username").get(0));
        assertEquals("Sincere@april.biz", json.getList("email").get(0));
        assertEquals("Kulas Light",json.getList("address.street").get(0));

    }

}