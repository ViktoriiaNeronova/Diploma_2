package api.client;

import dto.CreateUserRequest;
import dto.LoginRequest;
import dto.LogoutRequest;
import dto.UpdateUserRequest;
import io.restassured.response.Response;

public class UserClient extends BaseClient {

    public static Response createUser(CreateUserRequest user){
        return postRequest(user, null,  "/auth/register");
    }

    public static Response loginUser(LoginRequest user){
        return postRequest(user, null, "/auth/login");
    }

    public static Response deleteUser(String token) {
        return deleteRequest( "/auth/user", token);
    }

    public static Response logoutUser(String refreshToken){
        return postRequest(new LogoutRequest(refreshToken), null, "/auth/logout");
    }

    public static Response updateUser(UpdateUserRequest user, String token){
        return patchRequest(user, token, "/auth/user");
    }
}
