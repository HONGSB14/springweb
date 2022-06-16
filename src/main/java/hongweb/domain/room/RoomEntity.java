package hongweb.domain.room;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int rno;                                            //번호        [PK]
    private String rtitle;                                   // 방 이름
    private String rlat;                                       //위도
    private String rlon;                                      //경도
    private String rtrans;                                 //거래방식
    private String rprice;                                 //가격
    private String rarea;                                  // 면적
    private String rmanagementfee;               //관리비
    private String rstructure;                        //구조
    private String  rcompletiondate;             //준공날짜
    private boolean rparking;                          //주차여부
    private boolean relevator;                         //엘레베이터 여부
    private String rindate;                                 //입주가능일
    private int rfloor;                                         //현재층
    private int rmaxfloor;                                   //건물전체층
    private String rkind;                                   //건물종류
    private String raddress;                                //건물주소
    @Column(columnDefinition ="TEXT")
    private String rcontents;                              //상세설명
    private String rimg;                                        //첨부사진
    private String ractive;                                   //거래상태


    @OneToMany(mappedBy = "roomEntity", cascade = CascadeType.ALL)      //하나가 다수와 관계를 맺는다.
    private List<RoomImgEntity> roomImgEntityList = new ArrayList<>();
}
