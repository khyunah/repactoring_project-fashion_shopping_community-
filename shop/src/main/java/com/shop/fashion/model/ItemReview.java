package com.shop.fashion.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ItemReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "itemId")
	@JsonIgnoreProperties({"itemReviews"})
	private Item item;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private String originImageTitle;
	@Column(nullable = false)
	private String imageUrl;
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({"password", "role", "email", "oauth"})
	private User user;
	@CreationTimestamp
	private Timestamp timestamp;
}
