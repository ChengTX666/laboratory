package nefu.laboratory.Controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nefu.laboratory.Service.TeacherService;
import nefu.laboratory.dox.Course;
import nefu.laboratory.dox.Reservation;
import nefu.laboratory.dto.IdsDTO;
import nefu.laboratory.dto.ResultVO;
import nefu.laboratory.dto.WeeksDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/teacher/")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    //登录进去拿预约记录形成日程表
    @Operation(summary = "拿老师所有预约记录形成日程表")
    @GetMapping("reservations")
    public ResultVO courseList(@RequestAttribute("uid") String tid){

        return ResultVO.success(teacherService.reservationTid(tid));

    }
    //快速查询
    @Operation(summary = "快速查询")
    @GetMapping("reservation/fast/{week}/{day}")
    public ResultVO findFree(@PathVariable int week, @PathVariable int day){
        return ResultVO.success(teacherService.findFree(week,day));
    }
    //添加新预约
    @Operation(summary = "添加预约记录")
    @PostMapping("reservations")
    public ResultVO addReservations(@RequestBody WeeksDTO weeksDTO,
                                    @RequestAttribute("uid") String tid,@RequestAttribute String name){
        weeksDTO.setTeacherId(null);
        weeksDTO.setTeacherId(tid);
        weeksDTO.setTeacherName(name);
        teacherService.addReservations(weeksDTO);
        return ResultVO.success(teacherService.reservationTid(tid),"已成功添加");
    }

    @Operation(summary = "取消预约(判断是否是自己的预约记录),返回新的预约记录列表")
    //删除预约记录
    @DeleteMapping("reservations/{rid}")//
    public ResultVO delReservation(@PathVariable String rid,@RequestAttribute("uid") String tid){
         teacherService.delReservation(rid, tid);
         return ResultVO.success(teacherService.reservationTid(tid),"已成功删除");
    }
    @Operation(summary = "批量取消预约(判断是否是自己的预约记录),返回新的预约记录列表List<Reservation>")
    //删除预约记录
    @DeleteMapping("reservations/list")//
    public ResultVO delReservationList(@RequestAttribute("uid") String tid,
                                       @RequestBody List<Reservation> reservations){

        teacherService.delReservations(reservations,tid);
        return ResultVO.success(teacherService.reservationTid(tid),"已成功删除");
    }
    @Operation(summary = "批量取消预约(判断是否是自己的预约记录),返回新的预约记录列表Reservation[]")
    //删除预约记录
    @DeleteMapping("reservations")//
    public ResultVO delReservationList(@RequestAttribute("uid") String tid,
                                       @RequestBody Reservation[] reservations){
        List<Reservation> reservationList= Arrays.stream(reservations).toList();
        teacherService.delReservations(reservationList,tid);
        return ResultVO.success(teacherService.reservationTid(tid),"已成功删除");
    }


//    @Operation(summary = "批量取消预约(判断是否是自己的预约记录),返回新的预约记录列表 字符串拼接")
//    //删除预约记录
//    @DeleteMapping("reservations")//
//    public ResultVO delReservations(@RequestAttribute("uid") String tid, IdsDTO idsDTO){
//        String ids=idsDTO.getIds();
//        List<String> list = Arrays.stream(ids.split(",")).toList();
//        teacherService.delReservations(list,tid);
//        return ResultVO.success(teacherService.reservationTid(tid),"已成功删除");
//    }

    //###############       课程

    //没有更新预约,需要通过删除后再预约
    @Operation(summary = "所有课程")
    @GetMapping("courses")
    public ResultVO courseLab(@RequestAttribute("uid") String tid){

        return ResultVO.success(teacherService.courseLab(tid));
    }
    //添加课程
    @Operation(summary = "添加课程,返回新的课程列表")
    @PostMapping("courses")
    public ResultVO addCourse(@RequestBody Course course,
                              @RequestAttribute("uid")String tid,@RequestAttribute String name){
        course.setId(null);
        course.setTeacherId(tid);
        course.setTeacherName(name);
        course.setType(Course.LAB);
        teacherService.addCourse(course);
        return ResultVO.success(teacherService.courseLab(tid));
    }
    //删除课程
    @Operation(summary = "删除课程,返回新的课程列表")
    @DeleteMapping("courses/{cid}")
    public ResultVO delCourse(@PathVariable String cid,@RequestAttribute("uid") String tid){
        teacherService.delCourse(cid,tid);
        return ResultVO.success(teacherService.courseLab(tid));
    }

    @Operation(summary = "修改课程,返回新的课程列表")
    @PatchMapping("courses")
    public ResultVO updateCourse(@RequestBody Course course,
                                 @RequestAttribute("uid")String tid,@RequestAttribute String name){

        teacherService.updateCourse(course,tid,name);
        return ResultVO.success(teacherService.courseLab(tid));
    }


    //跳转到选择实验室阶段
    @Operation(summary = "查看所有实验室")
    @GetMapping("labs")
    public ResultVO labList(){
        return ResultVO.success(teacherService.labAndCount());
    }


    @Operation(summary = "点击某个实验室,拿到该实验室的预约记录")
    //点进去实验室,查看具体4×7格的时间表 取出预约记录
    @GetMapping("labs/{lid}")
    public ResultVO lab(@PathVariable("lid") String lid){
        return ResultVO.success(teacherService.reservationLab(lid));
    }


}