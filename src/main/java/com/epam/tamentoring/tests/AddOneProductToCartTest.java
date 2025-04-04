package com.epam.tamentoring.tests;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AddOneProductToCartTest {

    static Stream<Arguments> addOneProductToCartTestDataStream() {
        return Stream.of(
                // 2 apples + 5 apples => 7 apples:
                Arguments.of(2.0, 5.0, 7.0),

                // 2 apples + 0.5 apples => 2.5 apples:
                Arguments.of(2.0, 0.5, 2.5),

                // 2 apples + 0 apples => 2 apples:
                Arguments.of(2.0, 0.0, 2),

                // 0 apples + 0 apples => 0 apples:
                Arguments.of(0.0, 0.0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("addOneProductToCartTestDataStream")
    public void addOneProductToCartTest(
            double initialQuantityInCart, double addedQuantityToCart, double expectedProductQuantityInCart) {

        Product initialProductInCart = new Product(1, "apple", 1.0, initialQuantityInCart);
        Product productToAddToCart = new Product(1, "apple", 1.0, addedQuantityToCart);

        List<Product> products = new ArrayList<>();

        products.add(initialProductInCart);

        ShoppingCart shoppingCart = new ShoppingCart(products);

        shoppingCart.addProductToCart(productToAddToCart);

        double totalQuantityAfterAdding = shoppingCart.getProducts().get(0).getQuantity();

        Assertions.assertEquals(expectedProductQuantityInCart, totalQuantityAfterAdding);

    }
}
