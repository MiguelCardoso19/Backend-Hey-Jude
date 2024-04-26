package io.codeforall.hackaton.command;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;


public class ParentDto {
        private Integer id;

        @NotNull(message = "Name is mandatory")
        @NotBlank(message = "Name is mandatory")
        @Size(min = 3, max = 64)
        private String name;

    @NotNull(message = "It's fine")
    @Min(value = 0, message = "Please enter a valid number")
    @Max(value = 64, message = "Damn")
    private Integer age;

        @Email
        @NotBlank(message = "Email is mandatory")
        private String email;

        @NotBlank
        @Size(min = 9, max = 16)
        private String password;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
        public String toString() {
            return "ParentForm{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\''
                    ;
        }
    }