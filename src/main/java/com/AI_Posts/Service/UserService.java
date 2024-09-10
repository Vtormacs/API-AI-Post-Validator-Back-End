package com.AI_Posts.Service;

import com.AI_Posts.Entity.EmailEntity;
import com.AI_Posts.Entity.UserEntity;
import com.AI_Posts.Exception.User.UserNotFoundException;
import com.AI_Posts.Repository.UserRepository;
import com.AI_Posts.Service.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public UserEntity save(UserEntity user) {
        try {
            userRepository.save(user);
            if (!user.getEmail().isEmpty()) {
                EmailEntity email = emailService.criarEmail(user);
                emailService.enviaEmail(email);
            }
            return user;
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
        return userRepository.findById(uuid).orElseThrow(() -> new UserNotFoundException());
    }

    public List<UserEntity> findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            System.out.println("Erro no service, n deu para listar os usuarios do banco" + e.getMessage());
            return List.of();
        }
    }

    public boolean validarConta(UUID idUser, String hashRecebido) {
        UserEntity usuario = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException());

        String hashGerado = EmailService.generateHash(usuario.getNome(), usuario.getEmail());

        if (hashGerado.equals(hashRecebido)) {
            usuario.setAtivo(true);
            userRepository.save(usuario);
            return true;
        }
        return false;
    }
}
