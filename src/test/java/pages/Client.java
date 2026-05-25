package pages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {
    public String profile;
    public String email;
    public String password;
    public String confirmPassword;
    public String civility;
    public String firstName;
    public String lastName;
    public String countryOfResidence;
    public String countryOfResidenceValue;
    public String nationality;
    public String postCode;
    public String city;
    public String phone;
    public String function;
    public String organizationType;
    public String organizationTypeValue;
    public String organizationName;
    public String studyField;
    public String studyFieldValue;
    public String studyLevel;
    public String studyLevelValue;
}