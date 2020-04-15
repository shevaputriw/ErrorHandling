package id.putraprima.retrofit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Error {
    @SerializedName("email")
    @Expose
    private List<String> email = null;

    @SerializedName("name")
    @Expose
    private List<String> name = null;

    @SerializedName("password")
    @Expose
    private List<String> password = null;

    @SerializedName("confirm")
    @Expose
    private List<String> confirm = null;

    public List<String> getEmail(){
        return email;
    }

    public void setEmail(List<String> email){
        this.email = email;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

    public List<String> getConfirm() {
        return confirm;
    }

    public void setConfirm(List<String> confirm) {
        this.confirm = confirm;
    }
}
