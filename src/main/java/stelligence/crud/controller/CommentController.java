package stelligence.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stelligence.crud.dto.CommentRequestDto;
import stelligence.crud.dto.CommentResponseDto;
import stelligence.crud.dto.PostResponseDto;
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
}
