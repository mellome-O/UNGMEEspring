package ungmee.web.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ungmee.web.dao.PartnerDao;
import ungmee.web.dao.UserDao;
import ungmee.web.entity.Partner;
import ungmee.web.entity.Solo;
import ungmee.web.entity.SoloView;
import ungmee.web.entity.User;
import ungmee.web.security.CustomUserDetails;
import ungmee.web.service.MemberShipService;
import ungmee.web.service.PushService;

@Controller
@RequestMapping("/")
public class RootController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PartnerDao partnerDao;
	@Autowired
	private PushService pushService;
	@Autowired
	private MemberShipService msService;
	
	
	@GetMapping("login")
	public String login() {
		return "root.login";
	}
	
	@RequestMapping("withdraw")
	public String withdraw() {
		return "root.withdraw";
	}
	@GetMapping("signup")
	public String signup() {
		return "root.signup";
	}

	
	@GetMapping("partner-signup")
	public String partnersignup() {
		return "partner/signup";
	}
	
	@PostMapping("partner-signup")
	public String partnersignup(User user, Partner partner, @RequestParam("boss-name")String bName, 
			@RequestParam("partner-name")String pName, @RequestParam("partner-type")String pType ) {
		
		String pwd = user.getPw();
		System.out.println("signup-pwd:"+ pwd);
		PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		pwd = pwdEncoder.encode(pwd);
		
		user.setPw(pwd);
		partner.setbName(bName); //사장님이름
		partner.setpName(pName); //상호명
		partner.setpType(pType); //업종
		
		
		//user는 공통회원정보를 담고있는 객체(이메일,비번,닉네임) db 커밋되기 객체 다. 
		//insertUser 는 지금 회원 가입한 사람의 정보를 담고 있는 객체 db 커밋된 객체 를 가져온다.
		
		
		// 공통 회원 정보 삽입
		userDao.insertPartner(user);
		// 방금 가입 회원 정보 가져오기
		User insertUser = userDao.getEmail(user.getEmail());
		//등록된 회원의 ID 가져온다 
		insertUser.getId();
		System.out.println("인서트전 유저아이디" + insertUser.getId());
		partner.setUserId(insertUser.getId());
		System.out.println("인서트후 유저아이디" + partner.getUserId());
		partnerDao.insert(partner);
		
		
		return "redirect:/index" ; 	
	}
	
	@RequestMapping("index")
	public String index(Model model,Authentication auth,HttpServletRequest req) {
		
		model.addAttribute("title","qkRNa");
//		if(auth != null) {
//			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
//			int id = user.getId();
//			SoloView solo = msService.getSoloInfo(id);			
//			int count = pushService.getNewPushCount(id);
//			List<Map<String,Object>> list = pushService.getNewPushList(id);
//			model.addAttribute("count", count);
//			model.addAttribute("list", list);
//			model.addAttribute("user", solo);
//		}
//		int count = pushService.getNewPushedCount();

//		Map<String, Object> map = pushService.getNewPushedList();
//		model.addAttribute("eventList", map.get("eventList"));
		return "root.index";
	}
}
