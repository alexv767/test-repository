package ru.stqa.training.selenium.NewBasketTestStructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyLitecartBasketNewStructure {
    MyLitecartApplication app;

    @Before
    public void start() {
        MyLitecartApplication app = new MyLitecartApplication();
    }

    @Test
    public void myFirstTest() {
        int bp;

        //  Add 3 products to BASKET :
        app.addToBasket(3);

        bp = 1; // for breakpoint only

        //  DELETE ALL products from BASKET :
        app.clearBasket();

        bp = 1; // for breakpoint only
    }

    @After
    public void stop() {
        app.endApp();
    }

}
