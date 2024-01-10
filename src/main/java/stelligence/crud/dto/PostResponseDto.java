package stelligence.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stelligence.crud.entity.Comment;

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
}
