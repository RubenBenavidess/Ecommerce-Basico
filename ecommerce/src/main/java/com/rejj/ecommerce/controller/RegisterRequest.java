package com.rejj.ecommerce.controller;

public record RegisterRequest(
    String name,
    String email,
    String password,
    String address
) {

}
