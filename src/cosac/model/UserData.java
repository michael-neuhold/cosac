package cosac.model;

import java.io.Serializable;
import java.util.Objects;

public class UserData implements Serializable {

    private String studentID;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    private boolean locked;

    public UserData(String studentID, String firstname,
                    String lastname, String email,
                    String password,
                    Role role, boolean locked)
    {
        this.studentID = studentID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.locked = locked;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(isValidEmail(email)) this.email = email;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        if(isValidId(studentID)) this.studentID = studentID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean isValidId(String id) {
        return id.matches("S[0-9]{10}|P[0-9]{4}");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("[A-z0-9]*@students.fh-hagenberg.at");
    }

    public boolean hasID(String id) {
        return studentID.equals(id);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "studentID='" + studentID + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password#='" + password +'\'' +
                ", role=" + role +
                ", locked=" + locked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(studentID, userData.studentID);
    }

}
