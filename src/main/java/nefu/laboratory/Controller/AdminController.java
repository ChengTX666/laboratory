package nefu.laboratory.Controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nefu.laboratory.Service.AdminService;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.dox.User;
import nefu.laboratory.dto.ResultVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    private final AdminService adminService;
//    eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxIiwicm9sZSI6ImFiYzEiLCJuYW1lIjoi546L5rOiIiwiZXhwIjoxNzM3NTkzMTQ0LCJpYXQiOjE3MzUwMDExODB9.dn8zJ9wXKoud_UOgSuUsPh71z6iZmJS_Ogxqm3LU8A8

    //所有用户
    @Operation(summary = "得到所有用户(包括admin)")
    @GetMapping("users")
    public ResultVO userList(){
        return ResultVO.success(adminService.userList());
    }
    @Operation(summary = "得到所有老师")
    @GetMapping("users/teachers")
    public ResultVO teacherList(){
        return ResultVO.success(adminService.teacherList());
    }
    @Operation(summary = "添加用户,返回所有用户")
    @PostMapping("users")
    public ResultVO addUser(@RequestBody User user){
        user.setId(null);
        adminService.addUser(user);
        return ResultVO.success(adminService.userList());
    }

    @Operation(summary = "添加老师,返回所有老师")
    @PostMapping("users/teachers")
    public ResultVO addTeacher(@RequestBody User user){
        user.setId(null);
        adminService.addTeacher(user);
        return ResultVO.success(adminService.teacherList());
    }

    //批量导入用户
    @Operation(summary = "批处理添加用户list")
    @PostMapping("users/batch")
    public ResultVO addUsers(@RequestBody List<User> userList){
        adminService.batchInsertUsers(userList);
        return ResultVO.success(adminService.userList());
    }
    //删除老师
    @Operation(summary = "删除老师,并返回新的老师列表")
    @DeleteMapping("users/teacher/{id}")
    public ResultVO delUser(@PathVariable String id){
        adminService.delTeacher(id);
        return ResultVO.success(adminService.teacherList());
    }





    //实验室增删改查
    //得到所有实验室
    @Operation(summary = "所有实验室")
    @GetMapping("labs")
    public ResultVO labList(){
        return ResultVO.success(adminService.listLab());
    }

    //修改实验室
    @PatchMapping("labs")
    public ResultVO updateLaboratory(@RequestBody Laboratory laboratory){
        adminService.updateLab(laboratory);
        return ResultVO.success(adminService.listLab(),"已修改成功");
    }

    //删除
    @DeleteMapping("labs/{id}")
    public ResultVO deleteLab(@PathVariable("id") String lid){
        adminService.deleteLab(lid);
        return ResultVO.success(adminService.listLab());
    }
    //添加实验室
    @Operation(summary = "添加实验室,并返回新的实验室列表")
    @PostMapping("labs")
    public ResultVO addLab(@RequestBody Laboratory laboratory){
        adminService.addLab(laboratory);
        return ResultVO.success(adminService.listLab());
    }


    //重置用户密码
    @Operation(summary = "重置用户密码")
    @PatchMapping("reset")
    public ResultVO reset(@RequestBody String account){
        adminService.reset(account);
        return ResultVO.ok();
    }




    //
}
