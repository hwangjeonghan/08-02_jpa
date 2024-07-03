package com.ohgiraffers.mapping.section06.idcalss;

import jakarta.persistence.*;

@Entity(name = "member_section06_idclass")
@Table(name = "tbl_member_section06_idclass")
@IdClass(MemberPK.class)
public class Member {

    @Id
    @Column(name = "member_no")
    private int memberNo;

    @Id
    @Column(name = "member_id")
    private String memberid;

    @Id
    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickName")
    private String nickName;

    public Member() {
    }

    public Member(int memberNo, String memberid, String memberPwd, String nickName) {
        this.memberNo = memberNo;
        this.memberid = memberid;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
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

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", memberid='" + memberid + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}