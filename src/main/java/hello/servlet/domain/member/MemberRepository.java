package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 동시성 문제가 고려되어 있지 않음
 * 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

//    key : id, value : Member
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }
//    싱글톤을 만들 땐 private로 생성자를 막아줘야 함!!
//    아무나 생성하지 못하게 해 줘야 함

    private MemberRepository(){
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
//        이렇게 하면 스토어에 있는 모든 값 다 꺼내서 새로운 arraylist에 담아서 넘겨줌
//        스토어 자체를 보호하기 위해 하는 것
    }

    public void clearStore() {
        store.clear();
//        스토어를 다 날려버리는 것
    }

}