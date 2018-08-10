package richardTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class BaiduTest {
    @BeforeClass
    public void statt(){
        RestAssured.filters(
                (req,res,ctx)->{
                    req.cookie("testerhome","richard");
                    return ctx.next(req,res);

                }
        );
        RestAssured.proxy("127.0.0.1",8086);
    }
    @Test
    public void testGetHtml(){
        useRelaxedHTTPSValidation();
        given()
        .when()
                .log().all().get("https://testerhome.com")
                //.log().all().get("https://www.baidu.com")
        .then()
                .statusCode(200)
                //.log().all().statusCode(200);
                .body("html.head.title",equalTo("TesterHome"));


    }

    @Test
    public void topics(){
        useRelaxedHTTPSValidation();
        given().proxy("127.0.0.1",8086)
                .filter( (req,res,ctx)->{
                    req.cookie("testerhome","richard");
                    return ctx.next(req,res);

                })
        .when()
                .get("https://testerhome.com/api/v3/topics.json").prettyPeek()
        .then()
                .statusCode(200)
                //.body("topics.id[0]",equalTo(15323))
                //.body("topics.find{it.id == 15117}.title",equalTo("Charles 使用详解"))
                //.body("topics.find {topics ->topics.id ==15117}.title",equalTo("Charles 使用详解"))
                //.body("topics.find")
                //.body("topics[-1].user.id",equalTo(130426))
                .body("topics.id",hasItem(10254))
                .body("topics.id",hasItems(10254,15501))
                .body("topics.id.size()",equalTo(22))
                .time(lessThan(3000L))
        ;
    }
    @Test
    public void tesxml(){
         Response response = given().proxy("127.0.0.1",8484)
        .when().get("http://127.0.0.1:8888/xmltest.xml")
        .then()
                .body("shopping.category.size()",equalTo(3))
                .body("shopping.category.find{it.@type == 'groceries'}.item.name[0]", equalTo("Chocolate"))
                .extract().response()
                 ;
         String abc = response.path("shopping.category[0].item.name");
        //System.out.println(response.path("shopping.category[0][0]"));
        assertEquals("Chocolate10Coffee20",abc);
    }
    @Test
    public void testBaidu(){
        useRelaxedHTTPSValidation();
        HashMap<String,Object> hm = new HashMap<String, Object>();
        hm.put("name","ijiang");
        hm.put("name1","jiali");
        hm.put("name2","jiaxiao");
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(hm)
                .proxy("127.0.0.1",8585)
        .when()
                .post("https://www.baidu.com/s")
        .then()
                .statusCode(200);
        System.out.println("tset");
    }

    @Test
    public void testAuth(){
        given().auth().basic("hogwarts","123456")
                .get("http://shell.testing-studio.com:9002/")
                .then().statusCode(200);
    }
}
