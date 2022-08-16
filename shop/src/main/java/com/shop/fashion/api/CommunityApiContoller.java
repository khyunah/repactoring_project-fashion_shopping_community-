package com.shop.fashion.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.fashion.auth.PrincipalUserDetail;
import com.shop.fashion.dto.LikeListDto;
import com.shop.fashion.dto.ResponseDto;
import com.shop.fashion.model.CommunityBoard;
import com.shop.fashion.model.CommunityLike;
import com.shop.fashion.model.Follow;
import com.shop.fashion.model.Follower;
import com.shop.fashion.model.Reply;
import com.shop.fashion.service.CommunityService;
import com.shop.fashion.service.FollowService;
import com.shop.fashion.service.LikeService;

@RestController
public class CommunityApiContoller {

	@Autowired
	private CommunityService communityService;
	@Autowired
	private LikeService likeService;
	@Autowired
	private FollowService followService;

	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody CommunityBoard board) {
		communityService.modifyBoard(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		communityService.deleteById(id);
		return new ResponseDto<>(HttpStatus.OK.value(), 1);
	}

	// 댓글 쓰기
	@PostMapping("/community/reply-insert/{boardId}")
	public ResponseDto<Reply> insertReply(@PathVariable int boardId, @RequestBody Reply reply,
			@AuthenticationPrincipal PrincipalUserDetail userDetail) {
		Reply replyEntity = communityService.insertReply(boardId, reply, userDetail.getUser());
		return new ResponseDto<>(HttpStatus.OK.value(), replyEntity);
	}

	// 댓글 삭제
	@DeleteMapping("/community/reply-delete/{id}")
	public ResponseDto<String> deleteReply(@PathVariable int id, HttpServletRequest request) {
		communityService.deleteReply(id);
		String responceResult = "0";
		if (request.getHeader("Referer") != null) {
			responceResult = String.valueOf(request.getHeader("Referer"));
		}
		return new ResponseDto<>(HttpStatus.OK.value(), responceResult);
	}

	// 댓글 수정
	@PutMapping("/community/reply-update")
	public ResponseDto<Reply> updateReply(@RequestBody Reply reply) {
		Reply replyEntity = communityService.updateReply(reply);
		return new ResponseDto<Reply>(HttpStatus.OK.value(), replyEntity);
	}

	// 좋아요
	@GetMapping("/community/check-like/{communityBoardId}")
	public ResponseDto<CommunityLike> checkLike(@PathVariable int communityBoardId,
			@AuthenticationPrincipal PrincipalUserDetail principalUserDetail) {
		CommunityLike like = communityService.checkLike(communityBoardId, principalUserDetail.getUser());
		return new ResponseDto<CommunityLike>(HttpStatus.OK.value(), like);
	}

	// 좋아요 명단
	@GetMapping("/comminity/like-list/{boardId}")
	public List<LikeListDto> getLikeList(@PathVariable int boardId){
		List<LikeListDto> test =  likeService.getLikeList(boardId);
		return test;
	}
	
	// 팔로우 명단
	@GetMapping("/comminity/follow-list/{userId}")
	public List<Follow> getFollowList(@PathVariable int userId){
		return followService.getFollowList(userId);
	}
	
	// 팔로워 명단
	@GetMapping("/comminity/follower-list/{userId}")
	public List<Follower> getFollowerList(@PathVariable int userId){
		return followService.getFollowerList(userId);
	}
	
	// 팔로우
	@GetMapping("/comminity/m-follow/{userId}")
	public List<Follow> saveMFollow(@PathVariable int userId){
		return followService.getFollowList(userId);
	}
}
