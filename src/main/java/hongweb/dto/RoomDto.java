package hongweb.dto;

import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoomDto {

    private String roomname;
    private String x;
    private String y;
}
