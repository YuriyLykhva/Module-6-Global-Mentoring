package com.epam.tamentoring.tests;

import com.epam.tamentoring.bo.ShoppingCart;

public class BaseTest {

    protected static double getQuantityOfProduct(ShoppingCart shoppingCart) {
        if (shoppingCart.getProducts().isEmpty()) {
            return 0.0;
        }
        return shoppingCart.getProducts().get(0).getQuantity();
    }

    protected static double getQuantityOfProduct2(ShoppingCart shoppingCart) {
        if (shoppingCart.getProducts().size() == 1) {
            return 0.0;
        }
        return shoppingCart.getProducts().get(1).getQuantity();
    }
}
