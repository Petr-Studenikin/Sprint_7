package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static constants.UrlAddresses.MAIN_URL;
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
    private final String color;
    String track;

    @Before
    public void setUp() {
        RestAssured.baseURI = MAIN_URL;
    }

    public CreateOrderTest(String color) {
        this.color = color;
    }

    @Parameterized.Parameters (name = "colour = ''{0}''")
    public static Object[] getColour() {
        return new Object[][]{
                {"BLACK"},
                {"GREY"},
                {"BLACK GREY"},
                {""}
        };
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Заказ можно создать с указанием только одного цвета, обоих цветов, либо без их указания в принципе")
    public void createOrder() {
        Order order = new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, new String[]{color});
        Response response = OrderMethods.createOrder(order);
        track = response.then().extract().path("track").toString();
        response.then().assertThat().statusCode(201)
                .and()
                .assertThat()
                .body("track", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без указания параметра цвета")
    @Description("Заказ можно создать, если не передать параметр color")
    public void createOrderWithoutColor() {
        Order order = new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT);
        Response response = OrderMethods.createOrder(order);
        track = response.then().extract().path("track").toString();
        response.then().assertThat().statusCode(201)
                .and()
                .assertThat()
                .body("track", notNullValue());
    }

    @After
    public void cancelOrder() {
        OrderMethods.cancelOrder(track);
    }
}
