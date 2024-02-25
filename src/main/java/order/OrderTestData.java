package order;

import org.junit.runners.Parameterized;

public class OrderTestData {
    private static final String  FIRST_NAME = "Петр";
    private static final String  LAST_NAME = "Петров";
    private static final String  ADDRESS = "Москва, Ленинский проспект, д. 99";
    private static final String  METRO_STATION = "Новаторская";
    private static final String  PHONE = "89880287766";
    private static final int  RENT_TIME = 1;
    private static final String  DELIVERY_DATE = "2024-02-18";
    private static final String  COMMENT = "Тестовый заказ";
    private String[] color;



    public OrderTestData(String[] color){
        this.color=color;
    }
    @Parameterized.Parameters
    public static Object[][]getColor(){
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK","GREY"}},
                {new String[]{""}}
        };
    }
    Order order = new Order(FIRST_NAME,
            LAST_NAME,
            ADDRESS,
            METRO_STATION,
            PHONE,
            RENT_TIME,
            DELIVERY_DATE,
            COMMENT,
            color);
}
