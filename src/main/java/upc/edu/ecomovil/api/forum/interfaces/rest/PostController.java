package upc.edu.ecomovil.api.forum.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.ecomovil.api.forum.application.internal.commandservices.PostService;
import upc.edu.ecomovil.api.forum.application.internal.outboundservices.acl.ExternalProfile2Service;
import upc.edu.ecomovil.api.forum.domain.model.entities.Post;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;


    @Autowired
    private ExternalProfile2Service externalProfileService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Optional<Profile> profileOpt = externalProfileService.fetchProfileById(post.getProfile().getId());

        if (profileOpt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // O lanzar una excepción personalizada
        }

        Profile profile = profileOpt.get();

        // Asignar el Profile al Post
        post.setProfile(profile);

        // Guardar el Post
        Post savedPost = postService.save(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
