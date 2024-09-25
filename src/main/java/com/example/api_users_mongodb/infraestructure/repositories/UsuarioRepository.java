package com.example.api_users_mongodb.infraestructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.api_users_mongodb.infraestructure.entities.UsuarioEntity;

public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {
    
    UsuarioEntity findByEmail(String email);

    Boolean existsByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
