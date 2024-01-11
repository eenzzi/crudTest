package stelligence.crud.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import stelligence.crud.dto.PostContentRequestDto;
import stelligence.crud.dto.PostListResponseDto;
import stelligence.crud.dto.PostRequestDto;
import stelligence.crud.dto.PostResponseDto;
import stelligence.crud.service.PostService;

@Slf4j //로그
@RestController //클래스의 모든 메소드의 반환값이 HTTP 응답 본문(Response Body)으로 사용됨
@RequiredArgsConstructor //final로 선언된 필드의 생성자를 자동 생성
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	//생성
	@PostMapping
	public PostResponseDto save(@RequestBody PostRequestDto postRequestDto) {

		return postService.save(postRequestDto);
	}

	//단건조회
	@GetMapping("/{id}")
	public PostResponseDto findById(@PathVariable Long id) {
		return postService.findById(id);
	}

	//목록조회
	@GetMapping
	public List<PostListResponseDto> findAll(Pageable pageable) {
		return postService.findAll(pageable);
	}

	//수정
	@PatchMapping("/{id}")
	public PostResponseDto update(@PathVariable Long id, @RequestBody PostContentRequestDto postContentRequestDto) {
		return postService.update(id, postContentRequestDto);
	}

	//삭제
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		postService.delete(id);
		return "success";
	}

}
