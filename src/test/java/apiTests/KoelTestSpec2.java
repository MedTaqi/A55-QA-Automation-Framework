package apiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.InnerRule;
import models.Playlist;
import models.Rule;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class KoelTestSpec2 {
    RequestSpecification requestSpec;
    @BeforeClass
    public void AuthSetup(){
        Response response = given()
                .params("email","taqimed99@gmail.com",
                        "password","Med-20115-010499@")
                .post("https://qa.koel.app/api/me").then().statusCode(200).extract().response();
        String accessToken = response.jsonPath().getString("token");
        String authorizationHeader = "Bearer " + accessToken;

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Authorization", authorizationHeader);
        requestSpec = builder.build();
    }

   // @Test
    public void getPlaylist(){
        Response response = given()
                .spec(requestSpec).log().headers().
                when()
                .get("https://qa.koel.app/api/playlist").
                then().statusCode(200).extract().response();
        String responseBody = response.getBody().asString();
        System.out.println("Response body"+ responseBody);
    }
    @Test
    public void verifyPlaylistName(){
        Response response = given()
                .spec(requestSpec).log().headers()
                .baseUri("https://qa.koel.app")
                .basePath("/api/playlist")
                .get()
                .then().statusCode(200).extract().response();
       JsonPath json = response.jsonPath();
        Playlist[]playlists = response.as(Playlist[].class);
       Rule[]rules = json.getObject("rules[0]", Rule[].class);
       InnerRule innerRule = rules[0].getRules()[0];
       System.out.println("model:" + innerRule.getModel());

        Assert.assertEquals(playlists[0].getName(),"momo");
    }

    @Test
    public void changePlaylistName(){
        Response response = given()
                .spec(requestSpec).log().headers()
                .baseUri("https://qa.koel.app")
                .basePath("/api/playlist")
                .get()
                .then().statusCode(200).extract().response();
        JsonPath json = response.jsonPath();
        Playlist[]playlists = response.as(Playlist[].class);
        Rule[]rules = json.getObject("rules[0]", Rule[].class);
        InnerRule innerRule = rules[0].getRules()[0];
        System.out.println("model:" + innerRule.getModel());

        Assert.assertEquals(playlists[0].getName(),"momo");
    }

}
