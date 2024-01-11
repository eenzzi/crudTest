package stelligence.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stelligence.crud.entity.Comment;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

	private Long id;
	private String content;

	public static CommentResponseDto from(Comment comment) { //Comment를 CommentResponseDto로 변환하는 메서드
		return new CommentResponseDto(comment.getId(), comment.getContent());
	}
}
