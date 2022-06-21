package hongweb.dto;

import hongweb.domain.board.BoardEntity;
import lombok.*;

@Getter@Setter
@ToString@Builder
@AllArgsConstructor @NoArgsConstructor
public class BoardDto {

    private int bno;                   //게시판 번호
    private String btitle;           //게시판 제목
    private String bcontent;   //게시판 내용
    private int bview;               //게시판 조회수
    private int blike;                 //게시판 좋아요 수

    private String category;    //게시물 카테고리
    public BoardEntity getBoardEntity(){

        return BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .blike(this.blike)
                .build();

    }
}
