package ungmee.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.Event;

import ungmee.web.dao.CoupleDao;
import ungmee.web.dao.EventDao;
import ungmee.web.dao.NoticeDao;
import ungmee.web.dao.UserDao;
import ungmee.web.entity.Couple;
import ungmee.web.entity.Notice;
import ungmee.web.entity.User;
import ungmee.web.security.CustomUserDetails;

@Service
public class PushService {
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private EventDao eventDao;
	@Autowired
	private CoupleDao coupleDao;
	@Autowired
	private UserDao userDao;
	
	public User getSenderDetails(int id) {
		User user = userDao.get(id);
		
		return user;
	}

	public Couple getProposeDetail(int id) {
		Couple couple = coupleDao.get(id);
		couple.setRead("T");
		coupleDao.edit(couple);
		couple = coupleDao.get(id);
		return couple;
	}
	
	public int getNewPushCount(int userNum) {	
		System.out.println("service userNum : "+userNum);
		int pCount = coupleDao.getProposeCount(userNum);
		System.out.println("pCount : "+pCount);
		return pCount;
	}

	public List<Map<String,Object>> getNewPushList(int userNum){
		List<Map<String, Object>> list = new ArrayList<>();
		User user = new User();
	//	System.out.println("AccepterId : "+userNum);
		List<Couple> coupleList = coupleDao.getProposeList(userNum);
//		System.out.println("list : "+coupleList.get(0).getAccepterId());
		Map<String,Object> couple ;
		for(Couple c : coupleList) {
			user = userDao.get(c.getProposeId());
			couple = new HashMap<String, Object>();
			couple.put("cName",c.getCoupleName());
			couple.put("regDate",c.getAsk());
			couple.put("id", c.getId());
			couple.put("sender", user.getEmail());
			couple.put("profile",user.getProfile());
			couple.put("senderId", user.getId());
			list.add(couple);
		}
		return list;
	}
//	public int getNewPushedCount() {
//
//		List<Notice> noticeList = noticeDao.getListByMemberId(1);
//		List<Event> eventList = eventDao.getListByMemberId(1);
//
//		int count = noticeList.size() + eventList.size();
//
//		return count;
//
//	}
//
//	public Map<String, Object> getNewPushedList() {
//
//		List<Notice> noticeList = noticeDao.getListByMemberId(1);
//		List<Event> eventList = eventDao.getListByMemberId(1);
//
//		Map<String, Object> model = new HashMap<String, Object>();
//
//		model.put("noticeList", noticeList);
//		model.put("noticeList", noticeList);
//
//		return model;
//
//	}
//
//	public List<Map<String, Object>> getNewPushedList1() {
//	List<Map<String, Object>> list = new ArrayList<>();
//		
//		List<Notice> noticeList = noticeDao.getListByMemberId(1);
//		Map<String, Object> notice = null;
//		
//		for(Notice n : noticeList) {
//			notice = new HashMap<String, Object>();
//			notice.put("title", n.getTitle());
//			notice.put("regDate", n.getRegDate());
//			notice.put("type", "notice");
//			list.add(notice);
//		}		
//		
//		List<Event> eventList = eventDao.getListByMemberId(1);
//		Map<String, Object> event = null;
//		
//		for(Event n : eventList) {
//			event = new HashMap<String, Object>();
//			event.put("title", n.getTitle());
//			event.put("regDate", n.getRegDate());
//			event.put("type", "notice");
//			list.add(event);
//		}
//		
//		
//		Collections.sort(list, new Comparator<Map<String, Object>>() {
//            /*@Override
//            public int compare(Student s1, Student s2) {
//                if (s1.getScore() < s2.getScore()) {
//                    return -1;
//                } else if (s1.getScore() > s2.getScore()) {
//                    return 1;
//                }
//                return 0;
//            }*/
//
//			@Override
//			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
//				
//				Date d1 = (Date) o1.get("regDate");
//				Date d2 = (Date) o2.get("regDate");				
//				
//				if (d1 < d2) {
//                    return -1;
//                } else if (d1 > d2) {
//                    return 1;
//                }
//                return 0;
//			}
//		}
//		
//		return list;
//
//}
	
}