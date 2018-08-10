package richardTest;

import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class RestAssured1 {

    public void getother(){
        System.out.println("test");
    }
    @Test
    public void gettest(){
        //Map map = new HashMap<String.Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("limit",2);
        map.put("offset",0);
        map.put("type","last_actived");

        //limit=2&offset=0&type=last_actived"
        given().params(map).get("https://testerhome.com/api/v3/topics.json").prettyPeek();
        //get("https://testerhome.com/api/v3/topics.json).prettyPeek();
        //get("https://testerhome.com/api/v3/topics.json?limit=2&offset=0&type=last_activedhttps://testerhome.com/api/v3/topics.json?limit=2&offset=0&type=last_actived").prettyPeek();

    }
    @Test
    public void singin(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("user[login]","sgjweig");
        map.put("user[password]","wegeg");
        given().params(map)
                .post("https://testerhome.com/account/sign_in").prettyPeek()
                .prettyPeek();

        //put("https://testerhome.com/account/sign_in/")
    }
    @Test
    public void response(){
        Response se = get("https://testerhome.com/api/v3/topics.json?limit=2&offset=0&type=last_actived");
        System.out.println(se.jsonPath().getString("topics[0]['title']"));
        System.out.println(se.jsonPath().getString("topics[0].title"));
        System.out.println(se.statusCode());


    }
}
