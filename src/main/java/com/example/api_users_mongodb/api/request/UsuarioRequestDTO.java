package com.example.api_users_mongodb.api.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UsuarioRequestDTO {

    private String nome;

    private String email;

    private String documento;

    private EnderecoRequestDTO endereco;


}
