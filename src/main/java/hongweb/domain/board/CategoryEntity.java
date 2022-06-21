package hongweb.domain.board;

import hongweb.domain.BaseTime;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="category")
public class CategoryEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;
    private String cname;
    //board 와 연관관계
    @OneToMany(mappedBy = "categoryEntity",cascade=CascadeType.ALL)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

}
