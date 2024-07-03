package com.ohgiraffers.mapping.section06.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable // jpa에서 복합키(n개 이상의 키) 설정 할 때 Serializable을 상속받아야함
public class MemberPK implements Serializable { // 직렬화 시키다 네트워크로 패키지를 전송해줄때 작은단위로 나눠줘야한다

    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    // 위 두개가

    public MemberPK() {
    }

    public MemberPK(int memberNo, String memberId) {
        this.memberNo = memberNo;
        this.memberId = memberId;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    // 해시 함수 또는 해시 알고리즘 또는 해시함수알고리즘은 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다
    @Override // 제정의해줘야한다는 특징
    public int hashCode() {
        return Objects.hash(memberNo, memberId);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj){ //나와 오브젝트가 같은 주소를 쓴다
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        MemberPK memberPK = (MemberPK) obj;
        return memberNo == memberPK.memberNo && Objects.equals(memberId, memberPK.getMemberId());
    }

    @Override
    public String toString() {
        return "MemberPK{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
