package nefu.laboratory.exception;


import lombok.*;
import nefu.laboratory.dto.Code;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class XException extends RuntimeException{
    private Code code;
    private int codeN;
    private String message;
}
