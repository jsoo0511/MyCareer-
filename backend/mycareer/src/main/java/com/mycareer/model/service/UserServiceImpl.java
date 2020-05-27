
package com.mycareer.model.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycareer.model.dto.User;
import com.mycareer.model.dto.user.Award;
import com.mycareer.model.dto.user.Qualification;
import com.mycareer.model.dto.user.Url;
import com.mycareer.model.dto.user.Career;
import com.mycareer.model.dto.user.Language;
import com.mycareer.model.repo.AwardRepository;
import com.mycareer.model.repo.QualificationRepository;
import com.mycareer.model.repo.UrlRepository;
import com.mycareer.model.repo.CareerRepository;
import com.mycareer.model.repo.LanguageRepository;
import com.mycareer.model.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository ur;

	@Autowired
	private AwardRepository ar;

	@Autowired
	private QualificationRepository qr;

	@Autowired
	UrlRepository Urlr;
	private CareerRepository cr;

	@Autowired
	private LanguageRepository lr;

	@Override
	public User findByUserNo(int userNo) {
		try {
			User user = ur.findByUserNo(userNo);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		try {
			if (Objects.isNull(ur.findAll()))
				return null;
			else
				return ur.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 로그인
	@Override
	public User login(String email, String password) {
		try {
			User loginUser = ur.findByEmail(email);
			if (Objects.isNull(loginUser)) {
				throw new Exception("가입되지 않은 이메일입니다.");
			} else {
				if (loginUser.getPassword().equals(password)) {
					return loginUser;
				} else {
					throw new Exception("비밀번호가 틀렸습니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// 회원강비
	@Override
	public User signUp(User user) {
		try {
			User joinUser = ur.findByEmail(user.getEmail());
			if (Objects.isNull(joinUser)) {
				ur.save(user);
				return user;
			}else {
				throw new Exception("이미 존재하는 이메일입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Award> findAllByUserNo(int userNo) {
		try {
			if (Objects.isNull(ar.findAllByaUserUserNo(userNo)))
				return null;
			else
				return ar.findAllByaUserUserNo(userNo);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** Qualification 관련 메서드 **/

	@Override
	public List<Qualification> findAllByqUserUserNo(int userNo) {
		try {
			if (Objects.isNull(qr.findAllByqUserUserNo(userNo)))
				return null;
			else
				return qr.findAllByqUserUserNo(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Qualification saveQ(Qualification q) {
		try {
			Qualification result = qr.save(q);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Qualification updateQ(Qualification q) {
		try {
			Qualification result = qr.save(q);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public int deleteByqualificationNo(int qualificationId) {
		try {
			int result=qr.deleteByqualificationNo(qualificationId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**Url 관련 메서드*/

	@Override
	public List<Url> findAllByuUserUserNo(int userNo) {
		try {
			if (Objects.isNull(Urlr.findAllByuUserUserNo(userNo)))
				return null;
			else
				return Urlr.findAllByuUserUserNo(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Url saveUrl(Url u) {
		try {
			Url result = Urlr.save(u);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int deleteByurlNo(int urlId) {
		try {
			Urlr.deleteByurlNo(urlId);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	/** Career 관련 **/
	@Override
	public List<Career> findAllByCareerUserNo(int userNo) {
		try {
			List<Career> cList = cr.findBycUserUserNo(userNo);
			if (Objects.isNull(cList))
				return null;
			else
				return cList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object inserIntoCareer(Career career, int userNo) {
		try {
			User user = ur.findByUserNo(userNo);

			if (Objects.isNull(user))
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
	public List<Language> findAllByLanguageUserNo(int userNo) {
		try {
			List<Language> lList = lr.findAllBylUserUserNo(userNo);
			if (Objects.isNull(lList))
				return null;
			else
				return lList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object insertIntoLanguage(Language lang, int userNo) {
		try {
			User user = ur.findByUserNo(userNo);

			if (Objects.isNull(user))
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
