package upc.edu.ecomovil.api.forum.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.ecomovil.api.forum.domain.model.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
