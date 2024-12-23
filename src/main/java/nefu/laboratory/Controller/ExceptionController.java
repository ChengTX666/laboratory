package nefu.laboratory.Controller;

import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.dto.Code;
import nefu.laboratory.dto.ResultVO;
import nefu.laboratory.dto.exception.XException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(XException.class)
    public ResultVO handleValidException(XException e){
        if(e.getCode() !=null){
            return ResultVO.error(e.getCode());
        }
        return ResultVO.error(e.getCodeN(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO handleAllException(Exception e){
        return ResultVO.error(Code.ERROR,e.getMessage());
    }
}
