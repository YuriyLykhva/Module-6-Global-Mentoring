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

import static com.epam.tamentoring.tests.BaseTest.getQuantityOfProduct2;

public class AddTwoProductsToCartTest {

    //todo: test result - found a bug with zero product added -
    // required to implement zero quantity validation during adding product to shopping cart

    static Stream<Arguments> addSameProductToCartTestDataStream() {
        return Stream.of(
                // 2 apples + 5 apples => 7 apples:
                Arguments.of(1, "apple", 1, 2, 1, "apple", 1, 5, 7, 1),

                // 2 apples + 0.5 apples => 2.5 apples:
                Arguments.of(1, "apple", 1, 2, 1, "apple", 1, 0.5, 2.5, 1),

                // 2 apples + 0 apples => 2 apples:
                Arguments.of(1, "apple", 1, 2, 1, "apple", 1, 0, 2, 1),

                // 0 apples + 0 apples => 0 apples:
                Arguments.of(1, "apple", 1, 0, 1, "apple", 1, 0, 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("addSameProductToCartTestDataStream")
    public void addSameProductsToCartTest(
            int productId1, String productName1, double productPrice1, double initialQuantityInCart,
            int productId2, String productName2, double productPrice2, double addedQuantityToCart,
            double expectedProductQuantityInCart, int expectedProductNamesInCart) {

        Product initialProductInCart = new Product(productId1, productName1, productPrice1, initialQuantityInCart);
        Product productToAddToCart = new Product(productId2, productName2, productPrice2, addedQuantityToCart);

        List<Product> products = new ArrayList<>();

        products.add(initialProductInCart);

        ShoppingCart shoppingCart = new ShoppingCart(products);

        shoppingCart.addProductToCart(productToAddToCart);

        double totalQuantityAfterAdding = shoppingCart.getProducts().get(0).getQuantity() + getQuantityOfProduct2(shoppingCart);

        Assertions.assertEquals(expectedProductQuantityInCart, totalQuantityAfterAdding);
        Assertions.assertEquals(expectedProductNamesInCart, shoppingCart.getProducts().size());

    }

    static Stream<Arguments> addDifferentProductsToCartTestDataStream() {
        return Stream.of(
                // 2 apples + 5 bananas => 7 fruits:
                Arguments.of(1, "apple", 1, 2, 2, "banana", 3, 5, 7, 2),

                // 2 apples + 0.5 bananas => 2.5 fruits:
                Arguments.of(1, "apple", 1, 2, 2, "banana", 3, 0.5, 2.5, 2),

                // 2 apples + 0 bananas => 2 fruits:
                Arguments.of(1, "apple", 1, 2, 2, "banana", 3, 0, 2, 1),

                // 0 apples + 0 bananas => 0 fruits:
                Arguments.of(1, "apple", 1, 0, 2, "banana", 3, 0, 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("addDifferentProductsToCartTestDataStream")
    public void addDifferentProductsToCartTest(
            int productId1, String productName1, double productPrice1, double initialQuantityInCart,
            int productId2, String productName2, double productPrice2, double addedQuantityToCart,
            double expectedProductQuantityInCart, int expectedProductNamesInCart) {

        Product initialProductInCart = new Product(productId1, productName1, productPrice1, initialQuantityInCart);
        Product productToAddToCart = new Product(productId2, productName2, productPrice2, addedQuantityToCart);

        List<Product> products = new ArrayList<>();

        products.add(initialProductInCart);

        ShoppingCart shoppingCart = new ShoppingCart(products);

        shoppingCart.addProductToCart(productToAddToCart);

        double totalQuantityAfterAdding = shoppingCart.getProducts().get(0).getQuantity() + getQuantityOfProduct2(shoppingCart);

        Assertions.assertEquals(expectedProductQuantityInCart, totalQuantityAfterAdding);
        Assertions.assertEquals(expectedProductNamesInCart, shoppingCart.getProducts().size());

    }

}
