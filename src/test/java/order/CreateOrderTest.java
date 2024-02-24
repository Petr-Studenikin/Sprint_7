package order;

import base.BaseHttpClient;
import constants.UrlAddresses;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private static final String  FIRST_NAME = "Петр";
    private static final String  LAST_NAME = "Петров";
    private static final String  ADDRESS = "Москва, Ленинский проспект, д. 99";
    private static final String  METRO_STATION = "Новаторская";
    private static final String  PHONE = "89880287766";
    private static final int  RENT_TIME = 1;
    private static final String  DELIVERY_DATE = "2024-02-18";
    private static final String  COMMENT = "Тестовый заказ";
    private final String[] color;
    String track;


        public CreateOrderTest(String[] color){
            this.color=color;
        }
        @Parameterized.Parameters
        public static Object[][]authCombination(){
            return new Object[][]{
                    {new String[]{"BLACK"}},
                    {new String[]{"GREY"}},
                    {new String[]{"BLACK","GREY"}},
                    {new String[]{""}}
            };
        }

    @Test
    @DisplayName("Создание заказа")
    @Description("Заказ можно создать с указанием только одного цвета, обоих цветов, либо без их указания в принципе")
    public void createOrder() {
        Order order = new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, color);

        given()
                .spec(BaseHttpClient.baseRequestSpec())
                .body(order)
                .when()
                .post(UrlAddresses.ORDER)
                .then().statusCode(201)
                .and()
                .assertThat().body("track",notNullValue());
    }
}
