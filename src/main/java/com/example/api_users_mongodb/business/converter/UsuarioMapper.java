package com.example.api_users_mongodb.business.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.api_users_mongodb.api.response.UsuarioResponseDTO;
import com.example.api_users_mongodb.infraestructure.entities.EnderecoEntity;
import com.example.api_users_mongodb.infraestructure.entities.UsuarioEntity;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", source = "usuario.id")
    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "email", source = "usuario.email")
    @Mapping(target = "documento", source = "usuario.documento")
    @Mapping(target = "endereco", source = "enderecoEntity")
    UsuarioResponseDTO paraUsuarioResponseDTO(UsuarioEntity usuario, EnderecoEntity enderecoEntity);


}
