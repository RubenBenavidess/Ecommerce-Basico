package com.rejj.ecommerce.controller;

public record LoginRequest(
    String email,
    String password
) {

}
