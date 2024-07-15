package com.example.demo;

import java.util.ArrayList;
import java.util.Optional;

public class LoginClass {
	private ArrayList<MemberInfoClass> memberList = null;
	
	LoginClass() {
		memberList = new ArrayList<>();
		MemberInfoClass memberInfo = new MemberInfoClass();
		memberInfo.setId("hong");
		memberInfo.setName("홍길동");
		memberInfo.setPassword("hong12345");	
		
		MemberInfoClass memberInfo2 = new MemberInfoClass();
		memberInfo2.setId("kim");
		memberInfo2.setName("김유두");
		memberInfo2.setPassword("kim12345");
		
		MemberInfoClass memberInfo3 = new MemberInfoClass();
		memberInfo3.setId("lee");
		memberInfo3.setName("이승원");
		memberInfo3.setPassword("lee12345");
		
		memberList.add(memberInfo);
		memberList.add(memberInfo2);
		memberList.add(memberInfo3);
	}
	public boolean login(MemoRepository memoDao, String userId, String userPassword) {
		Long findCount = memoDao.countByUseridAndPassword(userId, userPassword);
		if(findCount <= 0) {
			return false;
		} else {
			return true;
		}
	}
	public boolean logout() {
		return true;
	}
}
