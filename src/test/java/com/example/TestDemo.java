package com.example;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("REST API Regression Testing using TestNG")
@Feature("Verify that the Get and POST API returns correctly")
public class TestDemo {

    @Test(description = "To get the details of post with id 1", priority = 1)
    @Story("GET Request with valid post id")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify that the GET API returns correctly")
    public void verifyGetApi() {
        given()
                .filter(new AllureRestAssured())
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")

                .when()
                .get("/posts/1")

                .then()
                .statusCode(200)

                .body("userId", equalTo(1))
                .body("id", equalTo(1));
    }

    @Test(description = "To create a new post", priority = 2)
    @Story("POST Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify that the post API returns correctly")
    public void verifyPostApi() {
        given()
                .filter(new AllureRestAssured())
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")

                .when()
                .body("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1\n}")
                .post("/posts")

                .then()
                .statusCode(201)

                .body("userId", equalTo(1))
                .body("id", equalTo(101))
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"));
    }
}
