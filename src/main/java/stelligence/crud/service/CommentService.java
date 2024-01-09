package stelligence.crud.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stelligence.crud.dto.CommentRequestDto;
import stelligence.crud.dto.PostResponseDto;
import stelligence.crud.entity.Comment;
import stelligence.crud.entity.Post;
import stelligence.crud.repository.CommentRepository;
import stelligence.crud.repository.PostRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    //등록
    public PostResponseDto save(CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=\" + id")
        );
        Comment comment = new Comment(commentRequestDto.getContent(), post);
        commentRepository.save(comment);
        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getComments(), post.getCreatedDate(), post.getLastModifiedDate());
    }
}
