package nefu.laboratory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Service.NoticeService;
import nefu.laboratory.dox.Notice;
import nefu.laboratory.dto.ResultVO;
import nefu.laboratory.dto.exception.XException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
@Tag(name = "通知模块")
public class NoticeController {

    private final NoticeService noticeService;


    //###############公告管理
    //分页查询公告
    @Operation(summary = "把第几页传进去,只查询一页数据")
    @GetMapping("notice/page/{page}")
    public ResultVO noticeLimit(@PathVariable int page){
        return ResultVO.success(Map.of(
                "notices",noticeService.noticeLimit((page-1)*8,8),
                "count",noticeService.countNotice()));
    }
    @Operation(summary = "添加公告,并返回到第一页")
    @PostMapping("admin/notice")
    public ResultVO addNotice(@RequestBody Notice notice){
        noticeService.addNotice(notice);
        return  ResultVO.success(Map.of(
                "notices",noticeService.noticeLimit(0,8),
                "count",noticeService.countNotice()));
    }
    @Operation(summary = "修改公告,并返回当前这页新的")
    @PatchMapping("admin/notice/page/{page}")
    public ResultVO updateNotice(@PathVariable int page, @RequestBody Notice notice){
        if(notice.getId()==null){
            throw XException.DATA_ERROR;
        }
        noticeService.updateNotice(notice);
        return ResultVO.success(Map.of(
                "notices",noticeService.noticeLimit((page-1)*8,8),
                "count",noticeService.countNotice()));
    }

    @Operation(summary = "删除公告,返回当前这页的新的")
    @DeleteMapping("admin/notice/page/{page}/{nid}")
    public ResultVO deleteNotice(@PathVariable int page,@PathVariable String nid){
        noticeService.deleteNotice(nid);
        return ResultVO.success(Map.of(
                "notices",noticeService.noticeLimit((page-1)*8,8),
                "count",noticeService.countNotice()));
    }
    @Operation(summary = "批量删除公告,返回当前这页的新的")
    @DeleteMapping("admin/notice/page/{page}/batch")
    public ResultVO deleteNotice(@PathVariable int page,@RequestBody List<String> ids){
        noticeService.batchDeleteNotice(ids);
        return ResultVO.success(Map.of(
                "notices",noticeService.noticeLimit((page-1)*8,8),
                "count",noticeService.countNotice()));
    }

}
