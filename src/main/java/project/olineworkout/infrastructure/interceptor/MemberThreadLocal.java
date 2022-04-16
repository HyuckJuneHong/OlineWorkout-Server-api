package project.olineworkout.infrastructure.interceptor;

import project.olineworkout.domain.entity.member.Member;

public class MemberThreadLocal {

    private static final ThreadLocal<Member> threadLocal;

    static {
        threadLocal = new ThreadLocal<>();
    }

    public static void set(Member member){
        threadLocal.set(member);
    }

    public static Member get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}
