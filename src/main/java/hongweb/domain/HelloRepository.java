package hongweb.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository  //Entiry 와 같은 위치에  Repository 생성하게되면 생략가능   <Dao 역할을 함 >
public interface HelloRepository extends JpaRepository<HelloEntity, Long> {

}
