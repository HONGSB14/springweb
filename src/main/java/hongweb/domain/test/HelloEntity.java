package hongweb.domain.test;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity                         //JPA
@Getter                        //LOMBOK
@NoArgsConstructor  //LOMBOK
public class HelloEntity {

    @Id  // JPA : pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //JPA :autokey
    private Long id;

    @Column(length =500, nullable = false)      //JPA : Column (속성명 = 값 , 속성명 = 값)      length=필드길이 , nullable =null포함여부
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)   //columnDefinition="TEXT" 긴글
    private String content;

    private String author;
}
