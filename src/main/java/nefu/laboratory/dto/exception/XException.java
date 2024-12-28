package nefu.laboratory.dto.exception;


import lombok.*;
import nefu.laboratory.dto.Code;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class XException extends RuntimeException{
    public static final XException DATA_ERROR =XException.builder()
            .codeN(400)
            .message("数据不合法,请重新添加")
            .build();
    public static final XException PERMISSION_ERROR =XException.builder()
            .codeN(400)
            .message("这不是你的,别捣乱哦")
            .build();
    private Code code;
    private int codeN;
    private String message;
}
