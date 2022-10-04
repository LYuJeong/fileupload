package com.example.demo.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.dto.BoardFileDTO;

@Component
public class FileUtils {
	public List<BoardFileDTO> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest)
			throws Exception {
		if (ObjectUtils.isEmpty(multipartHttpServletRequest))
			return null;
		List<BoardFileDTO> fileList = new ArrayList<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/" + current.format(format);
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		String contentType, originalExtension = "", newFileName;
		while (iterator.hasNext()) {
			String name = iterator.next();
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);

			for (MultipartFile multipartFile : list) {
				if (!multipartFile.isEmpty()) {
					contentType = multipartFile.getContentType();
					if (ObjectUtils.isEmpty(contentType))
						break;
					else {
						if (contentType.contains("image/jpeg"))
							originalExtension = ".jpg";
						else if (contentType.contains("image/png"))
							originalExtension = ".png";
						else if (contentType.contains("image/gif"))
							originalExtension = ".gif";
						else originalExtension="";
					}
					newFileName = Long.toString(System.nanoTime()) + originalExtension;
					BoardFileDTO boardFile = new BoardFileDTO();
					boardFile.setBoardIdx(boardIdx);
					boardFile.setFileSize(multipartFile.getSize());
					boardFile.setFileName(multipartFile.getOriginalFilename());
					boardFile.setFilePath(path + "/" + newFileName);
					fileList.add(boardFile);
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
}