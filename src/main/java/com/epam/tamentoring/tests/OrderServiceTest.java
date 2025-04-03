package com.epam.tamentoring.tests;

import com.epam.tamentoring.bo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

class OrderServiceTest {

    @Mock
    private DiscountUtility discountUtility;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDiscountForSpecificUser() {
        double expectedDiscount = 3.0;

        UserAccount user = new UserAccount();
        user.setName("John");
        user.setSurname("Smith");
        user.setDateOfBirth("1990/10/10");

        Product productInCart = new Product(1, "apple", 1.0, 100);
        List<Product> products = new ArrayList<>();

        products.add(productInCart);
        ShoppingCart shoppingCart = new ShoppingCart(products);
        user.setShoppingCart(shoppingCart);

        if (user.getName().equals("John") && user.getSurname().equals("Smith") && user.getDateOfBirth().equals("1990/10/10")) {
            Mockito.when(discountUtility.calculateDiscount(user)).thenReturn(3.0);
        }

        double actualPrice = orderService.getOrderPrice(user);
        Mockito.verify(discountUtility, Mockito.only()).calculateDiscount(user);
        Assertions.assertEquals(expectedDiscount, shoppingCart.getCartTotalPrice() - actualPrice);
    }

    @Test
    void getOrderPriceWithDiscount() {
        UserAccount user = new UserAccount();
        Product productInCart = new Product(1, "apple", 1.0, 100);
        List<Product> products = new ArrayList<>();

        products.add(productInCart);
        ShoppingCart shoppingCart = new ShoppingCart(products);
        user.setShoppingCart(shoppingCart);

        Mockito.when(discountUtility.calculateDiscount(user)).thenReturn(20.0);

        double actualPrice = orderService.getOrderPrice(user);

        Assertions.assertEquals(80.0, actualPrice);
    }

    @Test
    void getOrderPriceWithZeroDiscount() {
        UserAccount user = new UserAccount();
        Product productInCart = new Product(1, "apple", 1.0, 100);
        List<Product> products = new ArrayList<>();

        products.add(productInCart);
        ShoppingCart shoppingCart = new ShoppingCart(products);
        user.setShoppingCart(shoppingCart);

        Mockito.when(discountUtility.calculateDiscount(user)).thenReturn(0.0);

        double actualPrice = orderService.getOrderPrice(user);

        Assertions.assertEquals(100.0, actualPrice);
    }


}
