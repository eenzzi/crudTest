package stelligence.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private long id;
    private String title;
    private String content;
//    private LocalDateTime createdDate;

}
