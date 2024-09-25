package com.example.api_users_mongodb.business.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.api_users_mongodb.api.request.UsuarioRequestDTO;
import com.example.api_users_mongodb.api.response.UsuarioResponseDTO;
import com.example.api_users_mongodb.business.converter.UsuarioConverter;
import com.example.api_users_mongodb.business.converter.UsuarioMapper;
import com.example.api_users_mongodb.infraestructure.entities.*;
import com.example.api_users_mongodb.infraestructure.exceptions.BusinessException;
import com.example.api_users_mongodb.infraestructure.exceptions.ConflictException;
import com.example.api_users_mongodb.infraestructure.exceptions.UnprocessableEntityException;
import com.example.api_users_mongodb.infraestructure.repositories.UsuarioRepository;
import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final UsuarioMapper usuarioMapper;
    private final EnderecoService enderecoService;

    public UsuarioEntity salvaUsuario(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    public UsuarioResponseDTO gravarUsuarios(UsuarioRequestDTO usuarioRequestDTO) {
        try {
            Boolean existeUsuario = usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail());
            if(existeUsuario.equals(true)) {
                throw new ConflictException("Já existe usuário cadastrado com esse e-mail");
            }
            
            UsuarioEntity usuarioEntity = salvaUsuario(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO));
            EnderecoEntity enderecoEntity = enderecoService.salvaEndereco(
                usuarioConverter.paraEnderecoEntity(usuarioRequestDTO.getEndereco(), usuarioEntity.getId()));
            
            return usuarioMapper.paraUsuarioResponseDTO(usuarioEntity, enderecoEntity);
        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("Erro ao gravar dados de usuário", e);
        }
    }

    public UsuarioResponseDTO buscaDadosUsuario(String email) {
        try {
            UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(email);
            notNull(usuarioEntity, "Usuário não encontrado!");
            EnderecoEntity enderecoEntity = enderecoService.findByUsuarioId(usuarioEntity.getId());

            return usuarioMapper.paraUsuarioResponseDTO(usuarioEntity, enderecoEntity);
        } catch (IllegalArgumentException e) {
            throw new UnprocessableEntityException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar dados de usuário", e);
        }
    }

    @Transactional
    public void deletaDadosUsuario(String email) {
        try {
            UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(email);
            notNull(usuarioEntity, "Usuário não encontrado!");

            usuarioRepository.deleteByEmail(email);
            enderecoService.deleteByUsuarioId(usuarioEntity.getId());
        } catch (IllegalArgumentException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }catch (Exception e) {
            throw new BusinessException("Erro ao excluir usuário", e);
        }
    }   

}
