package com.ohgiraffers.mapping.section04.enumtype;

import jakarta.persistence.*;

import java.util.Date;


@Entity(name = "member_section04")
@Table(name = "tbl_member_section04")
public class Member {

    @Id
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;


    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nick_name")
    @Transient
    private String nickName;

    @Column(name="phone", columnDefinition="varchar(200) default '010-0000-0000'")
    private String phone;

    @Column(name = "address", unique = true)
    private String address;

    @Column(name = "erroll_data")
    @Temporal(TemporalType.TIMESTAMP)  //데이터를 식별하기 좋음 , 시큐리티에서 권한 확인할때 숫자오류 떄매 좋음 string이
    //@Temporal(TemporalType.DATE)
    //@Temporal(TemporalType.TIME)
    private Date enrollDate;

    @Column(name = "member_role", nullable = false)
    //@Enumerated(EnumType.ORDINAL)
    @Enumerated(EnumType.STRING)
    private Roletype memberRole;

    @Column(name = "status")
    private String status;

    public Member() {
    }

    public Member(int memberNo, String memberId, String memberPwd, String nickName, String phone, String address, Date enrollDate, Roletype memberRole, String status) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Roletype getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(Roletype memberRole) {
        this.memberRole = memberRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", enrollDate=" + enrollDate +
                ", memberRole='" + memberRole + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}


