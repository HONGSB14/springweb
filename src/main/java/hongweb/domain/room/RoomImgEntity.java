package hongweb.domain.room;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roomimg")
public class RoomImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rimgno;     //PK번호
    private String rimg;    //이미지이름

    @ManyToOne    //방번호 [ FK]  다수가 하나에 관계를 맺는다.
    @JoinColumn(name="rno")
    private RoomEntity roomEntity;
}
