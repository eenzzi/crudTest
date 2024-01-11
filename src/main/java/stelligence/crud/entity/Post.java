package stelligence.crud.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE post SET is_deleted = true WHERE id = ?") //post의 삭제 요청 -> is_deleted값을 true로
public class Post extends BaseEntity {

	@Id //Post 엔티티의 pk임을 나타냄
	@GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키의 값을 auto increment 방식으로 생성
	private Long id;

	private String title;

	private String content;

	private boolean isDeleted;

	//일대다: 관계의 소유자 = post, 부모 엔티티(post)가 삭제 => 자식 엔티티(comment) 삭제
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<>();

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
		this.isDeleted = false;
	}

	public Post update(String content) {
		this.content = content;
		return this;
	}
}
