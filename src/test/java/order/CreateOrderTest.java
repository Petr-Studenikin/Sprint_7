package order;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;



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
        Response response = OrderMethods.createOrder(order);
        track = response.then().extract().path("track").toString();
        response.then().assertThat().statusCode(201)
                .and()
                .assertThat()
                .body("track", notNullValue());
    }
}
