package com.example.api_users_mongodb.business.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.example.api_users_mongodb.api.request.EnderecoRequestDTO;
import com.example.api_users_mongodb.api.request.UsuarioRequestDTO;
import com.example.api_users_mongodb.infraestructure.entities.EnderecoEntity;
import com.example.api_users_mongodb.infraestructure.entities.UsuarioEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioConverter {

    public UsuarioEntity paraUsuarioEntity(UsuarioRequestDTO usuarioDTO) {
        return UsuarioEntity.builder()
                .id(UUID.randomUUID().toString())
                .nome(usuarioDTO.getNome())
                .documento(usuarioDTO.getDocumento())
                .email(usuarioDTO.getEmail())
                .dataCadastro(LocalDateTime.now())
                .build();

    }

    public EnderecoEntity paraEnderecoEntity(EnderecoRequestDTO enderecoDTO, String usuarioId) {
        return EnderecoEntity.builder()
                .rua(enderecoDTO.getRua())
                .usuarioId(usuarioId)
                .bairro(enderecoDTO.getBairro())
                .cep(enderecoDTO.getCep())
                .cidade(enderecoDTO.getCidade())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .build();
    }

}
