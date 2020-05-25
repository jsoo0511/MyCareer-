
package com.mycareer.model.dto.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mycareer.model.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "url")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Url {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int urlId;
	
	@Column(name="url")
	String url;
	
	@Column(name = "url_info")
	String urlInfo;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	User uUser;

}