package com.springboot.board.controller;

import com.springboot.board.dto.BoardResponseDto;
import com.springboot.board.exception.BoardNotFoundException;
import com.springboot.board.service.BoardQueryDslService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("querydsl")
public class BoardQueryDslController {

    private final BoardQueryDslService boardService;

    @ApiOperation(value = "게시글 단일 조회")
    @ApiResponses({
            @ApiResponse(code = 200, response = BoardResponseDto.class, message = ""),
            @ApiResponse(code = 500, response = BoardNotFoundException.class, message = "")
    })
    @GetMapping("/boards/{no}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long no) {
        BoardResponseDto boardResponseDto = boardService.getBoardByNo(no);

        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "전체 게시글 조회 - querydsl")
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = ""),
            @ApiResponse(code = 500, response = List.class, message = "")
    })
    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponseDto>> getAllBoardByQueryDsl() {
        List<BoardResponseDto> list = boardService.getBoardsByQueryDsl();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}