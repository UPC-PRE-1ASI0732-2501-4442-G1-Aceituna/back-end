package upc.edu.ecomovil.api.forum.application.internal.commandservices;

import upc.edu.ecomovil.api.forum.domain.model.entities.Post;
import upc.edu.ecomovil.api.forum.infrastructure.repositories.jpa.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCommandService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }
}
