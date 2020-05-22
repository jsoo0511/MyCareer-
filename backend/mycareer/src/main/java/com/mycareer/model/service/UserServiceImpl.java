
package com.mycareer.model.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycareer.model.dto.User;
import com.mycareer.model.dto.user.Award;
import com.mycareer.model.dto.user.Career;
import com.mycareer.model.dto.user.Language;
import com.mycareer.model.repo.AwardRepository;
import com.mycareer.model.repo.CareerRepository;
import com.mycareer.model.repo.LanguageRepository;
import com.mycareer.model.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository ur;
	
	@Autowired
	private AwardRepository ar;
	
	@Autowired
	private CareerRepository cr;
	
	@Autowired
	private LanguageRepository lr;
	
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
	
	
	/** Career 관련 **/
	@Override
	public List<Career> findAllByCareerUserId(int userId) {
		try {
			List<Career> cList = cr.findBycUserUserId(userId);
			if(Objects.isNull(cList))
				return null;
			else
				return cList;
		}catch(Exception e) {
			e.printStackTrace();			
			return null;
		}
	}
	
	@Override
	public Object inserIntoCareer(Career career, int userId) {
		try {
			User user = ur.findByUserId(userId);
			
			if(Objects.isNull(user))
				return null;
			else {
				career.setCUser(user);
				cr.save(career);
				return career;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Object deleteAll(int careerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** Language 관련 **/
	@Override
	public List<Language> findAllByLanguageUserId(int userId) {
		try {
			List<Language> lList = lr.findAllBylUserUserId(userId);
			if(Objects.isNull(lList))
				return null;
			else
				return lList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Object insertIntoLanguage(Language lang, int userId) {
		try {
			User user = ur.findByUserId(userId);
			
			if(Objects.isNull(user))
				return null;
			else {
				lang.setLUser(user);
				lr.save(lang);
				return lang;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Object delete(int languageId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
