package com.mycareer.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycareer.model.dto.user.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer>{

}
