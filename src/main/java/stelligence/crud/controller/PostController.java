package stelligence.crud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stelligence.crud.dto.PostContentRequestDto;
import stelligence.crud.dto.PostListResponseDto;
import stelligence.crud.dto.PostRequestDto;
import stelligence.crud.dto.PostResponseDto;
import stelligence.crud.service.PostService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
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
    public List<PostListResponseDto> findAll() {
        return postService.findAll();
    }

    //수정
    @PatchMapping("/{id}")
    public PostResponseDto update(@PathVariable Long id, @RequestBody PostContentRequestDto postContentRequestDto) {
        return postService.update(id, postContentRequestDto);
    }

    //삭제
    @DeleteMapping("/{id}")
    public List<PostListResponseDto> delete(@PathVariable Long id) {
        return postService.delete(id);
    }
}
