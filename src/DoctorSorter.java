import java.util.Comparator;
public class DoctorSorter implements Comparator<Doctor> {
    private String howToSort;
    private String upOrDown;
    DoctorSorter(String howToSort, String upOrDown) {
        this.howToSort = howToSort;
        this.upOrDown = upOrDown;
    }
    @Override
    public int compare(Doctor o1, Doctor o2) {
        boolean flag = upOrDown.equals("убывание");
        switch (howToSort){
            case"ФИО":
                if(flag) return (-1)*o1.getFio().compareToIgnoreCase(o2.getFio());
                else return o1.getFio().compareToIgnoreCase(o2.getFio());
            case"День рождения":
                if(flag) return (-1)*o1.getDay_of_birth().compareTo(o2.getDay_of_birth());
                else return o1.getDay_of_birth().compareTo(o2.getDay_of_birth());
            case"День приема":
                if(flag) return (-1)*o1.getDate_of_employment().compareTo(o2.getDate_of_employment());
                else return o1.getDate_of_employment().compareTo(o2.getDate_of_employment());
            case"№ участка":
                if(flag) return (-1)*o1.getNumber_of_plot().compareToIgnoreCase(o2.getNumber_of_plot());
                else return o1.getNumber_of_plot().compareToIgnoreCase(o2.getNumber_of_plot());
        }
    return 0;
    }
}
