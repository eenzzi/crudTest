package stelligence.crud.dto;

import jakarta.validation.constraints.NotNull;
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

    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(comment.getId(), comment.getContent());
    }
}
