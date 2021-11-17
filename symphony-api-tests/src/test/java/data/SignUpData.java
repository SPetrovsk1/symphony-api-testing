package data;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;

public class SignUpData {

    @DataProvider(name = "signUpDataProvider")
    public static Object[][] signUpObject() {
        final JSONObject postModel = new JSONObject();
        postModel.put("email", "user1@test1.com"); //change for new user
        postModel.put("password", "String12!@");
        postModel.put("firstName", "name1");
        postModel.put("lastName", "lastName1");
        postModel.put("username", "Stefan"); //change for new user
        postModel.put("dateOfBirth", "01/01/2001");
        final Object[][] returnObject = new Object[][]{{postModel}};
        return returnObject;
    }

    @DataProvider(name = "signUpRandomDataProvider")
    public static Object[][] signUpRandomObject() {
        final JSONObject postModel = new JSONObject();
        postModel.put("email", RandomStringUtils.randomAlphanumeric(12) + "@test1.com"); //add random string for the email
        postModel.put("password", "String12!@");
        postModel.put("firstName", "name1");
        postModel.put("lastName", "lastName1");
        postModel.put("username", RandomStringUtils.randomAlphanumeric(12)); //add random string for the username
        postModel.put("dateOfBirth", "01/01/2001");
        final Object[][] returnObject = new Object[][]{{postModel}};
        return returnObject;
    }

    @DataProvider(name = "signUpInvalidPasswordDataProvider")
    public static Object[][] signUpInvalidPasswordObject() {
        final JSONObject postModel = new JSONObject();
        postModel.put("email", RandomStringUtils.randomAlphanumeric(12) + "@test1.com"); //add random string for the email
        postModel.put("password", "test");
        postModel.put("firstName", "name1");
        postModel.put("lastName", "lastName1");
        postModel.put("username", RandomStringUtils.randomAlphanumeric(12)); //add random string for the username
        postModel.put("dateOfBirth", "01/01/2001");
        final Object[][] returnObject = new Object[][]{{postModel}};
        return returnObject;
    }

}
