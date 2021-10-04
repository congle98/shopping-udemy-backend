package com.example.shopbackend.service;

import com.example.shopbackend.dto.Purchase;
import com.example.shopbackend.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
