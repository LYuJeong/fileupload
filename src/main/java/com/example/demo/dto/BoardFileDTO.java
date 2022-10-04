package com.example.demo.dto;

import lombok.Data;

@Data
public class BoardFileDTO {
	private int idx;
	private int boardIdx;
	private String fileName;
	private String filePath;
	private long fileSize;
}