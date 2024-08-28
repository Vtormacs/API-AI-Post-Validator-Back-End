package com.AI_Posts.Service;

import com.AI_Posts.Entity.UserEntity;
import com.AI_Posts.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity save(UserEntity user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Erro no service, n deu para salvar o usuario no repository" + e.getMessage());
            return new UserEntity();
        }
    }

    public UserEntity update(UserEntity user, UUID uuid) {
        try {
            userRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Usuario n exixte no banco"));
            user.setUuid(uuid);
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Erro no service, n deu para atualizar o usuario no repository" + e.getMessage());
            return new UserEntity();
        }
    }

    public String delete(UUID uuid) {
        try {
            userRepository.deleteById(uuid);
            return "usuario deletado";
        } catch (Exception e) {
            System.out.println("Erro no service, n deu para atualizar o usuario no repository" + e.getMessage());
            return "N deu para deletar o suario, erro no service";
        }
    }

    public UserEntity findById(UUID uuid) {
        try {
            return userRepository.findById(uuid).orElseThrow(() -> new RuntimeException("usuario n encontrado no banco"));
        } catch (Exception e) {
            System.out.println("Erro no service, n deu para encontrar o usuario no repository" + e.getMessage());
            return new UserEntity();
        }
    }

    public List<UserEntity> findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            System.out.println("Erro no service, n deu para listar os usuarios do banco" + e.getMessage());
            return List.of();
        }
    }
}
