import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.event.PaintEvent;
import java.io.File;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDate;

public class DoctorTableModel implements TableModel {
    List<Doctor> doctors;
    User user;
    Project currentProject;
    List<Project> projects;
    File projectFile;
    ProjectsInfo p ;
    LocalDate today=LocalDate.now();

    public DoctorTableModel(ProjectsInfo p) {
        this.doctors = p.getProjectList().get(p.getTable().getSelectedRow()).getRequirements();
        this.user = p.getUser();
        this.projects = p.getProjectList();
        this.projectFile = p.getProjectsFile();
        this.currentProject = projects.get(p.getTable().getSelectedRow());
        this.p = p;
    }
    @Override
    public int getRowCount() {
        return doctors.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "ФИО";
            case 1: return "День рождения";
            case 2: return "Дата приема на работу";
            case 3: return "№ участка";
            case 4: return "Зарплата";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1:
            case 2: return LocalDate.class;
            case 3: return String.class;
            case 4: return int.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return doctors.get(rowIndex).getFio();//фио
            case 1:
                return doctors.get(rowIndex).getDay_of_birth();//др
            case 2:
                return doctors.get(rowIndex).getDate_of_employment();//день приема
            case 3: {
                if(projects.get(p.getSelectedRow()).getNarrow().equals("Да")) return doctors.get(rowIndex).getNumber_of_plot();
                else if(projects.get(p.getSelectedRow()).getNarrow().equals("Нет")) return "-";
                else return "wrong narrow";
            }
            case 4: {
                    double salary;
                    double stavka=Double.parseDouble (projects.get(p.getSelectedRow()).getStavka());
                    double coeff;
                    double coeff1;
                    double pensia=0; //если на пенсии
                    double allowance=0; // надбавка за стаж
                /** как считается зарплата */

                int age= Period.between(doctors.get(rowIndex).getDay_of_birth() ,today).getYears();
                int stazh= Period.between(doctors.get(rowIndex).getDate_of_employment(),today).getYears();

                    if(age>=55){
                        pensia=0.5;
                        return stavka*pensia;
                    }

                    if(stazh>=5 && stazh<10){
                        allowance=0.05;
                    }
                    else if(stazh>=10 && stazh<20){
                        allowance=0.10;
                    }
                    else if(stazh>=20 && stazh<35){
                        allowance=0.15;
                    }
                    else if(stazh>=35){
                        allowance=0.20;
                    }
                    coeff=pensia;
                    coeff1=allowance;
                    salary=stavka+(stavka*allowance);
                return salary;
            }
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
