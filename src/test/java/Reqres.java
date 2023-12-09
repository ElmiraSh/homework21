
import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginRequestSpec.loginRequestSpec;
import static specs.LoginRequestSpec.loginResponseSpec;

public class Reqres {
    @Test
    void succesfulLoginTest() {
        LoginBodyModel authBody = new LoginBodyModel();
        authBody.setEmail("eve.holt@reqres.in");
        authBody.setPassword("ityslicka");

        LoginResponseModel response = step("Make login requests", () ->
                given(loginRequestSpec)
                        .body(authBody)
                        .contentType(JSON)
                        .when()
                        .post("https://reqres.in/api/login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Verify response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));

    }


    ////////////////////////////////
//    @Test
//    void unsuccesfulLoginTest() {
//        String authBody = "{\n" +
//                "    \"email\": \"eve.holt@reqres.in\"}";
//        given()
//                .log().uri()
//                .log().method()
//                .log().body()
//                .body(authBody)
//                .contentType(JSON)
//                .when()
//                .post("https://reqres.in/api/login")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(400)
//                .body("error", is("Missing password"));
//
//    }

//    @Test
//    void succesfulRegisterlLoginTest() {
//        String authBody = "{\n" +
//                "    \"email\": \"eve.holt@reqres.in\",\n" +
//                "    \"password\": \"pistol\"\n" +
//                "}";
//        given()
//                .log().uri()
//                .log().method()
//                .log().body()
//                .body(authBody)
//                .contentType(JSON)
//                .when()
//                .post("https://reqres.in/api/register")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(200)
//                .body("id", is(4))
//                .body("token", is("QpwL5tke4Pnpja7X4"));
//
//    }
//
//    @Test
//    void unsuccesfulRegisterlLoginTest() {
//        String authBody = "{\n" +
//                "    \"email\": \"sydney@fife\"\n" +
//                "}";
//        given()
//                .log().uri()
//                .log().method()
//                .log().body()
//                .body(authBody)
//                .contentType(JSON)
//                .when()
//                .post("https://reqres.in/api/register")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(400)
//                .body("error", is("Missing password"));
//
//
//    }
//
//    @Test
//    void createTest() {
//        String authBody = "{\n" +
//                "    \"name\": \"morpheus\",\n" +
//                "    \"job\": \"leader\"\n" +
//                "}";
//        given()
//                .log().uri()
//                .log().method()
//                .log().body()
//                .body(authBody)
//                .contentType(JSON)
//                .when()
//                .post("https://reqres.in/api/users")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(201)
//                .body("name", is("morpheus"))
//                .body("job", is("leader"));
//
//
//    }

}
