package nefu.laboratory.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Repository.NoticeRepository;
import nefu.laboratory.dox.Notice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    //公告管理

    //分页查询
    public List<Notice> noticeLimit(int offset,int limit){
        return noticeRepository.findLimit(offset, limit);
    }
    //添加公告
    public void addNotice(Notice notice){
        notice.setId(null);
        notice.setCreateTime(null);
        notice.setUpdateTime(null);
        noticeRepository.save(notice);
    }
    //修改公告
    @Transactional
    public void updateNotice(Notice notice){
        noticeRepository.save(notice);
    }

    //删除公告
    @Transactional
    public void deleteNotice(String nid){
        noticeRepository.deleteById(nid);
    }

    @Transactional
    public void batchDeleteNotice(List<String> ids){
        ids.forEach(noticeRepository::deleteById);
    }
    //获取消息总数量
    @Transactional
    public long countNotice(){
        return noticeRepository.count();
    }

}


