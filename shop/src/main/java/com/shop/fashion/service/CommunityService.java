package com.shop.fashion.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.fashion.dto.CommunityCountDto;
import com.shop.fashion.dto.CommunityDto;
import com.shop.fashion.model.CommunityBoard;
import com.shop.fashion.model.CommunityLike;
import com.shop.fashion.model.Item;
import com.shop.fashion.model.Reply;
import com.shop.fashion.model.User;
import com.shop.fashion.repository.CommunityLikeRepository;
import com.shop.fashion.repository.CommunityReplyCountRepository;
import com.shop.fashion.repository.CommunityReplyRepository;
import com.shop.fashion.repository.CommunityRepository;
import com.shop.fashion.repository.ShoppingRepository;

@Service
public class CommunityService {

	@Value("${file.path}")
	private String uploadFolder;
	@Autowired
	private CommunityRepository communityRepository;
	@Autowired
	private CommunityReplyRepository communityReplyRepository;
	@Autowired
	private CommunityLikeRepository communityLikeRepository;
	@Autowired
	private ShoppingRepository shoppingRepository;
	@Autowired
	private CommunityReplyCountRepository communityReplyCountRepository;

	@Transactional
	public Page<CommunityBoard> getCommunityBoardList(Pageable pageable) {
		return communityRepository.findAll(pageable);
	}

	@Transactional
	public List<Item> getItemList() {
		return shoppingRepository.findAll();
	}

	@Transactional
	public CommunityBoard upload(CommunityDto fileDto, User user) {
		String newFileName = fileNameSet(fileDto);
		CommunityBoard communityBoardEntity = fileDto.toEntity(newFileName, user);
		System.out.println("-------------");
		CommunityBoard board = communityRepository.save(communityBoardEntity);
		return board;
	}

	@Transactional
	private String fileNameSet(CommunityDto fileDto) {

		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid.toString() + "." + extracktExt(fileDto.getFile().getOriginalFilename());

		String newFileName = (imageFileName.trim()).replaceAll("\\s", "");

		Path imageFilePath = Paths.get(uploadFolder + newFileName);

		try {
			Files.write(imageFilePath, fileDto.getFile().getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return newFileName;
	}

	@Transactional
	private String extracktExt(String originalFileName) {
		int pos = originalFileName.lastIndexOf(".");
		return originalFileName.substring(pos + 1);
	}

	@Transactional
	public CommunityBoard boardDetail(int id) {
		return communityRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("?????? ?????? ?????? ??? ????????????");
		});
	}

	@Transactional
	public CommunityBoard boardUpdate(int id, CommunityDto dto) {
		CommunityBoard board = boardDetail(id);

		if (!dto.getFile().getOriginalFilename().isEmpty()) {
			String updateFileName = fileNameSet(dto);
			board.setImageUrl(updateFileName);
		}

		board.setContent(dto.getContent());
		board.setTitle(dto.getTitle());
		return board;
	}

	@Transactional
	public void modifyBoard(int id, CommunityBoard board) { // title, content
		CommunityBoard boardEntity = communityRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("?????? ?????? ?????? ??? ????????????");
		});

		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		boardEntity.setImageUrl(board.getImageUrl());
	}

	@Transactional
	public void deleteById(int id) {
		communityRepository.deleteById(id);
	}

	// ???????????? ?????? ?????????
	@Transactional
	public CommunityBoard detailCommunityBoard(int id) {
		return communityRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("???????????? ???????????? ????????????.");
		});
	}

	// ????????? ????????? ????????? ?????? ?????? ?????? ??????
	@Transactional
	public CommunityLike isLike(int boardId, int userId) {
		return communityLikeRepository.findByBoardIdAndUserId(boardId, userId).orElseGet(() -> {
			return new CommunityLike();
		});
	}

	// ????????? ????????? ?????????
	@Transactional
	public List<CommunityLike> myLike(int userId) {
		return communityLikeRepository.findByUserId(userId);
	}

	// ?????? ???????????? ?????? ????????? ?????? ??????
	@Transactional
	public CommunityBoard checkBoard(int id) {
		return communityRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("???????????? ???????????? ?????? ??????????????? ?????????????????????.");
		});
	}

	// ?????? ??????
	@Transactional
	public Reply insertReply(int boardId, Reply reply, User user) {
		// ???????????? ?????? ????????? ??????????????? ??????
		CommunityBoard board = checkBoard(boardId);
		reply.setUser(user);
		reply.setBoard(board);
		return communityReplyRepository.save(reply);
	}

	// ?????? ??????
	@Transactional
	public void deleteReply(int id) {
		communityReplyRepository.deleteById(id);
	}

	// ?????? ??????
	@Transactional
	public Reply updateReply(Reply reply) {
		Reply replyEntity = communityReplyRepository.findById(reply.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("???????????? ???????????? ?????? ??????????????? ?????????????????????.");
		});

		replyEntity.setContent(reply.getContent());

		return replyEntity;
	}

	// ?????????
	@Transactional
	public CommunityLike checkLike(int communityBoardId, User user) {
		CommunityBoard board = checkBoard(communityBoardId);

		CommunityLike like = communityLikeRepository.findByBoardIdAndUserId(communityBoardId, user.getId())
				.orElseGet(() -> {
					return new CommunityLike();
				});

		// ???????????? ???????????? ??????
		if (like.getIsLike() == 1) {
			board.setLikeCount(board.getLikeCount() - 1);
			communityLikeRepository.deleteById(like.getId());
			return null;
			// ???????????? ????????? ??????
		} else {
			board.setLikeCount(board.getLikeCount() + 1);
			like.setBoard(board);
			like.setUser(user);
			like.setIsLike(1);
			communityLikeRepository.save(like);
			return like;
		}
	}

	// ?????? ??????
	@Transactional
	public Page<CommunityBoard> myCommunity(int userId, Pageable pageable) {
		return communityRepository.findByUserId(userId, pageable);
	}
	
	// ?????? like ??????
	@Transactional
	public Page<CommunityLike> myLikeCommunity(int userId, Pageable pageable) {
		return communityLikeRepository.findByUserId(userId, pageable);
	}

	// ?????? ?????? ??? 
	public List<CommunityCountDto> getTotalReplyCountList(){
		return communityReplyCountRepository.getTotalReplyCountList();
	}

}
