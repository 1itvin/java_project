import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BusinessAnalysts extends AuthorizedUser{
    public BusinessAnalysts(String Login, String Password) {
        super(Login, Password, "analysts");
    }

    public void createProject(ProjectsInfo p) {
        JFrame addProjectFrame = new JFrame("Add speciality");
        addProjectFrame.setLayout(null);
        addProjectFrame.setBounds(250,250,200,370);

        JLabel setName = new JLabel("Введите название");
        setName.setBounds(20,20,200,20);
        addProjectFrame.add(setName);

        JTextField name = new JTextField();
        name.setBounds(20,40,150,30);
        addProjectFrame.add(name);

        JLabel setNarrow = new JLabel("узкий/нет");
        setNarrow.setBounds(20,70,100,20);
        addProjectFrame.add(setNarrow);


        JComboBox<String> Narrow = new JComboBox<>(new String[]{"Нет","Да"});
        Narrow.setBounds(20,90,150,30);
        addProjectFrame.add(Narrow);

        JLabel setNumber = new JLabel("Количетво врачей");
        setNumber.setBounds(20,120,100,20);
        addProjectFrame.add(setNumber);

        JTextField number = new JTextField();
        number.setBounds(20,140,150,30);
        addProjectFrame.add(number);


        JLabel setStavka = new JLabel("Ставка зп");
        setStavka.setBounds(20,170,100,20);
        addProjectFrame.add(setStavka);

        JTextField stavka = new JTextField();
        stavka.setBounds(20,190,150,30);
        addProjectFrame.add(stavka);

        JButton toAdd = new JButton("Добавить");
        toAdd.setBounds(30,285,120,30);
        toAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Project> projectList = Manipulate.readProjects(p.getProjectsFile());
                projectList.add(new Project(name.getText(), Narrow.getSelectedItem().toString(),
                       number.getText(), stavka.getText(), new ArrayList<Doctor>()));
                for (Project project : projectList) project.calculateProgrammers();
                Manipulate.writeProjects(projectList,p.getProjectsFile());
                addProjectFrame.dispose();
                p.getContentPane().removeAll();
                p.redraw();
            }
        });

        addProjectFrame.add(toAdd);

        addProjectFrame.setVisible(true);
    }

    public void deleteProject(ProjectsInfo p) {
        try {
            p.getProjectList().remove(p.getTable().getSelectedRow());
            Manipulate.writeProjects(p.getProjectList(),p.getProjectsFile());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(p,"Выберите специальнсть.");
        }finally {
            p.redraw();
        }
    }

    public void createRequirement(ProjectsInfo p, JFrame j) {
        JFrame addReq = new JFrame("Добавьте врача");
        addReq.setLayout(null);
        addReq.setBounds(100,100,200,300);


        JTextField fio = new JTextField("fio");
        fio.setBounds(20,20,150,30);
        addReq.add(fio);

        JTextField birthDate = new JTextField("ДР yyyy.MM.dd");//dr
        birthDate.setBounds(20,70,150,30);
        addReq.add(birthDate);

        JTextField empDate = new JTextField("Устроился yyyy.MM.dd");//den` priema
        empDate.setBounds(20,120,150,30);
        addReq.add(empDate);

        JTextField number = new JTextField("№ участка");
        number.setBounds(20,170,150,30);
        addReq.add(number);

        JButton toAdd = new JButton("Добавить");
        toAdd.setBounds(20,220,150,30);
        toAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Project> proj = p.getProjectList();
                    proj.get(p.getTable().getSelectedRow()).getRequirements().add(new Doctor(
                            fio.getText(),
                            birthDate.getText(), empDate.getText() , number.getText()));
                    for (Project project : proj) project.calculateProgrammers();
                    Manipulate.writeProjects(proj, p.getProjectsFile());
                    p.getContentPane().removeAll();
                    p.redraw();
                    addReq.dispose();
                    j.dispose();
                    JOptionPane.showMessageDialog(j, "Врач добавлен.");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(addReq, "Ошибка данных.");
                }
            }
        });
        addReq.add(toAdd);

        addReq.setVisible(true);
    }

    public void deleteRequirement(ProjectsInfo p,JTable table, Project project) {
        List<Project> proj = p.getProjectList();
        try {
            project.getRequirements().remove((table.getSelectedRow()));
            project.calculateProgrammers();
            //proj.get(p.getTable().getSelectedRow()).calculateProgrammers();
            Manipulate.writeProjects(proj,p.getProjectsFile());
            p.getContentPane().removeAll();
            p.redraw();
            JOptionPane.showMessageDialog(p,"Врач удален.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Выберите врача.");
        }
    }

    public void changeRequirement(ProjectsInfo p, JTable table, JFrame f, Project project) {
        try {

            Doctor currentReq = project.getRequirements().get(table.getSelectedRow());
            JFrame addReq = new JFrame("Измените врача");
            addReq.setVisible(true);
            addReq.setLayout(null);
            addReq.setBounds(100, 100, 200, 300);

            JTextField disc = new JTextField(currentReq.getFio());
            disc.setBounds(20, 20, 150, 30);
            addReq.add(disc);

            JTextField endDate = new JTextField(String.valueOf(currentReq.getDate_of_employment()));//den` priema
            endDate.setBounds(20,100,150,30);
            addReq.add(endDate);

            JTextField day_of_birth = new JTextField(String.valueOf(currentReq.getDay_of_birth()));//dr
            day_of_birth.setBounds(20, 60, 150, 30);
            addReq.add(day_of_birth);

            JTextField isDone = new JTextField(currentReq.getNumber_of_plot());//number
            isDone.setBounds(20, 140, 150, 30);
            addReq.add(isDone);


            JButton toChange = new JButton("Изменить");
            addReq.add(toChange);
            toChange.setBounds(20,220,150,30);
            toChange.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        project.getRequirements().add(new Doctor(disc.getText(),
                                //Integer.parseInt(day_of_birth.getText()), /* priority.getSelectedItem().toString()*/
                                day_of_birth.getText(),endDate.getText(), isDone.getText()));
                        project.getRequirements().remove(table.getSelectedRow());
                        p.getProjectList().get(p.getTable().getSelectedRow()).calculateProgrammers();
                        Manipulate.writeProjects(p.getProjectList(), p.getProjectsFile());
                        p.getContentPane().removeAll();
                        p.redraw();
                        addReq.dispose();
                        JOptionPane.showMessageDialog(p, "Врач изменен.");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(f, "Выберите врача.");
                    }
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(p,"Выберите специальность");
        }
    }

    public void changeProject(ProjectsInfo p, Project currentProject, int index) {
        JFrame addProjectFrame = new JFrame("Изменить специальность");
        addProjectFrame.setLayout(null);
        addProjectFrame.setBounds(250,250,200,370);

        JTextField comp = new JTextField(currentProject.getName());
        comp.setBounds(20,20,150,30);
        addProjectFrame.add(comp);

        JTextField project = new JTextField(currentProject.getNarrow());
        project.setBounds(20,60,150,30);
        addProjectFrame.add(project);

        JTextField number = new JTextField(currentProject.getNumber());
        number.setBounds(20,100,150,30);
        addProjectFrame.add(number);

        JTextField endDate = new JTextField(currentProject.getStavka());
        endDate.setBounds(20,180,150,30);
        addProjectFrame.add(endDate);

        JButton toAdd = new JButton("Изменить специальность");
        toAdd.setBounds(60,220,80,30);
        toAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Project> projectList = Manipulate.readProjects(p.getProjectsFile());
                projectList.add(new Project(comp.getText(),project.getText(),
                      number.getText(),  endDate.getText(), currentProject.getRequirements()));
                projectList.remove(index);
                Manipulate.writeProjects(projectList,p.getProjectsFile());
                addProjectFrame.dispose();
                p.getContentPane().removeAll();
                p.redraw();
            }
        });

        addProjectFrame.add(toAdd);

        addProjectFrame.setVisible(true);
    }
}
