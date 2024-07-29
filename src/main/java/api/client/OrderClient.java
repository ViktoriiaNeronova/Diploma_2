package api.client;

import dto.CreateOrderRequest;
import io.restassured.response.Response;

public abstract class OrderClient extends BaseClient {

    public static Response createOrder(CreateOrderRequest order, String token){
        return postRequest(order, token, "/orders");
    }

    public static Response getOrder(String token){
        return getRequest(token,"/orders");
    }

}
