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

import static com.epam.tamentoring.tests.BaseTest.getQuantityOfProduct;

public class RemoveProductFromCartTest {
    //todo: test result - found a bug with remove(product) method
    // required to check this method correct behavior

    static Stream<Arguments> removeProductFromCartTestDataStream() {
        return Stream.of(
                // 10 apples - 10 apples => 0 apples:
                Arguments.of(1, "apple", 1, 10, 1, "apple", 1, 10, 0),

                // 10 apples - 7 apples => 3 apples:
                Arguments.of(1, "apple", 1, 10, 1, "apple", 1, 7, 3)

        );
    }

    @ParameterizedTest
    @MethodSource("removeProductFromCartTestDataStream")
    public void removeProductFromCartTest(
            int productId1, String productName1, double productPrice1, double initialQuantityInCart,
            int productId2, String productName2, double productPrice2, double productQuantityToRemove,
            double expectedProductQuantityInCart) {

        Product initialProductInCart = new Product(productId1, productName1, productPrice1, initialQuantityInCart);
        Product productToRemoveFromCart = new Product(productId2, productName2, productPrice2, productQuantityToRemove);

        List<Product> products = new ArrayList<>();
        products.add(initialProductInCart);

        ShoppingCart shoppingCart = new ShoppingCart(products);

        shoppingCart.removeProductFromCart(productToRemoveFromCart);

        Assertions.assertEquals(expectedProductQuantityInCart, getQuantityOfProduct(shoppingCart));
    }

}
