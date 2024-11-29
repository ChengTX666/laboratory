package nefu.laboratory.Service;


import lombok.RequiredArgsConstructor;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final LaboratoryRepository laboratoryRepository;
}
