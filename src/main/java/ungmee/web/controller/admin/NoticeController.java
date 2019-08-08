package ungmee.web.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ungmee.web.dao.NoticeDao;
import ungmee.web.entity.Event;
import ungmee.web.entity.EventFile;
import ungmee.web.entity.Notice;
import ungmee.web.security.CustomUserDetails;

@Controller("adminNoticeController")
@RequestMapping("admin/notice/")
public class NoticeController {
	@Autowired
	private NoticeDao noticeDao;

	@GetMapping("reg")
	public String reg() {
		return "admin/notice/reg";
	}

	@PostMapping("reg")
	public String reg(Notice notice, String category, MultipartFile[] file, HttpServletRequest request)
			throws IOException {
		/* ,Authentication auth */
		notice.setAdminId(61);
		// CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		// notice.setAdminId(userDetails.getId()); //占쏙옙占쏙옙占� 占싸깍옙占쏙옙 占쏙옙占쏙옙
		// UserDetails p.640
		// System.out.println(principal.getName());

		System.out.println(notice);
		noticeDao.insert(notice);

		return "redirect:list";
	}
	
	@PostMapping("upload")
	@ResponseBody
	public String upload(MultipartFile file, HttpServletRequest request)
			throws IOException {
		
		String fileName;
		String urlPath = "/upload";
		String realPath = request.getServletContext().getRealPath(urlPath);

		
			fileName = file.getOriginalFilename();
			String path = realPath + File.separator + fileName;

			System.out.println(realPath);

			File filePath = new File(realPath);
			if (!filePath.exists())
				filePath.mkdirs();

			File sameFile = new File(path);
			if (sameFile.exists()) {
				int ne = fileName.lastIndexOf(".");
				String name = fileName.substring(0, ne);
				String suffix = fileName.substring(ne);
				int parenS = name.lastIndexOf("(");
				int parenE = name.lastIndexOf(")");

				if (parenE == -1) {
					fileName = name + "(" + 1 + ")" + suffix;
					path = realPath + File.separator + fileName;
					System.out.println(path);
				} else {
					String indexC = name.substring(parenS + 1, parenE);
					int indexN = Integer.parseInt(indexC);
					indexN++;
					fileName = fileName.substring(0, parenS + 1) + indexN + ")" + suffix;
					path = realPath + File.separator + fileName;
					System.out.println(path);
				}
			
			InputStream fis = file.getInputStream();
			OutputStream fos = new FileOutputStream(path);

			int j = 0;
			byte[] arr = new byte[1024];

			while ((j = fis.read(arr)) != -1) {
				fos.write(arr, 0, j);
			}
			

			fos.close();
			fis.close();

		       
	

		
		}
		return "/upload/"+ fileName;
	}
	
	// 스마트에디터 파일
//	@RequestMapping(value="/upload", method=RequestMethod.POST)
//	public @ResponseBody String photoUpload(MultipartHttpServletRequest request, HttpServletResponse response, Model model){
//	        System.out.println("upload controller");
//	 
//	        try {
//	            Iterator<String> itr = request.getFileNames();
//	            MultipartFile mpf;
//	 
//	            while (itr.hasNext()) {
//	                mpf = request.getFile(itr.next());
//	                System.out.println(mpf.getOriginalFilename());
//	                
//	                String filename = mpf.getOriginalFilename();
//	                String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
//	                filename_ext = filename_ext.toLowerCase();
//	                String dftFilePath = request.getSession().getServletContext().getRealPath("/");
//	 
//	                //파일 기본경로 _ 상세경로
//	                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
//	                String folderName = formatter.format(new java.util.Date());     
//	                
//	                String filePath = dftFilePath +  "upload" + File.separator + folderName + File.separator;
//	                
//	              
//	                //String filePath = dftFilePath + "images" + File.separator + "upload" + File.separator + folderName + File.separator;
//	                //C:\Users\wook\Desktop\eGov\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BizV3\images\photo_upload\201703
//	                System.out.println("filePath = " + filePath);
//	 
//	                String realFileNm = "";
//	                formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//	                String today= formatter.format(new java.util.Date());
//	                realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
//	                String rlFileNm = filePath + realFileNm;
//	                //C:\Users\wook\Desktop\eGov\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BizV3\images\photo_upload\201703\2017030809570465a56af2-b3d6-4283-a0ae-1f0b19434864.jpg
//	                System.out.println("rlFileNm = " + rlFileNm);
//	                
//	                try {                
//	                    File file = new File(rlFileNm);
//	                    if(!file.exists()) {
//	                        file.mkdirs();
//	                    }                    
//	                    mpf.transferTo(file);
//	                } catch (IOException e) {
//	                    e.printStackTrace();
//	                }                
//	 
//	                String json = "";
//	                json +=    "{\"files\":[{";
//	                json +=    "\"name\":\""+realFileNm+"\",";
//	                json +=    "\"url\":\""+request.getContextPath()+"upload/"+folderName+"/"+realFileNm+"\"";
//	
//	                json +=    "}]}";
//	                
//	                return json;
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        
//	        return "";
//	    }

	@GetMapping("edit")
	public String edit(Integer eid, Model model) {
		model.addAttribute("notice", noticeDao.get(eid));
		System.out.println(noticeDao.get(eid));
		return "admin/notice/edit";
	}

	@PostMapping("edit")
	public String edit(Notice notice) {

		Notice n = noticeDao.get(notice.getId());
		n.setCategory(notice.getCategory());
		n.setTitle(notice.getTitle());
		n.setContent(notice.getContent());

		noticeDao.update(n);
		return "redirect:list";
	}

	@RequestMapping("list")
	public String list(Model model) {

		List<Notice> notice = noticeDao.getList();
		model.addAttribute("notice", notice);

		return "admin/notice/list";
	}

	@GetMapping("del")
	public String del(Integer did) {
		System.out.println(noticeDao.get(did));
		noticeDao.delete(did);
		return "redirect:list";
	}

}