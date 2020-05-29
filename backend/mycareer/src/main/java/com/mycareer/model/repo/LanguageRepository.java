package com.mycareer.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.user.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer>{
	
	// 단일로 찾기
	Language findByLanguageNo(int languageNo);
	
	// 유저에 해당되는 모든 언어 가져오기
	List<Language> findAllBylUserUserNo(int userNo);

}

