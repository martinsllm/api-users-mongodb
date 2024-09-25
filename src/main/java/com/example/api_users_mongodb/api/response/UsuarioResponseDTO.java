package com.example.api_users_mongodb.api.response;

public record UsuarioResponseDTO(
    String id,
    String nome,
    String email,
    String documento,
    EnderecoResponseDTO endereco) {}
