package com.mycareer.model.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mycareer.model.dto.User;
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

	public ProjectImg upload(MultipartFile file, String uploadPath, String delivery) throws IOException, Exception {

		ProjectImg pimg = new ProjectImg();

		String imgUploadPath = uploadPath + File.separator + delivery;
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);

		String fileName = null;

		if (file != null) {
			int fileIndex = file.getOriginalFilename().lastIndexOf('.') + 1;
			String fileExtension = file.getOriginalFilename().toLowerCase().substring(fileIndex,
					file.getOriginalFilename().length());
			if (!(fileExtension.equals("jpg") || fileExtension.equals("gif") || fileExtension.equals("png")
					|| fileExtension.equals("JPG") || fileExtension.equals("GIF") || fileExtension.equals("PNG"))) {
				return null;
			}
			fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);

			pimg.setSrc(File.separator + delivery + ymdPath + File.separator + fileName);
			return pimg;

		} else {

			pimg.setSrc(File.separator + delivery + ymdPath + File.separator + fileName);
			return pimg;

		}

	}

	// 단일 파일 업로드
	public ProjectImg uploadFile(MultipartFile file, String path, String deli) throws Exception {
		try {
			ProjectImg pimg = upload(file, path, deli);
			return pimg;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 다중 파일 업로드
	public List<Object> uploadFiles(MultipartFile[] files, String path, String deli) {
		List<Object> tList = Arrays.asList(files).stream().map(file -> {
			try {
				return upload(file, path, deli);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList());
		return tList;
	}

}
