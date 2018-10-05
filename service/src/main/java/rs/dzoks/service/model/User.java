package rs.dzoks.service.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class User {
    private Integer id;
    private String username;
    private String password;
    private String jmbg;
    private String fname;
    private String lname;
    private Byte male;
    private Date birthDate;
    private Byte administrator;
    private String token;
    private Timestamp tokenExpirationTime;
    private Byte loggedIn;
    private String placeOfBirth;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 256)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "jmbg", nullable = false, length = 13)
    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @Basic
    @Column(name = "fname", nullable = false, length = 45)
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Basic
    @Column(name = "lname", nullable = false, length = 45)
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Basic
    @Column(name = "male", nullable = false)
    public Byte getMale() {
        return male;
    }

    public void setMale(Byte male) {
        this.male = male;
    }

    @Basic
    @Column(name = "birth_date", nullable = false)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "administrator", nullable = false)
    public Byte getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Byte administrator) {
        this.administrator = administrator;
    }

    @Basic
    @Column(name = "token", nullable = true, length = 6)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "token_expiration_time", nullable = true)
    public Timestamp getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(Timestamp tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    @Basic
    @Column(name = "logged_in", nullable = false)
    public Byte getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Byte loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Basic
    @Column(name = "place_of_birth", nullable = false, length = 45)
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(jmbg, user.jmbg) &&
                Objects.equals(fname, user.fname) &&
                Objects.equals(lname, user.lname) &&
                Objects.equals(male, user.male) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(administrator, user.administrator) &&
                Objects.equals(token, user.token) &&
                Objects.equals(tokenExpirationTime, user.tokenExpirationTime) &&
                Objects.equals(loggedIn, user.loggedIn) &&
                Objects.equals(placeOfBirth, user.placeOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, jmbg, fname, lname, male, birthDate, administrator, token, tokenExpirationTime, loggedIn, placeOfBirth);
    }
}
