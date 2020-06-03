package com.mycareer.model.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mycareer.model.dto.project.ProjectImg;
import com.mycareer.model.repo.ProjectImgRepository;
import com.mycareer.util.UploadFileUtils;

@Service
public class ImageServiceImpl {

	@Autowired
	ProjectImgRepository ir;

	// String imgUploadPath = File.separator + "home" + File.separator + "ubuntu";
	// 이미지 업로드 경로 : home/ubuntu
	// 연, 월, 일을 기준으로 폴더를 나누어서 생성.

	// 단일 파일 업로드
	public ProjectImg upload(MultipartFile file, String uploadPath, String delivery) throws IOException, Exception {

		String imgUploadPath = uploadPath + File.separator + delivery;
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);

		String fileName = null;

		ProjectImg tmp = new ProjectImg();

		if (file != null) {
			int fileIndex = file.getOriginalFilename().lastIndexOf('.') + 1;
			String fileExtension = file.getOriginalFilename().toLowerCase().substring(fileIndex,
					file.getOriginalFilename().length());
			if (!(fileExtension.equals("jpg") || fileExtension.equals("gif") || fileExtension.equals("png")
					|| fileExtension.equals("JPG") || fileExtension.equals("GIF") || fileExtension.equals("PNG"))) {
				return null;
			}
			fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
			tmp.setSrc(File.separator + delivery + ymdPath + File.separator + fileName);
		} else {
			fileName = File.separatorChar + delivery + File.separator + "none.png";
			tmp.setSrc(fileName);
		}
		return tmp;

	}
}
