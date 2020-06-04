package com.mycareer.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.util.FileCopyUtils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;

//import net.coobird.thumbnailator.Thumbnails;
//UUID 가 사용될 수 있는곳으로 다음과 같은 곳이 있겠습니다.
//
//1. 업로드된 파일명의 중복을 방지하기 위해 파일명을 변경할 때 사용.
//
//2. 첨부파일 파일다운로드시 다른 파일을 예측하여 다운로드하는것을 방지하는데 사용.
//
//3. 일련번호 대신 유추하기 힘든 식별자를 사용하여 다른 컨텐츠의 임의 접근을 방지하는데 사용.
//
//

public class UploadFileUtils {

	static final int THUMB_WIDTH = 100; // 썸네일의 가로 크기
	static final int THUMB_HEIGHT = 100; // 썸네일의 세로 크기

	public static String fileUpload(String uploadPath, String fileName, byte[] fileData, String ymdPath)
			throws Exception {

		// 랜덤문자 생성
		UUID uid = UUID.randomUUID();

		String newFileName = uid + "_" + fileName; // "랜덤문자_파일명"
		String imgPath = uploadPath + ymdPath; // 업로드 경로 + 연월일 경로 = 이미지 저장 경로

		// 이미지 저장 경로에 원본 파일을 저장
		File target = new File(imgPath, newFileName);

		// 1. 원본 파일 읽기
		FileOutputStream fos = new FileOutputStream(target);
		fos.write(fileData);
		fos.close();

		// 2. 원본파일의 Orientation 정보 읽기
		int orientation = 1;
		int width = 0;
		int height = 0;
		int tempWidth = 0;
		Metadata metadata;
		Directory directory;
		JpegDirectory jpegDirectory;
		try {
			metadata = ImageMetadataReader.readMetadata(target);
			directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
			jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
			if (directory != null) {
				orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
				width = jpegDirectory.getImageWidth();
				height = jpegDirectory.getImageHeight();
			}
			// 3. 변경할 값들을 설정
			AffineTransform atf = new AffineTransform();
			switch (orientation) {
			case 1:
				break;
			case 2: // Flip X
				atf.scale(-1.0, 1.0);
				atf.translate(-width, 0);
				break;
			case 3: // PI rotation
				atf.translate(width, height);
				atf.rotate(Math.PI);
				break;
			case 4: // Flip Y
				atf.scale(1.0, -1.0);
				atf.translate(0, -height);
				break;
			case 5: // - PI/2 and Flip X
				atf.rotate(-Math.PI / 2);
				atf.scale(-1.0, 1.0);
				break;
			case 6: // -PI/2 and -width
				atf.translate(height, 0);
				atf.rotate(Math.PI / 2);
				break;
			case 7: // PI/2 and Flip
				atf.scale(-1.0, 1.0);
				atf.translate(-height, 0);
				atf.translate(0, width);
				atf.rotate(3 * Math.PI / 2);
				break;
			case 8: // PI / 2
				atf.translate(0, width);
				atf.rotate(3 * Math.PI / 2);
				break;
			}
			switch (orientation) {
			case 5:
			case 6:
			case 7:
			case 8:
				tempWidth = width;
				width = height;
				height = tempWidth;
				break;
			}

			BufferedImage image = ImageIO.read(target);
			final BufferedImage afterImage = new BufferedImage(width, height, image.getType());
			final AffineTransformOp rotateOp = new AffineTransformOp(atf, AffineTransformOp.TYPE_BILINEAR);
			final BufferedImage rotatedImage = rotateOp.filter(image, afterImage);
			Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");
			ImageWriter writer = iter.next();
			ImageWriteParam iwp = writer.getDefaultWriteParam();
			iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			iwp.setCompressionQuality(1.0f);

			// 4. 회전하여 생성할 파일을 만든다.
			File uploadFile = new File(imgPath, newFileName);
			FileImageOutputStream fios = new FileImageOutputStream(uploadFile);

			// 5. 원본파일을 회전하여 파일을 저장한다.
			writer.setOutput(fios);
			writer.write(null, new IIOImage(rotatedImage, null, null), iwp);
			fios.close();
			writer.dispose();

			byte[] fileBytes = null;
			FileInputStream inputStream = new FileInputStream(uploadFile);
			fileBytes = IOUtils.toByteArray(inputStream);
			FileCopyUtils.copy(fileBytes, uploadFile);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return newFileName;
	}

	public static String calcPath(String uploadPath) {

		// 캘린더를 불러옴
		Calendar cal = Calendar.getInstance();

		// 캘린더에서 연도만 추출
		String yearPath = File.separator + cal.get(Calendar.YEAR);

		// 캘린더에서 월만 추출. 형식은 00(01월, 02월...11월, 12월)
		// 단, 1월은 캘린더상에서 0이므로, +1을 해줌
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

		// 캘린더에서 날짜만 추출
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		// 원본 이미지가 저장될 경로 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);

		// 썸네일 이미지가 저장될 경로 생성
//		makeDir(uploadPath, yearPath, monthPath, datePath + "thumNail");

		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths) {

		// 폴더가 존재하는지 확인. 폴더가 존재하면 그대로 종료
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}

		// 폴더가 존재하지 않다면, 폴더를 생성
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
		}
	}

}
