package rs.dzoks.dokumenti.model;

import com.google.gson.annotations.Expose;

public class GetInfo {
    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private String token;
    @Expose
    private String jmbg;

    public GetInfo() {

    }

    public GetInfo(String username, String password, String token, String jmbg) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.jmbg = jmbg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
}
