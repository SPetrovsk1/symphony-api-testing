package data;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;

public class LoginData {

    @DataProvider(name = "logInDataProvider")
    public static Object[][] logInObject() {
        final JSONObject postModel = new JSONObject();
        postModel.put("username", "Stefan");
        postModel.put("password", "Symphony.is1");
        final Object[][] returnObject = new Object[][]{{postModel}};
        return returnObject;
    }

    @DataProvider(name = "logInInvalidUserDataProvider")
    public static Object[][] invalidLogInObject() {
        final JSONObject postModel = new JSONObject();
        postModel.put("username", "StefanPetrovski");
        postModel.put("password", "Test");
        final Object[][] returnObject = new Object[][]{{postModel}};
        return returnObject;
    }
}
