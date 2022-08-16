package com.shop.fashion.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CommunityBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;

	@Column(nullable = false)
	private String title;

	@ColumnDefault(value = "0")
	private int likeCount;

	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "board", "user" })
	@OrderBy("id desc")
	private List<CommunityLike> communityLikes;

	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "board", "content", "user" })
	@OrderBy("id desc")
	private List<Reply> replies;

	@CreationTimestamp
	private Timestamp createDate;

	@Lob
	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String originImageTitle;

	@Column(nullable = false)
	private String imageUrl;

}
