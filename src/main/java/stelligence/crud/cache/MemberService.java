package stelligence.crud.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public Member save(Member member) {
		Member saveMember = memberRepository.save(member);
		log.info("save to DB : " + member);
		return saveMember;
	}

	@Cacheable(key = "#id", cacheNames = "member")
	public Member getMember(Long id) {
		Member findMember = memberRepository.findById(id)
			.orElseThrow(() -> new RuntimeException(id + " 멤버는 존재하지 않습니다."));
		log.info("Member fetching from DB : " + id);
		return findMember;
	}
}
