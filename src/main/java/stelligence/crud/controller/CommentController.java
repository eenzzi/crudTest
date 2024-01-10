package stelligence.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import stelligence.crud.dto.CommentContentRequestDto;
import stelligence.crud.dto.CommentRequestDto;
import stelligence.crud.dto.CommentResponseDto;
import stelligence.crud.dto.PostResponseDto;
import stelligence.crud.entity.Post;
import stelligence.crud.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    //등록
    @PostMapping
    public PostResponseDto save(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.save(commentRequestDto);
    }

    //수정
    @PatchMapping("/{id}")
    public PostResponseDto update(@PathVariable Long id, @RequestBody CommentContentRequestDto commentContentRequestDto) {
        return commentService.update(id, commentContentRequestDto);
    }


    //삭제
    @DeleteMapping("/{id}")
    public PostResponseDto delete(@PathVariable Long id) {
        return commentService.delete(id);
    }
}
