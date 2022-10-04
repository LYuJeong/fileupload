package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.BoardFileDTO;


@Mapper
public interface BoardMapper {
void insertBoard(BoardDTO board);
List<BoardDTO> selectBoardList();
void insertBoardFile(BoardFileDTO boardFile);
int selectBoardIdx();
BoardDTO selectBoardDetail(int boardIdx);
List<BoardFileDTO> selectBoardFileList(int boardIdx);
BoardFileDTO selectBoardFile(int idx);
}

 