package com.rejj.ecommerce.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(
    @JsonProperty("token_acceso")
    String accessToken,
    @JsonProperty("token_refresco")
    String refreshToken
) {
}
