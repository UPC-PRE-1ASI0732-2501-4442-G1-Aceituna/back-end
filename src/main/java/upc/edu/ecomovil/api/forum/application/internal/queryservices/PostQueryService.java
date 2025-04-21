package upc.edu.ecomovil.api.forum.application.internal.queryservices;

import upc.edu.ecomovil.api.forum.domain.model.entities.Post;
import upc.edu.ecomovil.api.forum.infrastructure.repositories.jpa.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostQueryService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
