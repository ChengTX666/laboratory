package nefu.laboratory.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Service.AdminService;
import nefu.laboratory.dto.ResultVO;
import nefu.laboratory.dto.StatusDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/laboratory/admin/")
public class AdminController {
    private final AdminService adminService;

    //实验室增删改查
    @GetMapping("labs")
    public ResultVO labList(){
        return ResultVO.success(Map.of("labList",adminService.listLab()));
    }

    //根据id改状态
    @PatchMapping("labs/{id}")
    public ResultVO updateStatus(@PathVariable("id") String lid,@RequestBody StatusDTO statusDTO){
        adminService.updateLabStatus(lid,statusDTO.getStatus());
        return ResultVO.ok();
    }
    //删除
    @DeleteMapping("labs/{id}")
    public ResultVO deleteLab(@PathVariable("id") String lid){
        adminService.deleteLab(lid);
        return ResultVO.ok();
    }


    //创建用户,重置用户密码
}
