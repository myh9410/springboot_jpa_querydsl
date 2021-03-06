package com.springboot.board.repository;

import com.springboot.board.MockHelper;
import com.springboot.board.config.persistence.DataSourceConfiguration;
import com.springboot.board.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest //@SpringBootTest는 실제 DataSourceConfiguration을 활용하여 테스트를 진행하게 된다.
@Transactional  //실제 DB에 데이터가 추가,수정,삭제 되는 것을 방지하기 위하여 @Transactional을 추가한다.
@Import(DataSourceConfiguration.class)
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @DisplayName("게시판 조회 레포 테스트 - boardRepository.findByNo - optional이 null인 경우")
    @Test
    public void findByNoTestWithNoOptionalResult() {

        //given
        final Board board = MockHelper.getMockBoard().toEntity();

        //when
        Optional<Board> optionalBoard =  boardRepository.findByNo(board.getNo());

        //then
        assertTrue(optionalBoard.isEmpty());

    }

    @DisplayName("게시판 조회 레포 테스트 - boardRepository.findByNo - Board가 정상적으로 조회되는 경우")
    @Test
    public void findByNoTestWithValidOptionalResult() {

        //given
        Board board = Board.builder()
                .no(1L)
                .title("제목1")
                .content("내용1")
                .build();

        //when
        Optional<Board> optionalBoard =  boardRepository.findByNo(board.getNo());

        //then
        assertTrue(optionalBoard.isPresent());
        assertEquals(optionalBoard.get().getNo(), board.getNo());
        assertEquals(optionalBoard.get().getTitle(), board.getTitle());
        assertEquals(optionalBoard.get().getContent(), board.getContent());

    }

    @DisplayName("게시판 추가 레포 테스트 - boardRepository.save")
    @Test
    public void saveTest() {

        //given
        Board beforeBoard = MockHelper.getMockBoard().toEntity();

        //when
        Board afterBoard =  boardRepository.save(beforeBoard);

        //then
        assertEquals(beforeBoard, afterBoard);

    }

    @DisplayName("게시판 삭제 레포 테스트 - boardRepository.deleteById")
    @Test
    public void deleteTest() {
        //given
        Board board = MockHelper.getMockBoard().toEntity();
        Board afterBoard = boardRepository.save(board);
        Long no = afterBoard.getNo();

        //when
        boardRepository.deleteById(no);

        //then
        assertFalse(boardRepository.existsById(no));
    }
}
