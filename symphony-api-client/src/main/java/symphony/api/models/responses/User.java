package symphony.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public Integer id;
    public String username;
    public String firstName;
    public String dateOfBirth;
    public String lastName;
    public Boolean isInfluencer;
    public Boolean hasSelectedInfluencers;
    public String country;
    public String city;
    public String bio;
    public String jobTitle;
    public String numberOfFollowers;
    public String numberOfFollowings;
    public String profileImage;
    public String coverImage;
}
