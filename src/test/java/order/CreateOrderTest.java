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
public class CreateOrderTest extends OrderTestData {


    public CreateOrderTest(String[] color) {
        super(color);
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Заказ можно создать с указанием только одного цвета, обоих цветов, либо без их указания в принципе")
    public void createOrder() {
        given()
                .spec(BaseHttpClient.baseRequestSpec())
                .body(order)
                .when()
                .post(UrlAddresses.ORDER)
                .then().statusCode(201)
                .and()
                .assertThat().body("track", notNullValue());
    }
}
