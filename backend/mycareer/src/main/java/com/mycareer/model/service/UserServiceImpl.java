
package com.mycareer.model.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycareer.model.dto.User;
import com.mycareer.model.dto.user.Award;
import com.mycareer.model.dto.user.Qualification;
import com.mycareer.model.dto.user.Url;
import com.mycareer.model.repo.AwardRepository;
import com.mycareer.model.repo.QualificationRepository;
import com.mycareer.model.repo.UrlRepository;
import com.mycareer.model.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository ur;
	
	@Autowired
	private AwardRepository ar;
	
	@Autowired
	private QualificationRepository qr;
	
	@Autowired
	UrlRepository Urlr;
	
	@Override
	public User findByUserId(int userId) {
		try {
			User user=ur.findByUserId(userId);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	// 모든 유저 정보 찾기
	public List<User> findAll() {
		try {
			if(Objects.isNull(ur.findAll()))
				return null;
			else
				return ur.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Award> findAllByUserId(int userId) {
		try {
			if(Objects.isNull(ar.findAllByaUserUserId(userId)))
				return null;
			else
				return ar.findAllByaUserUserId(userId);
			
		}catch(Exception e) {
			e.printStackTrace();			
			return null;
		}
	}
	@Override
	public List<Qualification> findAllByqUserUserId(int userId) {
		try {
			if(Objects.isNull(qr.findAllByqUserUserId(userId)))
				return null;
			else
				return qr.findAllByqUserUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Qualification saveQ(Qualification q) {
		try {
			Qualification result=qr.save(q);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Url> findAllByuUserUserId(int userId) {
		try {
			if(Objects.isNull(Urlr.findAllByuUserUserId(userId)))
				return null;
			else
				return Urlr.findAllByuUserUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int deleteByqualificationId(int qualificationId) {
		try {
			qr.deleteByqualificationId(qualificationId);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int deleteByurlId(int urlId) {
		try {
			Urlr.deleteByurlId(urlId);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public Url saveUrl(Url u) {
		try {
			Url result=Urlr.save(u);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
