package SecondWeek_Servlet.SecondWeek_Servlet.domain;


import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Getter
    private static final MemoryMemberRepository instance = new MemoryMemberRepository();

    // 외부에서 new 막기
    private MemoryMemberRepository() {}

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // ✅ 실제 저장된 회원 목록
    }
}