package gritbus.hipchonbackend.Service;

import static gritbus.hipchonbackend.error.ErrorCode.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import gritbus.hipchonbackend.error.ErrorCode;
import gritbus.hipchonbackend.exception.FileUploadException;
import gritbus.hipchonbackend.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3Service {
	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	public String uploadFile(String category,String loginId, MultipartFile multipartFile){

		String fileName = uploadS3(category, loginId, multipartFile);

		return amazonS3Client.getUrl(bucketName, fileName).toString();
	}

	public List<String> uploadFileList(String category,String loginId, List<MultipartFile> multipartFileList){

		List<String> urlList = new ArrayList<>();

		for (MultipartFile multipartFile : multipartFileList) {
			if (!multipartFile.isEmpty()){
				String fileName = uploadS3(category, loginId, multipartFile);
				urlList.add(amazonS3Client.getUrl(bucketName, fileName).toString());
			}
		}

		return urlList;
	}

	private String uploadS3(String category, String loginId, MultipartFile multipartFile) {
		validateFileExists(multipartFile);

		String fileName = buildFileName(category, loginId, multipartFile.getOriginalFilename());

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(multipartFile.getContentType());

		try (InputStream inputStream = multipartFile.getInputStream()) {
			amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (IOException e) {
			throw new FileUploadException(FILE_BAD_REQUEST.getErrorCode(), FILE_BAD_REQUEST);
		}
		return fileName;
	}

	public void deleteFileList(List<String> fileList){
		for (String image : fileList) {
			deleteFile(image);
		}

	}

	public void deleteFile(String fileName){
		fileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);
		DeleteObjectRequest request = new DeleteObjectRequest(bucketName, fileName);
		amazonS3Client.deleteObject(request);
	}

	private void validateFileExists(MultipartFile multipartFile) {
		if (multipartFile.isEmpty()) {
			throw new NoSuchElementException("파일이 존재하지 않습니다", ELEMENT_NOT_FOUND);
		}
	}

	private String buildFileName(String category, String loginId,String originalFileName) {
		originalFileName = originalFileName
			.replaceAll("[^a-zA-Z0-9가-힣_.]", "") // 알파벳과 한글, 숫자, 언더바와 점만 허용한다.
			.replaceAll(" ","");
		int fileExtensionIndex = originalFileName.lastIndexOf(".");
		String fileExtension = originalFileName.substring(fileExtensionIndex);
		String fileName = originalFileName.substring(0, fileExtensionIndex);
		String now = LocalDateTime.now().toString();

		return category + "/" + loginId + "_" +fileName + "_" + now + fileExtension;
	}
}
