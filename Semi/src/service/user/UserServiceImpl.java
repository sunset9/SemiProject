package service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.plan.Plan;
import dto.timetable.Location;
import dto.user.Bookmark;
import dto.user.User;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	
	//요청파라미터 처리(id)
	@Override
	public User getParam(HttpServletRequest req, HttpServletResponse resp) {
		User user = new User();
		
		user.setId(req.getParameter("userid"));
		user.setPassword(req.getParameter("userpw"));
		user.setNickname(req.getParameter("usernickname"));
		user.setSns_idx(Integer.parseInt(req.getParameter("snsIdx")));

		return user;
	}

	//요청 파라미터 처리(소셜)
	@Override
	public User getParamSocial(HttpServletRequest req, HttpServletResponse resp) {
		User user = new User();
		
		user.setId(req.getParameter("id"));
		user.setNickname(req.getParameter("nickname"));
		user.setProfile(req.getParameter("profileImage"));
		user.setSns_idx(Integer.parseInt(req.getParameter("snsIdx")));
		
		return user;
	}

	//회원정보수정 파라미터 처리
	@Override
	public Map<String, String> getParamUpdate(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String> map = new HashMap<>();
		String userid = req.getParameter("userid");
		String nickname = req.getParameter("nickname");
		String currPw = req.getParameter("currPw");
		String newPw = req.getParameter("newPw");
		String newPwCheck = req.getParameter("newPwCheck");
		
		map.put("userid", userid);
		map.put("nickname", nickname);
		map.put("currPw", currPw);
		map.put("newPw", newPw);
		map.put("newPwCheck", newPwCheck);
		
		return map;
	}
	
	// id 로그인처리
	@Override
	public boolean login(User user) {
		if( userDao.selecCntUserByUseridAndUserpw(user) == 1 ) {
			return true;
		} else {
			return false;
		}
	}
	
	// social 로그인처리
	@Override
	public boolean socialLogin(User user) {
		//로그인 처리 전에 아이디 조회후
		//db에 아이디 검색후 있는 아이디면 바로 로그인처리, 없는 아이디면 db에 저장
		boolean checkid = checkid(user);
		if(checkid) {
			//true : 존재하지 않는 아이디 -> db에 저장
			userDao.insert(user);
			return true; 
		} else {
			//false : 존재하는 아이디 -> 바로 로그인 처리
			return true;
		}
	}

	//이메일로 유저조회
	@Override
	public User getUserByid(User user) {
		return userDao.selectUserByid(user);
	}

	//회원가입 처리
	@Override
	public void join(User user) {
		userDao.insert(user);
		System.out.println("INSERT success");
	}

	//아이디로 회원 삭제
	@Override
	public void deleteUserByid(User user) {
		userDao.delete(user);
		
	}

	//현재 로그인한 유저의 비밀번호 확인
	@Override
	public int checkPw(User user) {
		
		return userDao.chechPw(user);
	}

	//회원정보수정
	@Override
	public void updateUserInfo(Map<String, String> param) {
		User user = new User();
		
		user.setId(param.get("userid"));
		user.setPassword(param.get("currPw"));

		System.out.println("제대로나오나:"+user.getId());
		System.out.println("제대로나오나:"+user.getPassword());
		
//		if (userDao.chechPw(user) == 1)
		
		
//		userDao.update(param);
	}

	//아이디 중복 조회
	@Override
	public boolean checkid(User user) {
		if( userDao.checkid(user) == 0 ) {
			System.out.println("아이디 중복되지 않음, 존재하지 않는 아이디");
			return true;
		} else {
			System.out.println("아이디 중복, 존재하는 아이디");
			return false;
		}
	}

	//닉네임 변경
	@Override
	public void changeNick(Map<String, String> param) {
		User user = new User();
		
		user.setId(param.get("userid"));
		user.setNickname(param.get("nickname"));
		
		userDao.changeNick(user);
	}

	//비밀번호 변경
	@Override
	public void changePw(Map<String, String> param) {
		User user = new User();
		
		user.setId(param.get("userid"));
		user.setPassword(param.get("newPw"));

		//param의 newPw와 newPwCheck 값 비교
		//같으면 비번 변경
		if( param.get("newPw").equals(param.get("newPwCheck"))) {
			userDao.changePw(user);			
		}
	}

	//내 일정 가져오기
	@Override
	public List<Plan> getPlanner(User user) {
		return userDao.getPlanner(user);
	}

	//내 북마크 가져오기
	@Override
	public List<Bookmark> getBookmarkList(User user) {
		return userDao.getBookmarkList(user);
	}

	//내 일정 포스팅 개수 가져오기
	@Override
	public int getCntPlan(User user) {
		return userDao.getCntPlan(user);
	}

  
	// 좌표와 좌표 계산
	//calculate haversine distance for linear distance
	//startLat : 시작 위도, startLong : 시작 경도, endLat : 도착 위도, endLong : 도착 경도
	double getDistance(double startLat, double startLong, double endLat, double endLong)
	{
		double d2r = (Math.PI / 180.0);
		
	    double dlong = (endLong - startLong) * d2r;
	    double dlat = (endLat - startLat) * d2r;
	    double a = Math.pow(Math.sin(dlat/2.0), 2) + Math.cos(startLat*d2r) * Math.cos(endLat*d2r) * Math.pow(Math.sin(dlong/2.0), 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double d = 6367 * c;

	    return d;
	}
	
	//내 총 여행거리 가져오기 
	@Override
	public double getTotDist(List<Plan> plannerList) {
		double totDist = 0;
		
		// 등록한 플래너가 하나 이상인 경우
		if(plannerList.size() > 1) {
			for(Plan plan: plannerList) {
				// 해당 플랜에 있는 위치 정보들 방문 순서대로 가져온다
				List<Location> latLngList = userDao.getTotDist(plan);
				
				// 플랜 별로 거리계산
				for(int i = 0 ; i<latLngList.size()-1; i++) {
					Location startLocation = latLngList.get(i);
					Location endLocation = latLngList.get(i + 1);
					
					// 거리 계산
					double dist = getDistance(startLocation.getLat(), startLocation.getLng(), endLocation.getLat(), endLocation.getLng());
					
					// 총 거리에 합한다
					totDist += dist;
				}
			}
		}
		
		return totDist;
	}

	@Override
	public List<User> getSelectAll() {
		return userDao.selectUserAll();
	}

	//현재 유저의 글을 제외한 모든 글 가져오기 
	@Override
	public List<Plan> getAllPlanList(User cUser) {
		
		return userDao.getAllPlanList(cUser);
	}

	//임시 비번 발급 
	@Override
	public String createTempPw(int length) {
		
		int index = 0;
		char[] charSet = new char[] {
				'0','1','2','3','4','5','6','7','8','9'
				,'A','B','C','D','E','F','G','H','I','J','K','L','M','N'
				,'O','P','Q','R','S','T','U','V','W','X','Y','Z'
				,'a','b','c','d','e','f','g','h','i','j','k','l','m','n'
				,'o','p','q','r','s','t','u','v','w','x','y','z'};
		
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<length; i++) {
			index = (int) (charSet.length * Math.random());
			sb.append(charSet[index]);
		}
		
		return sb.toString();
	}

	//임시비번으로 비번 변경 
	@Override
	public void changeTempPw(String email, String tempPw) {
		userDao.changeTempPw(email, tempPw);
	}

	//gmail send 설정
	@Override
	public void gmailSend(String userEmail, String tempPw) {
		String user = "semiproject99@gmail.com";
		String password = "tpalvm9999";
		
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true");
		
//		mail.smtp.host : 이메일 발송을 처리해주는 Smtp 서버 
//		mail.smtp.port : smtp 서버와 통신하는 포트, naver의 경우 587
//			주의 : 구글과 네이버의 properties 설정 값 다름 -> 찾아보기
		
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			//수신자 메일 주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			
			//subject
			message.setSubject("Logo에서 임시비밀번호를 보내드립니다."); // 메일 제목
			
			//메일 내용
			message.setText("회원님의 임시 비밀번호입니다.\n"
					+ tempPw + "\n"
					+ "발급된 임시 비밀번호로 로그인하신 후 \n"
					+ "반드시 새 비밀번호로 변경해주시길 바랍니다.");
			
			//send the message
			Transport.send(message); 
			System.out.println("message sent successfully... ");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	// 총 게시물 수, 총 여행거리 정보 추가된 user 객체 반환
	@Override
	public User getUseraddedInfo(User cUser) {
		User user = cUser;
		
		int cntPlan = getCntPlan(user);
		double totDist = getTotDist(getPlanner(user));
		
		user.setTotalPlanCnt(cntPlan);
		user.setTotalDist(totDist);
		
		return user;
	}




}
