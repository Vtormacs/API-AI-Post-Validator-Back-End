package com.AI_Posts.Exception.Post;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException() {
        super("Post nao encontrado");
    }

    public PostNotFoundException(String mensagem) {
        super(mensagem);
    }

}
