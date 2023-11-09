import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectsInfo extends JFrame {
    private List<Project> projectList = new ArrayList<>();
    private User user;
    private File usersFile;
    private File projectsFile;
    private JTable table;
    public int selectedRow;

    public ProjectsInfo() {
        super("ProjectInfo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100,100,1000,700);
        this.usersFile = new File("usersInfo.bin");
        this.projectsFile = new File("projectsInfo.bin");
        user = new NotAuthorizedUser();
        redraw();
    }

    public void redraw() {

        this.projectList = Manipulate.readProjects(this.projectsFile);
        //for(Project p:projectList) p.calculateProgrammers();

        JPanel pan = new JPanel();

        JComboBox<String> sortProject = new JComboBox<>(new String[]{"Специальность, убывание","Специальность, возрастание"
        ,"Узкий, убывание","Узкий, возрастание","Количество, убывание","Количество, возрастание", "Ставка, убывание",
                "Ставка, возрастание", "Общие затраты, убывание","Общие затраты, возрастание"});

        pan.add(sortProject);


        JButton toSort = new JButton("Сортировать");
        toSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] args = sortProject.getSelectedItem().toString().split(", ");
                Collections.sort(projectList, new ProjectSorter(args[0],args[1]));
                Manipulate.writeProjects(projectList, projectsFile);
                ProjectsInfo.this.getContentPane().removeAll();
                ProjectsInfo.this.redraw();
            }
        });

        pan.add(toSort);


        SpecialityTableModel specialityTableModel = new SpecialityTableModel(this);
        this.table = new JTable(specialityTableModel);

        JScrollPane scrollPane = new JScrollPane(table);


        this.add(scrollPane, BorderLayout.CENTER);

        JButton showRequire = new JButton("Врачи");
        showRequire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    user.showRequirements(ProjectsInfo.this, ProjectsInfo.this.getProjectList().get(table.getSelectedRow()));
                    selectedRow=(table.getSelectedRow());
                } catch (IndexOutOfBoundsException e1) {
                    JOptionPane.showMessageDialog(ProjectsInfo.this,"Выберите специальность.");
                }
            }
        });
        pan.add(showRequire);
        this.add(pan, BorderLayout.SOUTH);

        if(user instanceof BusinessAnalysts) {
            BusinessAnalysts b = (BusinessAnalysts) user;
            JButton toAddProject = new JButton("Добавить специальность");
            toAddProject.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    b.createProject(ProjectsInfo.this);
                    toAddProject.removeAll();
                }
            });

            JButton toDeleteProject = new JButton("Удалить специальность");
            toDeleteProject.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    b.deleteProject(ProjectsInfo.this);
                    toDeleteProject.removeAll();
                }
            });

            JButton toChangeProject = new JButton("Изменить специальность");
            toChangeProject.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        b.changeProject(ProjectsInfo.this, projectList.get(table.getSelectedRow()), table.getSelectedRow());
                        toChangeProject.removeAll();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(ProjectsInfo.this,"Выберите специальность");
                    }
                }
            });

            pan.add(toDeleteProject);
            pan.add(toAddProject);
            pan.add(toChangeProject);
        }

        if(user instanceof Admin) {
            Admin a = (Admin) user;
            JButton toAddUser = new JButton("Создать пользователя");
            toAddUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    a.createUser(ProjectsInfo.this);
                    toAddUser.removeAll();
                }
            });

            JButton showUsers = new JButton("Показать пользователей");
            showUsers.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    a.showUsers(ProjectsInfo.this);
                    showUsers.removeAll();
                }
            });
            pan.add(showUsers);
            pan.add(toAddUser);
        }

        JPanel forAuthorization = new JPanel();
        forAuthorization.setBackground(Color.white);

        JButton signInOut = new JButton();
        if(user instanceof NotAuthorizedUser) {
            signInOut = new JButton("Sign In");
            signInOut.setBackground(Color.white);
            signInOut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((NotAuthorizedUser)user).LogIn(ProjectsInfo.this);
                }
            });
        }
        else {
            signInOut = new JButton("Sign Out");
            signInOut.setBackground(Color.white);
            signInOut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((AuthorizedUser)user).LogOff(ProjectsInfo.this);
                }
            });
        }
        forAuthorization.add(signInOut);
        this.add(forAuthorization, BorderLayout.NORTH);

        setVisible(true);
    }


    public User getUser() {
        return user;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public File getUsersFile() {
        return usersFile;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public File getProjectsFile() {
        return projectsFile;
    }

    public JTable getTable() {
        return table;
    }
}
