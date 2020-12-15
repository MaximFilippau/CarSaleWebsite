package com.filippau.tc.services;

import com.filippau.tc.domain.Role;
import com.filippau.tc.domain.User;
import com.filippau.tc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService  implements UserDetailsService {
    public final PasswordEncoder passwordEncoder;
    public final UserRepository userRepository;
    public final MailSenderService mailSenderService;

    public void addUser(User user) {
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(false);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        sendMessage(user);

    }
    private void sendMessage(User user){
        if(!StringUtils.isEmpty(user.getEmail())){

            String message = String.format(
                    "Hello, %s! \n" +
                            "Thank's for choosing our transport company \"Maxim\"" +
                            "\n" +
                            "Follow the link to confirm your registration" +
                            "\n" +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSenderService.send(user.getEmail(), "Activation code", message);
        }
    }



    public boolean activateUser(String code){
        User userByCode = userRepository.findByActivationCode(code);

        if (userByCode == null){
            return false;
        } else {

            userByCode.setActivationCode(null);
            userByCode.setActive(true);
            userRepository.save(userByCode);

            return true;
        }
    }

    public List<User> users(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }


    public void saveUser(User user, String username, Map<String, String> form){

        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for(String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
    }


    public void deleteUser(@PathVariable(value = "id") long id){
        User user = userRepository.findById(id);
        userRepository.delete(user);
    }
}
