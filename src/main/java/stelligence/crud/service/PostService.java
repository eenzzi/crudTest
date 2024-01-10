package stelligence.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stelligence.crud.dto.*;
import stelligence.crud.entity.Comment;
import stelligence.crud.entity.Post;
import stelligence.crud.repository.PostRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        postRepository.deleteById(id);

        return findAll();
    }

    //수정
    public PostResponseDto update(Long id, PostContentRequestDto postContentRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );
        if (post.isDeleted()) {
            throw new IllegalArgumentException("이미 삭제된 게시글입니다.");
        }
        post.update(postContentRequestDto.getContent());
        return PostResponseDto.from(post);
    }

    //단건조회
    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
//        Post post = new Post()
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
                .filter(post -> !post.isDeleted())
                .map(PostListResponseDto::from)
                .toList();
    }
}
