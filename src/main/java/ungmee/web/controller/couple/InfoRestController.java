package ungmee.web.controller.couple;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ungmee.web.entity.Couple;
import ungmee.web.security.CustomUserDetails;
import ungmee.web.service.CoupleService;


@RestController("coupleInfoRestController")
@RequestMapping("/couple/info")
public class InfoRestController {

	
	@Autowired
	private CoupleService coupleService;
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		//�ٲٰ��� �ϴ� ���ڿ��� ġȯ 
		return str.replace("-",File.separator);
	}

	@PostMapping("accept")
	public int accept(Authentication auth , int cId) {
		CustomUserDetails aUser = (CustomUserDetails) auth.getPrincipal();
		int id = aUser.getId();
		int result = coupleService.proposeAccept(cId,id);
		return result;
	}
	
	@GetMapping("refuse")
	public int reject( int coupleId) {
		
		int result = coupleService.prposeRefuse(coupleId);
		return result;
	}
	
	@PostMapping("name-update")
	public int nameUpdate(Authentication auth, String name) {
		CustomUserDetails aUser = (CustomUserDetails) auth.getPrincipal();
		int id = aUser.getId();
		int result = coupleService.nameUpdate(name,id);
		return result;
	}
	
	@PostMapping("message-update")
	public int messageUpdate(Authentication auth, String name) {
		CustomUserDetails aUser = (CustomUserDetails) auth.getPrincipal();
		int id = aUser.getId();
		int result = coupleService.messageUpdate(name,id);
		return result;
	}
	
	@PostMapping("profile-upload")
	public int profileUpload(MultipartFile file ,HttpServletRequest req,Authentication auth) throws IOException {
		UUID uuid = UUID.randomUUID();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		int id = user.getId();
		Couple couple = coupleService.get(id);
		

		String url = "/upload";
		String realPath =req.getServletContext().getRealPath(url)+File.separator+getFolder();
		String fileName=uuid.toString() + "_" + file.getOriginalFilename();//�������� ���� �����̸�
		String path= realPath+File.separator+fileName;
	System.out.println(req.getServletContext());
	System.out.println(req.getServletContext().getRealPath("/"));
		System.out.println(realPath);
		
		File filePath = new File(realPath);
		if(!filePath.exists())
			filePath.mkdirs();
		
		
		
		InputStream fis =file.getInputStream();
		OutputStream fos =new FileOutputStream(path);
		
		int i= 0;
		byte[] arr = new byte[1024];
		
		while((i=fis.read(arr)) != -1) {
			fos.write(arr, 0, i);
		}
		fos.close();
		fis.close();
		
		
		System.out.println(fileName);
		
		int result = coupleService.editSoloProfile(couple.getId(), fileName);
		
		return result;
	}
}
