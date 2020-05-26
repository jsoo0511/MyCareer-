package com.mycareer.model.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.user.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer>{
	List<Url> findAllByuUserUserNo(int userNo);
	@Transactional
	int deleteByurlNo(int urlNo);

}
