import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class NotAuthorizedUser extends User{
    public void LogIn(ProjectsInfo p) {
        JFrame frame = new JFrame("SIGN IN");
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.white);
        frame.setBounds(p.getWidth()/4,p.getHeight()/4,
                300,300);

        JLabel enterLogin = new JLabel("Enter Login");
        enterLogin.setBounds(30,10,100,20);
        frame.add(enterLogin);

        JTextField login = new JTextField();
        login.setBounds(30,40,200,40);
        frame.add(login);

        JLabel enterPassword = new JLabel("Enter Password");
        enterPassword.setBounds(30,100,100,20);
        frame.add(enterPassword);

        JTextField password = new JTextField();
        password.setBounds(30,130,200,40);
        frame.add(password);

        JButton toSign = new JButton("Sign In");
        toSign.setBackground(Color.white);
        toSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<AuthorizedUser> users = Manipulate.readUsers(p.getUsersFile());
                for(AuthorizedUser user:users) {
                    if (user.getLogin().equals(login.getText()) && user.getPassword().equals(password.getText())) {
                        switch(user.getRole()) {
                            case "analysts": p.setUser(new BusinessAnalysts(login.getText(),password.getText())); break;
                            case "admin": p.setUser(new Admin(login.getText(),password.getText())); break;
                            default: p.setUser(new AuthorizedUser(login.getText(),password.getText(),"user"));
                        }
                        frame.dispose();
                        p.getContentPane().removeAll();
                        p.redraw();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame,"Неверный логин и/или пароль.");
            }
        });
        toSign.setBounds(90,190,80,30);
        frame.add(toSign);

        frame.setVisible(true);
    }
}
