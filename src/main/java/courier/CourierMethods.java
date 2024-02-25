package courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static constants.UrlAddresses.*;
import static io.restassured.RestAssured.given;

public class CourierMethods {
    @Step("Создание курьера")
    public static Response createCourier(Courier courier) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .baseUri(MAIN_URL)
                .body(courier)
                .when()
                .post(COURIER);
    }

    @Step("Авторизация курьера")
    public static Response loginCourier(Courier courier) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .baseUri(MAIN_URL)
                .body(courier)
                .when()
                .post(LOGIN);
    }

    @Step("Удаление курьера")
    public static void deleteCourier(String courierId) {
        given()
                .baseUri(MAIN_URL)
                .delete(COURIER + "{courierId}", courierId);
    }
}