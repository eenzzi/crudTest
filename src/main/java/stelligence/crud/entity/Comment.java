package stelligence.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private boolean isDeleted;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post post;

    public Comment(String content, Post post) {
        this.content = content;
        this.isDeleted = false;
        this.post = post;
    }

    public Comment update(String content) {
        this.content = content;
        return this;
    }
}
