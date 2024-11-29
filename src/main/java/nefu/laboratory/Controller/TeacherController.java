package nefu.laboratory.Controller;

import lombok.RequiredArgsConstructor;
import nefu.laboratory.Service.TeacherService;
import nefu.laboratory.dto.ResultVO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/laboratory/teacher/")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("index")
    public ResultVO courseList(@RequestAttribute("uid") String tid){
        //理论课程(已提前分好时间)
        //实验课程(已经预约过的,从预约表里拿)
        return ResultVO.success(Map.of(
                "reservations",teacherService.reservationList(tid),
                "courseTheory",teacherService.courseTheory(tid)
                ));
    }
    @GetMapping("courses/lab")
    public ResultVO courseLab(@RequestAttribute("uid") String tid){
        return ResultVO.success(Map.of("courseLab",teacherService.courseLab(tid)));
    }

    //跳转到选择实验室阶段
    @GetMapping("labs")
    public ResultVO labList(){
        return ResultVO.success(Map.of("labList",teacherService.labList()));
    }
    //点进去实验室,查看具体4×7格的时间表 取出预约记录
    @GetMapping("labs/{lid}")
    public ResultVO lab(@PathVariable("lid") String lid){
        return ResultVO.success(Map.of(
                "reservationLab",teacherService.reservationLab(lid)
        ));
    }
}