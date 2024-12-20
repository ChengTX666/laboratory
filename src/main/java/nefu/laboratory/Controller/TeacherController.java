package nefu.laboratory.Controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Service.TeacherService;
import nefu.laboratory.dto.ResultVO;
import nefu.laboratory.dto.WeeksDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/teacher/")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    //登录进去拿预约记录形成日程表
    @Operation(summary = "拿老师所有预约记录形成日程表")
    @GetMapping("reservations")
    public ResultVO courseList(String tid){
        //实验课程(已经预约过的,从预约表里拿)
        return ResultVO.success(Map.of(
                "reservations",teacherService.reservationTid(tid)
                ));
    }
    //快速查询
    @Operation(summary = "快速查询")
    @GetMapping("reservation/fast")
    public ResultVO findFree(int week, int day){
        return ResultVO.success(Map.of("free",teacherService.findFree(week,day)));
    }
    //添加新预约
    @Operation(summary = "添加预约记录")
    @PostMapping("reservations")
    public ResultVO addReservations(@RequestBody WeeksDTO weeksDTO){
        teacherService.addReservations(weeksDTO);
        return ResultVO.success(Map.of("weeks",weeksDTO));
    }

    @Operation(summary = "取消预约(判断是否是自己的预约记录)")
    //删除预约记录
    @DeleteMapping("reservations/{rid}")
    public ResultVO delReservation(@PathVariable String rid,@RequestAttribute("uid") String tid){
         teacherService.delReservation(rid, tid);
         return ResultVO.ok();
    }
    //没有更新预约,需要通过删除后再预约
    @Operation(summary = "所有课程")
    @GetMapping("courses")
    public ResultVO courseLab(@RequestAttribute("uid") String tid){

        return ResultVO.success(Map.of("courseLab",teacherService.courseLab(tid)));
    }
    //跳转到选择实验室阶段
    @Operation(summary = "查看所有实验室")
    @GetMapping("labs")
    public ResultVO labList(){
        return ResultVO.success(Map.of(
                "labList",teacherService.labList(),
                "countMap",teacherService.countMap()));
    }
    @Operation(summary = "点击某个实验室,拿到该实验室的预约记录")
    //点进去实验室,查看具体4×7格的时间表 取出预约记录
    @GetMapping("labs/{lid}")
    public ResultVO lab(@PathVariable("lid") String lid){
        return ResultVO.success(Map.of(
                "reservationLab",teacherService.reservationLab(lid)
        ));
    }


}