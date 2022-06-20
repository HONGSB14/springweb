package hongweb.domain.board;

import hongweb.domain.BaseTime;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;                    //게시판 번호
    private String btitle;           //게시판 제목
    private String bcontent; //게시판 내용
    private int bview;               //게시판 조회수
    private int blike;                  //게시판 좋아요 수
    //작성자 [연관관계]
    //첨부파일 [연관관계]
    //카테고리 [연관관계]
    //댓글 [연관관계]
}
