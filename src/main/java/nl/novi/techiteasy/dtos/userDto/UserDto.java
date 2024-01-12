package nl.novi.techiteasy.dtos.userDto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import nl.novi.techiteasy.models.Authority;

import java.util.Set;

@Getter
@Setter
public class UserDto {

    public String username;
    public String password;
    public Boolean enabled;
    public String apikey;
    public String email;
    @JsonSerialize
    public Set<Authority> authorities;
}
