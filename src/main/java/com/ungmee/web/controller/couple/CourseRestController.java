package com.ungmee.web.controller.couple;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ungmee.web.security.CustomUserDetails;


@RestController
@RequestMapping("/couple/course/")
public class CourseRestController {
	@PostMapping("spot-img")
	public String reg(MultipartFile[] files,HttpServletRequest req,Authentication auth) throws IOException {
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		
		int id = user.getId();
	
		String url = "/upload";

		// ��������� ���ε�
		/*
				for(int i = 0; i<files.length; i++) {
					String realPath =req.getServletContext().getRealPath(url);
					String fileName=uuid.toString() + "_" + files[i].getOriginalFilename();//�������� ���� �����̸�
					String path= realPath+File.separator+fileName;
					//���� ���丮 ���
					System.out.println(req.getServletContext().getRealPath("/"));
					//upload ������ �������
					System.out.println(realPath);
					System.out.println(files[i].getSize());
					
					File filePath = new File(realPath);
					if(!filePath.exists())
						filePath.mkdirs();
					
					
					
					InputStream fis =files[i].getInputStream();
					OutputStream fos =new FileOutputStream(path);
					
					int j= 0;
					byte[] arr = new byte[1024];
					
					while((j=fis.read(arr)) != -1) {
						fos.write(arr, 0, i);
					}
					fos.close();
					fis.close();
				}
		 */
		// �� �������� ���ε�
				for(MultipartFile file : files ) {
					//���ε�ø��� �������̵����
					UUID uuid = UUID.randomUUID();
	
					String realPath =req.getServletContext().getRealPath(url);
					String fileName=uuid.toString() + "_" + file.getOriginalFilename();//�������� ���� �����̸�
					String path= realPath+File.separator+fileName;
					System.out.println(path);
					File uploadFile = new File(path);
					//���� ���δ�
					file.transferTo(uploadFile);
					
					
				}
			
		return "okay";
	}
}
