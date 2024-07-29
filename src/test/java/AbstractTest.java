import api.client.OrderClient;
import api.client.UserClient;
import dto.*;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;

import java.util.UUID;

import static org.hamcrest.Matchers.is;

public abstract class AbstractTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api";
    }

    @Step("Create user")
    public static Response createUser(CreateUserRequest user){
        return UserClient.createUser(user);
    }

    @Step("Login user")
    public static Response loginUser(LoginRequest user){
        return UserClient.loginUser(user);
    }

    @Step("Delete user")
    public static Response deleteUser(String token) {
        return UserClient.deleteUser( token);
    }

    @Step("Logout user")
    public static Response logoutUser(String refreshToken){
        return UserClient.logoutUser(refreshToken);
    }

    @Step("Update user")
    public static Response updateUser(UpdateUserRequest user, String token){
        return UserClient.updateUser(user, token);
    }

    @Step("Get access token from created user")
    public static String createUserAndGetAccessToken(CreateUserRequest user) {
        Response response = createUser(user);

        String accessToken = response.then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .extract()
                .body()
                .path("accessToken");
        return accessToken;
    }

    @Step("Create order")
    protected Response createOrder(CreateOrderRequest order, String token){
        return OrderClient.createOrder(order, token);
    }

    @Step("Get orders")
    protected Response getOrder(String token){
        return OrderClient.getOrder(token);
    }

    public static String getRandomEmail() {
        return UUID.randomUUID() + "@yandex.ru";
    }

}
