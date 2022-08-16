package com.shop.fashion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.fashion.model.Follow;
import com.shop.fashion.model.Follower;
import com.shop.fashion.model.User;
import com.shop.fashion.repository.FollowRepository;
import com.shop.fashion.repository.FollowerRepository;

@Service
public class FollowService {
	
	@Autowired
	private FollowRepository followRepository;
	@Autowired
	private FollowerRepository followerRepository;
	@Autowired
	private UserService userService;
	
	// 팔로우
	@Transactional
	public void followSave(int userId, User me) {
		User followUser = userService.getUser(userId);
		Follow followEntity = Follow.builder()
				.me(me)
				.followUser(followUser)
				.build();
		
		followRepository.save(followEntity);
		followerSave(userId, me);
	}
	
	// 팔로워
	@Transactional
	public void followerSave(int userId, User follower) {
		User me = userService.getUser(userId);
		Follower followerEntity = Follower.builder()
				.me(me)
				.followerUser(follower)
				.build();
		followerRepository.save(followerEntity);
	}
	
	// 팔로우 취소
	@Transactional
	public void cancleFollow(int cancleId, int myId) {
		Follow followEntity = checkFollow(cancleId, myId);
		followRepository.deleteById(followEntity.getId());
		Follower followerEntity = followerRepository.mFindByMyIdAndFollowerUserId(cancleId, myId);
		followerRepository.deleteById(followerEntity.getId());
	}
	
	// 팔로우 수
	@Transactional
	public long followCount(int id) {
		return followRepository.mCountByMyId(id);
	}
	
	// 팔로워 수
	@Transactional
	public long followerCount(int id) {
		return followerRepository.mCountByMyId(id);
	}
	
	// 팔로우버튼 체크
	@Transactional
	public Follow checkFollow(int userId, int myId) {
		return followRepository.mFindByMyIdAndFollowUserId(myId, userId);
	}
	
	// 팔로우 리스트
	@Transactional
	public List<Follow> getFollowList(int userId){
		return followRepository.mFindByMyId(userId);
	}
	
	// 팔로워 리스트
	@Transactional
	public List<Follower> getFollowerList(int userId){
		return followerRepository.mFindByMyId(userId);
	}
}
