package hongweb.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

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
    private int rno;
    private String rname;
    private String x;
    private String y;
    private String rimg;

}
