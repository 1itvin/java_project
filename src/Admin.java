import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Admin extends AuthorizedUser{
    public Admin(String Login, String Password) {
        super(Login, Password, "admin");
    }

    private JTable table;
    public void createUser(ProjectsInfo p) {
        JFrame frame = new JFrame("Добавить пользователя");
        frame.setBounds(p.getWidth()/4,p.getHeight()/4,250,p.getHeight()/2);
        frame.setLayout(null);

        JLabel setLogin = new JLabel("Set login");
        setLogin.setBounds(10,20,100,20);
        frame.add(setLogin);

        JTextField userLogin = new JTextField();
        userLogin.setBounds(10,40,200,30);
        frame.add(userLogin);

        JLabel setPass = new JLabel("Set password");
        setPass.setBounds(10,70,100,20);
        frame.add(setPass);

        JTextField userPassword = new JTextField();
        userPassword.setBounds(10,90,200,30);
        frame.add(userPassword);

        JLabel role = new JLabel("Choose role");
        role.setBounds(10,130,100,20);
        frame.add(role);

        JComboBox<String> roles = new JComboBox<>(new String[]{"user","analysts","admin"});
        roles.setBounds(10,150,120,30);
        frame.add(roles);

        JButton addUser = new JButton("Add user");
        addUser.setBounds(10,210,200,30);
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<AuthorizedUser> users = Manipulate.readUsers(p.getUsersFile());
                switch ((String) roles.getSelectedItem()) {
                    case "user": users.add(new AuthorizedUser(userLogin.getText(),userPassword.getText(),"user")); break;
                    case "analysts": users.add(new BusinessAnalysts(userLogin.getText(),userPassword.getText())); break;
                    case "admin": users.add(new Admin(userLogin.getText(),userPassword.getText())); break;
                }
                Manipulate.writeUsers(users, p.getUsersFile());
                frame.dispose();
                JOptionPane.showMessageDialog(p,"Пользователь добавлен.");
            }
        });

        frame.add(addUser);

        frame.setVisible(true);
    }

    public void showUsers(ProjectsInfo p) {
        JFrame frame = new JFrame("Пользователи");
        frame.setBounds(100,100,400,300);

        UsersTableModel projectTableModel = new UsersTableModel(p.getUsersFile());
        table = new JTable(projectTableModel);

        JScrollPane scrollPane = new JScrollPane(table);


        frame.add(scrollPane, BorderLayout.CENTER);

        JButton deleteUser = new JButton("Удалить пользователя");
        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser(p,frame);
            }
        });
        frame.add(deleteUser, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void deleteUser(ProjectsInfo p, JFrame f) {
        List users =  Manipulate.readUsers(p.getUsersFile());
        try {
            users.remove(table.getSelectedRow());
            JOptionPane.showMessageDialog(p,"Пользователь удалён.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(f,"Выберите пользователя.");
        } finally {
            Manipulate.writeUsers(users,p.getUsersFile());
            f.dispose();
        }
    }
}
