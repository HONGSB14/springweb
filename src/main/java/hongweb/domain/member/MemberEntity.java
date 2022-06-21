package hongweb.domain.member;

import hongweb.domain.BaseTime;
import hongweb.domain.board.BoardEntity;
import hongweb.domain.room.RoomEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@Builder
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;
    private String mid;
    private String mpassword;
    private String mname;

    @OneToMany(mappedBy ="memberEntity" , cascade=CascadeType.ALL)
    List<RoomEntity> roomEntityList;

    @Builder.Default
    @OneToMany(mappedBy="memberEntity" , cascade=CascadeType.ALL)
    List<BoardEntity> boardEntityList = new ArrayList<>();
}
