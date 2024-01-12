package nl.novi.techiteasy.service;

import nl.novi.techiteasy.dtos.userDto.UserDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.User;
import nl.novi.techiteasy.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new RecordNotFoundException("no user find with id " + username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        User newUser = /*TODO repo*/.save(toUser(userDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        /*repo*/.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new /*TODO exception*/();
        User user = /*TODO repo*/.findById(username).get();
        user.setPassword(newUser.getPassword());
        /*TODO repo*/.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!/*TODO repo*/.existsById(username)) throw new /*TODO exception*/(username);
        User user = /*TODO repo*/.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!/*TODO repo*/.existsById(username)) throw new /*TODO exception*/(username);
        User user = /*TODO repo*/.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        /*TODO repo*/.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!/*TODO repo*/.existsById(username)) throw new /*TODO exception*/(username);
        User user = /*TODO repo*/.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        /*TODO repo*/.save(user);
    }

    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(/*TODO encrypted password*/);
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }


}
