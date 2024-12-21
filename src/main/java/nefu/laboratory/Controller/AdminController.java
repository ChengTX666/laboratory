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



    //实验室增删改查
    //得到所有实验室
    @GetMapping("labs")
    public ResultVO labList(){
        return ResultVO.success(Map.of("labList",adminService.listLab()));
    }

    //根据id改状态
    @PatchMapping("labs/{lid}")//?????????????前端传递的是什么
    public ResultVO updateStatus(@PathVariable("lid") String lid,@RequestBody Laboratory laboratory){
        adminService.updateLabStatus(lid,laboratory.getStatus());
        return ResultVO.ok();
    }
    //删除
    @DeleteMapping("labs/{id}")
    public ResultVO deleteLab(@PathVariable("id") String lid){
        adminService.deleteLab(lid);
        return ResultVO.ok();
    }
    //添加实验室
    @PostMapping("labs")
    public ResultVO addLab(Laboratory laboratory){
        return ResultVO.success(Map.of("lab",adminService.addLab(laboratory)));
    }
    //重置用户密码
    @Operation(summary = "重置用户密码")
    @PatchMapping("reset")
    public ResultVO reset(String account){
        adminService.reset(account);
        return ResultVO.ok();
    }
    //批量导入用户
    @PostMapping("users")
    public ResultVO addUsers(List<User> userList){
        adminService.batchInsertUsers(userList);
        return ResultVO.ok();
    }

    //
}
