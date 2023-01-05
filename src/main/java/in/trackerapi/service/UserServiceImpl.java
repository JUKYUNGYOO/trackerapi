package in.trackerapi.service;

import in.trackerapi.entity.User;
import in.trackerapi.entity.UserModel;
import in.trackerapi.exceptions.ItemExistsException;
import in.trackerapi.exceptions.ResourceNotFoundException;
import in.trackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public User createUser(UserModel uModel){
        if(userRepo.existsByEmail(uModel.getEmail())){
            throw new ItemExistsException("User is already register with email" +
                    uModel.getEmail());
        }
        User newUser = new User();
        BeanUtils.copyProperties(uModel, newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepo.save(newUser);

//        BeanUtils.copyProperties(source, target);
//        source : 원본 객체
//        target :  복사 대상 객체
    }
    @Override
    public User readUser(){
        Long userId = getLoggedInUser().getId();
        return userRepo.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User not found for the id:"));

    }

    @Override
    public User updateUser(UserModel user) {
        User existingUser = readUser();
        existingUser.setName(user.getName() != null ? user.getName():existingUser.getName());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail():existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()):existingUser.getPassword());
        existingUser.setAge(user.getAge() != null ? user.getAge():existingUser.getAge());
        return userRepo.save(existingUser);
    }
    @Override
    public void deleteUser(){
        User existingUser = readUser();
        userRepo.delete(existingUser);

    }
    @Override
    public User getLoggedInUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("user not found" +
                "the email" + email));
    }


}
