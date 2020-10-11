import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonPlaceHolderDELETETest {
    @Test
    public void jsonplaceholderDeleteUser(){

        Response response = given()
                .when()
                .delete("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

    }
}
