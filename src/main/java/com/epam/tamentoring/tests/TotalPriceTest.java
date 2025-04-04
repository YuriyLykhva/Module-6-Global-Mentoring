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

public class TotalPriceTest {

    //todo: test result - found a bug with remove(product) method
    // required to check this method correct behavior

    static Stream<Arguments> addingProductTotalPriceTestDataStream() {
        return Stream.of(
                // 10 apple + 5 apple => 15 apple x 2$ = 30$:
                Arguments.of(1, "apple", 2, 10, 1, "apple", 2, 5, 30),

                // 0 apple + 5 apples => 5 apple x 2$ = 10$:
                Arguments.of(1, "apple", 2, 0, 1, "apple", 2, 5, 10),

                // 10 apple + 5 banana => 10 apple x 2$ + 5 banana x 3$ = 35$:
                Arguments.of(1, "apple", 2, 10, 2, "banana", 3, 5, 35)
        );
    }

    @ParameterizedTest
    @MethodSource("addingProductTotalPriceTestDataStream")
    public void getCartTotalPriceAfterAddingTest(
            int productId1, String productName1, double productPrice1, double initialQuantityInCart,
            int productId2, String productName2, double productPrice2, double addedQuantityToCart,
            double expectedShoppingCartPrice) {

        Product initialProductInCart = new Product(productId1, productName1, productPrice1, initialQuantityInCart);
        Product productToAddToCart = new Product(productId2, productName2, productPrice2, addedQuantityToCart);

        List<Product> products = new ArrayList<>();

        products.add(initialProductInCart);

        ShoppingCart shoppingCart = new ShoppingCart(products);

        shoppingCart.addProductToCart(productToAddToCart);

        Assertions.assertEquals(expectedShoppingCartPrice, shoppingCart.getCartTotalPrice());
    }

    static Stream<Arguments> removingProductTotalPriceTestDataStream() {
        return Stream.of(
                // 10 apple - 7 apple => 3 apple x 2$ = 6$:
                Arguments.of(1, "apple", 2, 10, 1, "apple", 2, 7, 6)
        );
    }

    @ParameterizedTest
    @MethodSource("removingProductTotalPriceTestDataStream")
    public void getCartTotalPriceAfterRemovingTest(
            int productId1, String productName1, double productPrice1, double initialQuantityInCart,
            int productId2, String productName2, double productPrice2, double productQuantityToRemove,
            double expectedShoppingCartPrice) {

        Product initialProductInCart = new Product(productId1, productName1, productPrice1, initialQuantityInCart);
        Product productToRemoveFromCart = new Product(productId2, productName2, productPrice2, productQuantityToRemove);

        List<Product> products = new ArrayList<>();
        products.add(initialProductInCart);

        ShoppingCart shoppingCart = new ShoppingCart(products);

        shoppingCart.removeProductFromCart(productToRemoveFromCart);

        Assertions.assertEquals(expectedShoppingCartPrice, shoppingCart.getCartTotalPrice());
    }
}
