package order;

import java.util.HashMap;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import io.restassured.response.ValidatableResponse;

import org.junit.Test;
import java.util.List;


import static org.junit.Assert.*;

public class GetOrderListTest {
    private final OrderMethods orderMethod = new OrderMethods();



    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверка, что в тело ответа возвращается непустой список заказов")
    public void getOrderList() {
        ValidatableResponse responseCreate = orderMethod.getOrderList();
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        List<HashMap> orderBody = responseCreate.extract().path("orders");
        assertEquals(200, actualStatusCodeCreate);
        assertNotNull(orderBody);
        assertTrue(orderBody.get(0).get("id").toString().matches("[\\d]+"));
    }
}
