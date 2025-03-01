package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepositay;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepositay userRepositay;

    private static final PasswordEncoder passwordEncodder = new BCryptPasswordEncoder();


    public void saveAdmin(User user){
        user.setPassword(passwordEncodder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepositay.save(user);
    }

    public boolean saveNewUser(User user){
        try {
            user.setPassword(passwordEncodder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepositay.save(user);
            return true;
        } catch (Exception e){
            log.info("hahahhahahaha");
            return false;
        }
    }


    public void saveUser(User user){
        userRepositay.save(user);
    }


    public List<User> getAll(){
        return userRepositay.findAll();
    }

    public Optional<User> findById(ObjectId Id) {
        return userRepositay.findById(Id);
    }

    public void deleteById(ObjectId Id){
        userRepositay.deleteById(Id);
    }

    public User findByUsername(String userName){
        return userRepositay.findByUserName(userName);
    }
}
