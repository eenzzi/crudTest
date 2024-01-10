package stelligence.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE comment SET is_deleted = true WHERE id = ?")
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

    public Comment delete() {
        this.isDeleted = true;
        return this;
    }
}
