package model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    @Size(min = 5, max = 45)
    private String firstname;
    @NotEmpty
    @Size(min = 5, max = 45)
    private String lastname ;
    private String phonenumber;
    @Min(18)
    private long age;
    @Email
    private String email;

    public User() {
    }

    public User(String firstname, String lastname, String phonenumber, long age, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.age = age;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
        String phonenumber = user.getPhonenumber();
        ValidationUtils.rejectIfEmpty(errors, "phonenumber", "number.empty");
        if (phonenumber.length()>11 || phonenumber.length()<10){
            errors.rejectValue("phonenumber", "number.length");
        }
        if (!phonenumber.startsWith("0")){
            errors.rejectValue("phonenumber", "number.startsWith");
        }
        if (!phonenumber.matches("(^$|[0-9]*$)")){
            errors.rejectValue("phonenumber", "number.matches");
        }
    }
}
