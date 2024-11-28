package nefu.laboratory.Service;

import lombok.RequiredArgsConstructor;
import nefu.laboratory.Dox.User;
import nefu.laboratory.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public User getUser(String account){
        return userRepository.findByAccount(account);
    }
}
