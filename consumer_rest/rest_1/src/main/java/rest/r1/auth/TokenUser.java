package rest.r1.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenUser {
    private String hq;
    private String email;
    private List<String> groups;
    private String city;
    private String name;
    @JsonProperty(value = "middlename")
    private String middleName;
    private String surname;
    @JsonProperty(value = "time_token")
    private Date timeToken;
}

