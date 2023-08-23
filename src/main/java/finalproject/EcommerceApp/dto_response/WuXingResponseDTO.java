package finalproject.EcommerceApp.dto_response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WuXingResponseDTO {
    private Long id;
    private String birthTime;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
}
