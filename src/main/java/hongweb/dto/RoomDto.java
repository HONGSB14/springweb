package hongweb.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoomDto {

    private String rname;
    private String x;
    private String y;
    private List<MultipartFile> rimg;

}
