import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;

public class SpecialityTableModel implements TableModel {
    List<Project> projects;
    User user;
    File projectFile;

    public SpecialityTableModel(ProjectsInfo p){
        this.user = p.getUser();
        this.projectFile = p.getProjectsFile();
        this.projects = Manipulate.readProjects(this.projectFile);
    }


    @Override
    public int getRowCount() {
        return projects.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Название специальности";
            case 1: return "Узкий?";
            case 2: return "Количество";
            case 3: return "Ставка";
            case 4: return "Общие затраты";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
                return String.class;
            case 2:
            case 3:
                return String.class;
            case 4: return int.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (user.getClass().equals(BusinessAnalysts.class)) {
            switch (columnIndex) {
                case 0:
                case 1:
                case 2:
                case 3:
                    return true;
                case 4:
                    return false;
            }
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return projects.get(rowIndex).getName();
            case 1: return projects.get(rowIndex).getNarrow();
            case 2: return projects.get(rowIndex).getNumber();
            case 3: return projects.get(rowIndex).getStavka();
            case 4: return String.valueOf(Integer.parseInt(projects.get(rowIndex).getNumber())*Integer.parseInt(projects.get(rowIndex).getStavka()));
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                projects.get(rowIndex).setCompany((String)aValue);
                break;
            case 1:
                projects.get(rowIndex).setProjectName((String)aValue);
                break;
            case 2: projects.get(rowIndex).setNumber((String)aValue);
            case 3: projects.get(rowIndex).setStavka((String) aValue); break;
            case 4: break;
        }
        Manipulate.writeProjects(this.projects, this.projectFile);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
