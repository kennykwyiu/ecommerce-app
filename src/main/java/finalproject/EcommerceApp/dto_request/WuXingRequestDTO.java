package finalproject.EcommerceApp.dto_request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WuXingRequestDTO {
    private String birthTime;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private Long systemUserId;
}
