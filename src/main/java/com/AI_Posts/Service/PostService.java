package com.AI_Posts.Service;

import com.AI_Posts.Entity.PostEntity;
import com.AI_Posts.Entity.TagEntity;
import com.AI_Posts.Entity.UserEntity;
import com.AI_Posts.Repository.PostRepository;
import com.AI_Posts.Repository.TagRepository;
import com.AI_Posts.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    public PostEntity save(PostEntity post) {
        try {
            UserEntity user = userRepository.findById(post.getUser().getUuid()).orElseThrow(() -> new RuntimeException("Erro ao achar usuario"));

            List<UUID> tagsId = post.getTags().stream().map(TagEntity :: getUuid).toList();

            List<TagEntity> tags = tagRepository.findAllById(tagsId);

            post.setUser(user);

            post.setTags(tags);

            post.setData(Instant.now());

            return postRepository.save(post);
        } catch (Exception e) {
            System.out.println("Erro no service, não deu para salvar o post no repository: " + e.getMessage());
            return new PostEntity();
        }
    }

    public PostEntity update(PostEntity post, UUID uuid) {
        try {
            postRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Post não existe no banco"));
            post.setUuid(uuid);
            return postRepository.save(post);
        } catch (Exception e) {
            System.out.println("Erro no service, não deu para atualizar o post no repository: " + e.getMessage());
            return new PostEntity();
        }
    }

    public String delete(UUID uuid) {
        try {
            postRepository.deleteById(uuid);
            return "Post deletado";
        } catch (Exception e) {
            System.out.println("Erro no service, não deu para deletar o post no repository: " + e.getMessage());
            return "Não deu para deletar o post, erro no service";
        }
    }

    public PostEntity findById(UUID uuid) {
        try {
            return postRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Post não encontrado no banco"));
        } catch (Exception e) {
            System.out.println("Erro no service, não deu para encontrar o post no repository: " + e.getMessage());
            return new PostEntity();
        }
    }

    public List<PostEntity> findAll() {
        try {
            return postRepository.findAll();
        } catch (Exception e) {
            System.out.println("Erro no service, não deu para listar os posts do banco: " + e.getMessage());
            return List.of();
        }
    }
}
