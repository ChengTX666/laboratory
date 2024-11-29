package nefu.laboratory.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/laboratory/admin/")
public class AdminController {
    private final AdminService adminService;
}
