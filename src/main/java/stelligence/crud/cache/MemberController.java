package stelligence.crud.cache;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	private final MemberService memberService;

	@PostMapping
	public Member save(@RequestBody Member member) {
		Member saveMember = memberService.save(member);
		log.info("save to DB : " + member);
		return saveMember;
	}

	@GetMapping("/{id}")
	public Member getMember(@PathVariable Long id) {
		Member findMember = memberService.getMember(id);
		log.info("Member fetching from DB : " + id);
		return findMember;
	}
}
