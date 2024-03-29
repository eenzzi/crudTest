package stelligence.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import stelligence.crud.dto.CommentContentRequestDto;
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
			() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + commentRequestDto.getPostId())
		);
		Comment comment = new Comment(commentRequestDto.getContent(), post);
		commentRepository.save(comment); //comment repo에 comment 추가
		// post.getComments().add(comment); //post에도 comment 추가

		return PostResponseDto.from(post);

		//        List<Comment> comments = post.getComments(); //post entity에 있는 comment list를 불러옴
		//        comments.add(comment); //post entity에 comment entity 추가
		//
		//        List<CommentResponseDto> list = new ArrayList<>(); //반환하고 싶은 CommentResponseDto 형태의 리스트 생성 및 초기화
		//        //Comment를 CommentResponseDto로 변환
		//        for (Comment comment1 : comments) {
		//            CommentResponseDto commentResponseDto = CommentResponseDto.from(comment1);
		//            list.add(commentResponseDto);     //리스트에 담기
		//        }
		//        log.info("comment.getPost()={}", comment.getPost().getId());
		//        log.info("comment.getPost(){}", comment.getPost().getComments());
		//        log.info("comment.getPost(){}", comment.getPost().getComments().get(0));
		//        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent(), list, post.getCreatedDate(), post.getLastModifiedDate());
	}

	//수정
	public PostResponseDto update(Long id, CommentContentRequestDto commentContentRequestDto) {
		Comment comment = commentRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + id)
		);
		if (comment.isDeleted()) {
			throw new IllegalArgumentException("이미 삭제된 댓글입니다."); //이미 삭제된 게시글이면 던지는 예외
		}
		comment.update(commentContentRequestDto.getContent());
		Post post = comment.getPost();
		return PostResponseDto.from(post);

		//        List<Comment> comments = post.getComments();
		//        List<CommentResponseDto> list = new ArrayList<>();
		//
		//        for (Comment comment1 : comments) {
		//            CommentResponseDto commentResponseDto = CommentResponseDto.from(comment1);
		//            list.add(commentResponseDto);
		//        }

		//        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent(), list, post.getCreatedDate(), post.getLastModifiedDate());
	}

	//삭제
	public PostResponseDto delete(Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + id)
		);
		Post post = comment.getPost();
		comment.delete();

		return PostResponseDto.from(post);
	}
}
