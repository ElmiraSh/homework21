
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginRequestSpec.*;

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

    @Test
    public void unsuccesfulLoginTest() {
        LoginUnsuccefulBody request = new LoginUnsuccefulBody();
        request.setEmail("sydney@fife");

        LoginUnsuccesfullResponce response = step("Make login requests", () ->
                given(loginRequestSpec)
                        .body(request)
                        .contentType(JSON)
                        .when()
                        .post("https://reqres.in/api/register")
                        .then()
                        .spec(unsuccessfulLoginResponseSpec)
                        .extract().as(LoginUnsuccesfullResponce.class));

        step("Verify response", () ->
                assertEquals("Missing password", response.getError()));

    }

    @Test
    public void succesfulRegister() {
        RegisterSuccesfulBody request = new RegisterSuccesfulBody();
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("pistol");

        RegisterSuccesfulResponse response = step("Make login requests", () ->
                given(loginRequestSpec)
                        .body(request)
                        .contentType(JSON)
                        .when()
                        .post("https://reqres.in/api/register")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(RegisterSuccesfulResponse.class));

        step("Verify response", () ->
                assertAll(
                        () -> assertEquals("QpwL5tke4Pnpja7X4", response.getToken()),
                        () -> assertEquals("4", response.getId())));

    }

    @Test
    public void unsuccesfulRegister() {
        RegisterUnsuccesfulBody request = new RegisterUnsuccesfulBody();
        request.setEmail("sydney@fife");

        RegisterUnsuccefulResponse response = step("Make login requests", () ->
                given(loginRequestSpec)
                        .body(request)
                        .contentType(JSON)
                        .when()
                        .post("https://reqres.in/api/register")
                        .then()
                        .spec(unsuccessfulLoginResponseSpec)
                        .extract().as(RegisterUnsuccefulResponse.class));

        step("Verify response", () ->
                assertEquals("Missing password", response.getError()));

    }

    @Test
    public void succesfulCreateUsers() {
        CreateUserBody request = new CreateUserBody();
        request.setName("morpheus");
        request.setJob("leader");

        CreateUserResponse response = step("Make login requests", () ->
                given(loginRequestSpec)
                        .body(request)
                        .contentType(JSON)
                        .when()
                        .post("https://reqres.in/api/users")
                        .then()
                        .spec(succesfulRegistration)
                        .extract().as(CreateUserResponse.class));


        step("Verify response", () -> {

            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());

        });

    }
}




