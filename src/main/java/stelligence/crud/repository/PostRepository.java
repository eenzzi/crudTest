package stelligence.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stelligence.crud.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
