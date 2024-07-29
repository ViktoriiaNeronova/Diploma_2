import dto.CreateUserRequest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.Matchers.is;

public class CreateUserTest extends AbstractTest {
    private String token;

    @After
    public void deleteUser() {
        if (Objects.nonNull(token)) {
            deleteUser(token);
        }
    }

    @Test
    @DisplayName("Создание уникального пользователя")
    @Description("Создание уникального пользователя\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: true")
    public void createUserUniqueTest() {
        CreateUserRequest user = new CreateUserRequest()
                .setName("viktoriya")
                .setEmail(getRandomEmail())
                .setPassword("Qwe12!");
        Response response = createUser(user);

        token = response.then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .extract()
                .body()
                .path("accessToken");
    }

    @Test
    @DisplayName("Создание пользователя, который уже зарегистрирован")
    @Description("Создание пользователя, который уже зарегистрирован\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void CreateDuplicateUserTest() {
        CreateUserRequest user = new CreateUserRequest()
                .setName("viktoriya")
                .setEmail(getRandomEmail())
                .setPassword("Qwe12!");
        Response response = createUser(user);

        token = response.then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .extract()
                .body()
                .path("accessToken");

        response = createUser(user);
        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("success", is(false));
    }

    @Test
    @DisplayName("Создание пользователя без обязательного поля name")
    @Description("Попытка создания пользователя без обязатльного поля name\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void requiredFieldNameTest() {
        CreateUserRequest user = new CreateUserRequest()
                .setEmail(getRandomEmail())
                .setPassword("Qwe12!");
        Response response = createUser(user);

        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("success", is(false));
    }

    @Test
    @DisplayName("Создание пользователя без обязательного поля email")
    @Description("Попытка создания пользователя без обязательного поля email\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void requiredFieldEmailTest() {
        CreateUserRequest user = new CreateUserRequest()
                .setName("viktoriya")
                .setPassword("Qwe12!");
        Response response = createUser(user);

        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("success", is(false));
    }

    @Test
    @DisplayName("Создание пользователя без обязательного поля password")
    @Description("Попытка создания пользователя без обязательного поля password\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void requiredFieldPasswordTest() {
        CreateUserRequest user = new CreateUserRequest()
                .setName("viktoriya")
                .setEmail(getRandomEmail());
        Response response = createUser(user);

        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("success", is(false));
    }
}


