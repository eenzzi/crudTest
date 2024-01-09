package stelligence.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stelligence.crud.entity.Post;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostListResponseDto {


    private long id;
    private String title;

    public static PostListResponseDto from(Post post) {
        return new PostListResponseDto(post.getId(), post.getTitle());
    }
}
