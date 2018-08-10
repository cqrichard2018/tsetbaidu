package richardTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class DataTest {
    @Parameterized.Parameters(name ="my parameter")
    public static Integer[][] data(){
        return new  Integer[][]{
                {0,0},{1,1},{2,3}
        };
    }
    @Parameterized.Parameter
    public int first;
    @Parameterized.Parameter(1)
    public int second;

    @Test
    public void testDemo(){
                assertThat(first,equalTo(second));
    }

    @Test
    public void testBaidu(){
        given().get("https://www.baidu.com/s?wd=201"+first)
                .then()
                //.log().all()
                .statusCode(200)
                .body("html.head.title",equalTo("201"+second+"_百度搜索"))
                ;
    }
}
