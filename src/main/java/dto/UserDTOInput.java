package dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTOInput {
    private int id;
    private String name;
    private String password;
}
