package cosac.model;

public class UserData {

    private String studentID;
    private String firstname;
    private String lastname;
    private String email;
    private boolean locked;

    public UserData(String studentID, String firstname, String lastname, String email, boolean locked) {
        this.studentID = studentID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
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
        this.email = email;
    }

    public boolean getLock() {
        return locked;
    }

    public void setLock(boolean locked) {
        this.locked = locked;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        if(studentID.toLowerCase().contains("s"))
            this.studentID = studentID;
        else
            System.out.println("no valid studentID");
    }

    @Override
    public String toString() {
        return "UserData{" +
                "studentID='" + studentID + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", locked=" + locked +
                '}';
    }
}
