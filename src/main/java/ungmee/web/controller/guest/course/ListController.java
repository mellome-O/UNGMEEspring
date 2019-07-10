package ungmee.web.controller.guest.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ListController implements Controller {
	private String list;
	public void setList(String list) {
		this.list = list;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("type");
		System.out.println(id);
		ModelAndView mv = new ModelAndView();
		
		if(id.equals("who")) {
			mv = new ModelAndView("/WEB-INF/view/guest/courses/wholist.jsp");
		}
		else if(id.equals("where")) {
			mv = new ModelAndView("/WEB-INF/view/guest/courses/wherelist.jsp");
		}
		else if(id.equals("what")) {
			mv = new ModelAndView("/WEB-INF/view/guest/courses/whatlist.jsp");
		}
		else if(id.equals("search")) {
			mv = new ModelAndView("/WEB-INF/view/guest/courses/searchlist.jsp");
		}
		return mv;
	}

}
