package stelligence.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import stelligence.crud.repository.PostRepository;

@SpringBootTest
public class CrudTest {

	@Autowired
	PostRepository postRepository;

	// @Test
	// public void test() {
	//
	//     Post newPost = new Post(null, "테스트 제목", "테스트 내용", false);
	//
	//     // Post 저장
	//     Post savedPost = postRepository.save(newPost);
	//
	//     // 검증
	//     assertNotNull(savedPost.getId());
	//     assertEquals("테스트 제목", savedPost.getTitle());
	//     assertEquals("테스트 내용", savedPost.getContent());
	//
	// }

	//    @Test
	//    public void find() {
	////        List<Member> all = memberRepository.findAll();
	////
	////        for (Member member : all) {
	////            System.out.println("member = " + member);
	////        }
	//
	//        Member member = memberRepository.findById(1L).orElseThrow(
	//                () -> new IllegalArgumentException("해당 유저가 없습니다.")
	//        );
	//
	//        System.out.println("member = " + member);
	//    }
	//
	//    @Test
	//    public void delete() {
	//
	//        memberRepository.deleteById(1L);
	//    }
	//
	//    @Test
	//    public void update() {
	//        Member member = memberRepository.findById(2L)
	//                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
	//
	//        int beforeAge = member.getAge();
	//        System.out.println("beforeAge = " + beforeAge);
	//
	//        member.setAge(member.getAge() + 1);
	//
	//        int afterAge = member.getAge();
	//        System.out.println("afterAge = " + afterAge);
	//    }
}
