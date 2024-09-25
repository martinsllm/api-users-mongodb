package com.example.api_users_mongodb.infraestructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.api_users_mongodb.infraestructure.entities.EnderecoEntity;

public interface EnderecoRepository extends MongoRepository<EnderecoEntity, String> {
    
    EnderecoEntity findByUsuarioId(String usuarioId);

    @Transactional
    void deleteByUsuarioId(String usuarioId);
}
