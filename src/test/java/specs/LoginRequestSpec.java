package specs;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.*;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class LoginRequestSpec {
    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().method()
            .log().body()
            .contentType(JSON);
//            .baseUri("https://reqres.in")
//             .basePath("/api");;

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
       .log(LogDetail.STATUS)
        .log(LogDetail.BODY)
        .expectStatusCode(200)
        .build();

}
