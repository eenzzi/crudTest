package stelligence.crud.entity;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE comment SET is_deleted = true WHERE id = ?")
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	private boolean isDeleted;

	@JoinColumn(name = "post_id") //fk 매핑: post_id = db에서 외래 키의 이름 지정
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
