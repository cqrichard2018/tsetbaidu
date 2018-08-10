package richardTest;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static org.hamcrest.Matchers.*;

public class TesterHome {
    @Test
    public void testHtml(){
        useRelaxedHTTPSValidation();
        given().queryParam("q","appium").
                get("https://testerhome.com/search").prettyPeek().
                then()
                .statusCode(200)
                .body("html.head.title",equalTo("appium · 搜索结果 · TesterHome"));

    }
    /*@BeforeClass
    public static void setup(){
        useRelaxedHTTPSValidation();
        RestAssured.baseURI = "https://testerhome.com";
                //System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\lijiang816\\Downloads\\cacert");
        RestAssured.proxy("127.0.0.1",8444);

    }*/
    @Test
    public void testerHomeJson(){
        given().get("https://testerhome.com/api/v3/topics.json")
                .then()
                .statusCode(200)
        //.body("topics.title",hasItems("优质招聘汇总","一步一步教你打造接口测试平台 (1)"))
        ;
    }

    @Test
    public void testerHomeJsonId(){
        given().get("https://testerhome.com/api/v3/topics/10254.json")
                .then()
                .statusCode(200)
                .body("topic.user.id",equalTo(10433));

    }
    @Test
    public void testHomeJson3(){
        given().when().get("https://testerhome.com/api/v3/topics.json").prettyPeek()
                .then()
        //.body("topics.title[3]",equalTo("一步一步教你打造接口测试平台 (1)"))
        ;
    }
    @Test
    public void testHomerSearch(){
        given().queryParam("q","霍格沃兹学院")
                .when().get("https://testerhome.com/api/v3/topics.json").prettyPeek()
                .then().statusCode(200)
                .body("topics.title[0]",equalTo("优质招聘汇总"))
                .body("topics.findAll {topics -> topics.id==10254}.title[0]",equalTo("优质招聘汇总"))
                .body("topics.findAll {topics -> topics.id==10254}.title",hasItem("优质招聘汇总"))
                //.body("topics.findAll {topics -> topics.title=='优质招聘汇总'}.id",hasItems(10254))
                .body("topics.find {topics -> topics.id == 10254}.title",equalTo("优质招聘汇总"))
        ;
    }
    //@Test
    public void xmlTest(){
        Response se = given().when().get("http://localhost:8888/xmltest.xml").prettyPeek()
                .then()
                .statusCode(200)
                .body("shopping.category.item.name[3]",equalTo("Pens"))
                .body("shopping.category[1].item.name[0]",equalTo("Paper"))
                .body("shopping.category.item.name.size()",equalTo(5))
                .body("shopping.category.findAll { it.@type=='present' }.item.name",equalTo("Kathryn's Birthday\""))
                .body("**.find {it.price ==200}.name",equalTo("Kathryn's Birthday\""))
                .extract().response();
        System.out.println("=====================>>>>>>>");
        System.out.println(((Response) se).statusLine());
        System.out.println("=====================<<<<<<<");
    }

    @Test
    public void proxyTest(){
        //useRelaxedHTTPSValidation();

        given().when().get("/api/v3/topics/10254.json").prettyPeek()
                //given(burp).proxy("127.0.0.1",8444).when().get("/api/v3/topics/10254.json").prettyPeek()
                .then()
                .statusCode(200)
                //.body("topic.title",equalTo("优质招聘汇总"))
                .time(lessThan(1000L));

    }

    @Test
    public void testJsonPost(){
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("id",6040);
        data.put("title","通过代理安装 appium");
        data.put("name","思寒");

        HashMap<String, Object> root = new HashMap<String, Object>();
        root.put("title",data);
        given().proxy("127.0.0.1",8444).contentType(ContentType.JSON)
                //.body(data)
                .body(root)
                .when().post("https://www.baidu.com")
                .then().statusCode(200)
                .time(lessThan(2000L));

    }

    @Test
    public void multiApi(){
        String name = given().get("https://testerhome.com/api/v3/topics/6050.json").prettyPeek()
                .then().statusCode(200)
                .body("topic.user.name",equalTo("心向东"))
                .extract().path("topic.user.name");

        System.out.println(name);
        given().queryParam("q",name)
                .cookie("uid",name)
                .when().get("https://testerhome.com/search")
                .then().statusCode(200).body(containsString(name));
    }


    @Test
    public void multiApiData(){
        Response response = given().get("https://testerhome.com/api/v3/topics/6050.json").prettyPeek()
                .then().statusCode(200)
                .body("topic.user.name",equalTo("心向东"))
                .extract().response();
        String name = response.path("topic.user.name");
        Integer uid = response.path("topic.user.id");
        System.out.println(name+"love");
        System.out.println("===========>>>>");
        System.out.println(uid);
        System.out.println("===========>>>>");
        given().queryParam("q",name).proxy("127.0.0.1",8444)
                .cookie("name",name)
                .cookie("uid",uid)
                .when().get("https://testerhome.com/search")
                .then().statusCode(200).body(containsString(name));
    }

    @Test
    public void testSpec(){
        ResponseSpecification rs = new ResponseSpecBuilder().build();
        rs.statusCode(200);
        rs.time(lessThan(3500L));
        rs.body(not(containsString("error")));
        given().get("/api/v3/topics/6050.json")
                .then().spec(rs);

    }

    @Test
    public void testFilter(){
        RestAssured.filters();
    }


}
