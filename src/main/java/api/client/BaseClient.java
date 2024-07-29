package api.client;

import dto.UpdateUserRequest;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public abstract class BaseClient {

    protected static Response getRequest(String token, String url) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        if (Objects.nonNull(token)) {
            headers.put("Authorization", token);
        }
        return given()
                .headers(headers)
                .when()
                .get(url);
    }

    protected static <T> Response postRequest(T body, String token, String url) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        if (Objects.nonNull(token)) {
            headers.put("Authorization", token);
        }
        return given()
                .headers(headers)
                .and()
                .body(body)
                .when()
                .post(url);
    }

    protected static Response deleteRequest(String url, String token) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(url);
    }

    protected static Response patchRequest(UpdateUserRequest user, String token, String url) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        if (Objects.nonNull(token)) {
            headers.put("Authorization", token);
        }
        return given()
                .headers(headers)
                .and()
                .body(user)
                .when()
                .patch(url);
    }
}
