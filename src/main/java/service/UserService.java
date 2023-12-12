package service;
import dto.UserDTOInput;
import dto.UserDTOOutput;
import lombok.Getter;
import lombok.Setter;
import model.User;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserService {

    private List<User> userList = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    public List<UserDTOOutput> listAllUsers() {
        return userList.stream()
                .map(user -> modelMapper.map(user, UserDTOOutput.class))
                .collect(Collectors.toList());
    }

    public void addUser(UserDTOInput userDTOInput) {
        User user = modelMapper.map(userDTOInput, User.class);
        userList.add(user);
    }

    public void updateUser(UserDTOInput userDTOInput) {
        User updatedUser = modelMapper.map(userDTOInput, User.class);
        userList.stream()
                .filter(user -> user.getId() == updatedUser.getId())
                .findFirst()
                .ifPresent(user -> {
                    user.setName(updatedUser.getName());
                    user.setPassword(updatedUser.getPassword());
                });
    }

    public UserDTOOutput findUserById(int id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .map(user -> modelMapper.map(user, UserDTOOutput.class))
                .orElse(null);
    }

    public void deleteUser(int id) {
        userList.removeIf(user -> user.getId() == id);
    }
}
