package upc.edu.ecomovil.api.forum.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}