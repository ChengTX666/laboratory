package nefu.laboratory.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import nefu.laboratory.Service.AdminService;
import nefu.laboratory.Service.NoticeService;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.dox.Notice;
import nefu.laboratory.dox.User;
import nefu.laboratory.dto.ResultVO;
import nefu.laboratory.dto.exception.XException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/")
@Tag(name = "管理员模块")
public class AdminController {
    private final AdminService adminService;
    private final NoticeService noticeService;
//    eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxIiwicm9sZSI6ImFiYzEiLCJuYW1lIjoi546L5rOiIiwiZXhwIjoxNzM3NTkzMTQ0LCJpYXQiOjE3MzUwMDExODB9.dn8zJ9wXKoud_UOgSuUsPh71z6iZmJS_Ogxqm3LU8A8

    @Operation(summary = "根据id查用户")
    @GetMapping("search/id/{id}")
    public ResultVO findById(@PathVariable String id){
        return ResultVO.success(adminService.findById(id));
    }
    @Operation(summary = "根据账号查询用户")
    @GetMapping("search/account/{account}")
    public ResultVO searchByAccount(@PathVariable String account){
        return ResultVO.success(adminService.findByAccount(account));
    }
    @Operation(summary = "根据名字查询用户")
    @GetMapping("search/name/{name}")
    public ResultVO searchByName(@PathVariable String name){
        return ResultVO.success(adminService.findByName(name));
    }
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
    @Operation(summary = "更新老师信息,返回新的老师列表")
    @PatchMapping("users/teachers")
    public ResultVO updateTeacher(@RequestBody User user){
        adminService.updateTeacher(user);
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
    @Operation(summary = "删除老师,并返回新的老师列表(存在预约记录禁止删除)")
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

    //###############公告管理
//
//    //分页查询公告
//    @Operation(summary = "把第几页传进去,只查询一页数据")
//    @GetMapping("notices/page/{page}")
//    public ResultVO noticeLimit(@PathVariable int page){
//        return ResultVO.success(Map.of(
//                "notices",noticeService.noticeLimit((page-1)*8,8),
//                "count",noticeService.countNotice()));
//    }
//    @Operation(summary = "添加公告,并返回到第一页")
//    @PostMapping("notices")
//    public ResultVO addNotice(@RequestBody Notice notice){
//        noticeService.addNotice(notice);
//        return  ResultVO.success(Map.of(
//                "notices",noticeService.noticeLimit(0,8),
//                "count",noticeService.countNotice()));
//    }
//    @Operation(summary = "修改公告,并返回当前这页新的")
//    @PatchMapping("notices/page/{page}")
//    public ResultVO updateNotice(@PathVariable int page, @RequestBody Notice notice){
//        if(notice.getId()==null){
//            throw XException.DATA_ERROR;
//        }
//        noticeService.updateNotice(notice);
//        return ResultVO.success(Map.of(
//                "notices",noticeService.noticeLimit((page-1)*8,8),
//                "count",noticeService.countNotice()));
//    }
//
//    @Operation(summary = "删除公告,返回当前这页的新的")
//    @DeleteMapping("notices/page/{page}/{nid}")
//    public ResultVO deleteNotice(@PathVariable int page,@PathVariable String nid){
//        noticeService.deleteNotice(nid);
//        return ResultVO.success(Map.of(
//                "notices",noticeService.noticeLimit((page-1)*8,8),
//                "count",noticeService.countNotice()));
//    }
//    @Operation(summary = "批量删除公告,返回当前这页的新的")
//    @DeleteMapping("notices/page/{page}/batch")
//    public ResultVO deleteNotice(@PathVariable int page,@RequestBody List<String> ids){
//        noticeService.batchDeleteNotice(ids);
//        return ResultVO.success(Map.of(
//                "notices",noticeService.noticeLimit((page-1)*8,8),
//                "count",noticeService.countNotice()));
//    }
    //
    @PostConstruct
    public void init() {
        adminService.preAll();
    }
}
