package ezenweb.dto;

import ezenweb.domain.member.MemberEntity;
import ezenweb.domain.member.Role;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OauthDto {

    //아이디
    private String mid;
    //이름
    private String mname;
    //이메일
    private String memail;
    //회원정보
    private Map<String,Object> attributes;
    //회원정보 요청 키
    private String nameAttributeKey;
    //클라이언트id
    private String registrationid;

    //1. 클라이언트 구분 메소드 [ 네이버 , 카카오 ] p.186 -> 1
    public static OauthDto of(String registrationId, String nameAttributeKey, Map<String,Object> attributes){
        if(registrationId.equals("naver")){
            return ofnaver(registrationId,nameAttributeKey,attributes);
        }else if (registrationId.equals("kakao")){
            return ofkakao(registrationId,nameAttributeKey,attributes);
        }
        return null;
    }

    //2 만약에 registrationId 가.네이버 이면
    public static OauthDto ofnaver(String registrationId,String nameAttributeKey, Map<String, Object> attributes){
        Map<String,Object> response= (Map<String, Object>)attributes.get(nameAttributeKey);
        System.out.println("네이버로 로그인");
       String mid=((String)response.get("email")).split("@")[0];

       return OauthDto.builder()
                .mid(mid)
               .mname((String)response.get("email"))
               .nameAttributeKey(nameAttributeKey)
               .registrationid(registrationId)
               .attributes(attributes)
               .build();
    }
    //3. 만약에 registrationId 가 카카오면
    public static OauthDto ofkakao(String registrationId,String nameAttributeKey, Map<String, Object> attributes){

        Map<String,Object> kakao_accout=(Map<String,Object>)attributes.get(nameAttributeKey);
        Map<String,Object> profile=(Map<String,Object>)attributes.get("profile");

        String mid=((String)kakao_accout.get("email")).split("@")[0];
        System.out.println("카카오로 로그인");
        return OauthDto.builder()
                        .mid(mid)
                                .mname((String)profile.get("nickname"))
                                        .memail((String)kakao_accout.get("email"))
                                                .registrationid(registrationId)
                                                        .attributes(attributes)
                                                                .build();
    }
    //oauth 정보 -> entity 변경
    public MemberEntity toentitiy(){
        return MemberEntity.builder()
                .mid(this.mid)
                .mname(this.mname)
                .memail(this.memail)
                .oauth(this.registrationid)
                .role(Role.MEMBER)
                .build();
    }
}
