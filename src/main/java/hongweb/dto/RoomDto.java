package hongweb.dto;

import hongweb.domain.room.RoomEntity;
import hongweb.domain.room.RoomImgEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoomDto {

    private int rno;                                            //번호
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
    private String rcontents;                              //상세설명

    private List<MultipartFile> rimg;                 //첨부사진
    private String ractive;                                   //거래상태



    //1. Dto -> Entity 로 바꿔주는 메소드 (이유: 반복사용)
    public RoomEntity toEntity(){
        return RoomEntity.builder()
                .rno(this.rno)
                .rtitle(this.rtitle)
                .rlat(this.rlat)
                .rlon(this.rlon)
                .rtrans(this.rtrans)
                .rprice(this.rprice)
                .rarea(this.rarea)
                .rmanagementfee(this.rmanagementfee)
                .rstructure(this.rstructure)
                .rcompletiondate(this.rcompletiondate)
                .rparking(this.rparking)
                .relevator(this.relevator)
                .rindate(this.rindate)
                .rfloor(this.rfloor)
                .rmaxfloor(this.rmaxfloor)
                .rkind(this.rkind)
                .raddress(this.raddress)
                .rcontents(this.rcontents)
                .ractive(this.ractive)
                .roomImgEntityList(new ArrayList<>())
                .build();


    }

}
