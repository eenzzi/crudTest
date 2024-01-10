package stelligence.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stelligence.crud.entity.Comment;
import stelligence.crud.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private long id;
    private String title;
    private String content;

    private List<CommentResponseDto> comments = new ArrayList<>();
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public static PostResponseDto from(Post post) {
        List<Comment> comments = post.getComments();
        List<CommentResponseDto> list = new ArrayList<>();

        for (Comment comment1 : comments) {
            if (!comment1.isDeleted()) {
                CommentResponseDto commentResponseDto = CommentResponseDto.from(comment1);
                list.add(commentResponseDto);
            }
        }

        return new PostResponseDto(post.getId(),
                post.getTitle(),
                post.getContent(),
                list,
                post.getCreatedDate(),
                post.getLastModifiedDate());
    }
}
