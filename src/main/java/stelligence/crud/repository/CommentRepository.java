package stelligence.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stelligence.crud.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
