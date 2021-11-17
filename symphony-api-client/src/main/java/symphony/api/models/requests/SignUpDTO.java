package symphony.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public String username;
    public String dateOfBirth;
}
