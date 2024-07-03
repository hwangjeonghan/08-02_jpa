package com.ohgiraffers.mapping.section06.compositekey;

import jakarta.persistence.*;

@Entity(name = "member_section06")
@Table(name = "tbl_member_section06") // 어떤 테이블과 매핑할건지
public class Member {

    // 두가지의 값이 조인되어 생성될 때?
    // PK가 한 테이블에 두가지 일 경우나 PK가 없을때 값을 식별할 경우
    @EmbeddedId // 하나로 묶어서 id 두개를 인식못하니까 이걸쓴다 pk에서 컬럼을 같이 붙여서 쓰는거와같다
    private MemberPK memberNo; // pk가 n개 이상이라 class 단위로 잡아줘야한다 복합키

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickName")
    private String nickName;

    public Member() {
    }

    public Member(MemberPK memberNo, String memberPwd, String nickName) {
        this.memberNo = memberNo;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
    }

    public MemberPK getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(MemberPK memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
