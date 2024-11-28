package nefu.laboratory.Controller;

import lombok.RequiredArgsConstructor;
import nefu.laboratory.Service.TeacherService;
import nefu.laboratory.dto.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/laboratory/teacher/")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("courses")
    public ResultVO courseList(@RequestAttribute("uid") String tid){
        return ResultVO.success(Map.of("courses",teacherService.courseList(tid)));
    }
    @GetMapping("courses/lab")
    public ResultVO courseLab(@RequestAttribute("uid") String tid){
        return ResultVO.success(Map.of("courses",teacherService.courseList(tid)));
    }
}