public class AuthorizedUser extends User {
    private String Login;
    private String Password;
    private String Role;

    public AuthorizedUser(String Login, String Password, String Role) {
        this.Login = Login;
        this.Password = Password;
        this.Role = Role;
    }

    public void LogOff(ProjectsInfo p) {
        p.setUser(new NotAuthorizedUser());
        p.getContentPane().removeAll();
        p.redraw();
    }

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return Password;
    }

    public String getRole() {
        return Role;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setRole(String role) {
        Role = role;
    }
}
