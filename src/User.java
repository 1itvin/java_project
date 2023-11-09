import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Collections;

public abstract class User implements Serializable {
    public void showRequirements(ProjectsInfo p, Project currentProject) {
        try {
            JFrame req = new JFrame("Врачи");
            req.setBounds(100, 100, 700, 300);

            DoctorTableModel projectTableModel = new DoctorTableModel(p);
            JTable table = new JTable(projectTableModel);

            JScrollPane scrollPane = new JScrollPane(table);
            req.add(scrollPane);

            JPanel forSort = new JPanel();
            req.add(forSort, BorderLayout.NORTH);

            JComboBox<String> sortReqs = new JComboBox<>(new String[]{"ФИО, убывание", "ФИО, возрастание",
            "День рождения, убывание", "День рождения, возрастание", "День приема, убывание","День приема, возрастание",
            "№ участка, убывание","№ участка, возрастание","Зарплата, убывание","Зарплата, возрастание"});

            forSort.add(sortReqs);

            JButton toSort = new JButton("Сортировать");
            toSort.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] args = sortReqs.getSelectedItem().toString().split(", ");
                    Collections.sort(currentProject.getRequirements(), new DoctorSorter(args[0],args[1]));
                    Manipulate.writeProjects(p.getProjectList(),p.getProjectsFile());
                    req.dispose();
                    User.this.showRequirements(p,currentProject);
                }
            });
            forSort.add(toSort);

            if (p.getUser() instanceof BusinessAnalysts) {
                JPanel pan = new JPanel();
                JButton toAdd = new JButton("Добавить врача");
                toAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((BusinessAnalysts)p.getUser()).createRequirement(p,req);
                    }
                });
                pan.add(toAdd);

                JButton toDel = new JButton("Удалить врача");
                toDel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Project project = p.getProjectList().get(p.getTable().getSelectedRow());
                        ((BusinessAnalysts)p.getUser()).deleteRequirement(p,table,project);
                        req.dispose();
                    }
                });

                JButton toChange = new JButton("Изменить врача");
                toChange.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((BusinessAnalysts)p.getUser()).changeRequirement(p,table,req, currentProject);
                        req.dispose();
                    }
                });
                pan.add(toDel);
                pan.add(toChange);
                req.add(pan, BorderLayout.SOUTH);
            }

            req.setVisible(true);
        } catch (Exception e ) {
            JOptionPane.showMessageDialog(p,"Врачей нет и/или вы не выбрали специальность");
        }
    }
}
