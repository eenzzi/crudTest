package stelligence.crud.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import stelligence.crud.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Query(value = "select * from post where is_deleted = false", nativeQuery = true)
	List<Post> findAllWithoutDelete(Pageable pageable);
}
