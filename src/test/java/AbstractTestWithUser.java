import dto.CreateUserRequest;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractTestWithUser extends AbstractTest {
    protected String accessToken;

    @Before
    public void createUser() {
        CreateUserRequest user = new CreateUserRequest()
                .setName("viktoriya")
                .setEmail(getRandomEmail())
                .setPassword("Qwe12!");

        accessToken = createUserAndGetAccessToken(user);
    }

    @After
    public void deleteUser() {
        deleteUser(accessToken);
    }

}
