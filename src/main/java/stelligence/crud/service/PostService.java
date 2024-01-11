package stelligence.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import stelligence.crud.dto.PostContentRequestDto;
import stelligence.crud.dto.PostListResponseDto;
import stelligence.crud.dto.PostRequestDto;
import stelligence.crud.dto.PostResponseDto;
import stelligence.crud.entity.Post;
import stelligence.crud.repository.PostRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	//생성
	public PostResponseDto save(PostRequestDto postRequestDto) {
		Post post = new Post(postRequestDto.getTitle(), postRequestDto.getContent());
		postRepository.save(post);

		return PostResponseDto.from(post);
	}

	//삭제
	public List<PostListResponseDto> delete(Long id) {
		//        Post post = postRepository.findById(id).orElseThrow(
		//                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
		//        );
		//        postRepository.delete(post);
		//        List<Post> postList = postRepository.findAll();
		//        List<PostListResponseDto> list = new LinkedList<>();
		//        for (Post post1 : postList) {
		//            list.add(new PostListResponseDto(post1.getId(), post1.getTitle()));
		//        }
		//        return list;
		postRepository.deleteById(id); //주어진 id를 db에서 삭제

		return findAll();
	}

	//수정
	public PostResponseDto update(Long id, PostContentRequestDto postContentRequestDto) {
		Post post = postRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id) //해당 id가 없을 때 예외 발생
		);

		if (post.isDeleted()) {
			throw new IllegalArgumentException("이미 삭제된 게시글입니다."); //이미 삭제된 게시글이면 던지는 예외
		}

		post.update(postContentRequestDto.getContent());
		return PostResponseDto.from(post);
	}

	//단건조회
	@Transactional(readOnly = true) //데이터를 변경할 일 없는 Read 연산에 사용
	public PostResponseDto findById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
		);

		if (post.isDeleted()) {
			throw new IllegalArgumentException("이미 삭제된 게시글입니다.");
		}

		return PostResponseDto.from(post);
	}

	//목록조회
	@Transactional(readOnly = true)
	public List<PostListResponseDto> findAll() {
		//        List<Post> postList = postRepository.findAll();
		//        List<PostListResponseDto> list = new LinkedList<>();
		//        for (Post post : postList) {
		//            if (post.isDeleted()) {
		//                continue;
		//            }
		//            PostListResponseDto postListResponseDto = new PostListResponseDto(post.getId(), post.getTitle());
		//            list.add(postListResponseDto);
		//        }
		//        return list;
		return postRepository.findAll()
			.stream()
			.filter(post -> !post.isDeleted()) //isDeleted가 false가 아닌 것만 필터링
			.map(PostListResponseDto::from) //PostListResponseDto의 from 메서드를 각 스트림에 적용
			.toList(); //다시 리스트로
	}
}
