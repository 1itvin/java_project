
import java.io.Serializable;
import java.util.List;

public class  Project implements Serializable {
    private String name;
    private String narrow;

//    LocalDate date_of_birth;
//    LocalDate date_of_employment;
    private String number;
    private String stavka;
    private String total;
    private int programmers;
    private List<Doctor> doctors;
    private int totalTime;
    private int totalDays;

    public Project(String name,String narrow, String number, String stavka, List<Doctor> doctors) {
        this.name = name;
        this.narrow = narrow;
        this.number=number;
        this.stavka = stavka;
//        this.BirthDate = LocalDate.parse(beginDate, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
//        this.EmpDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        this.doctors = doctors;
        this.calculateProgrammers();
    }

    public void calculateProgrammers() {
        int num=Integer.parseInt(this.getNumber());
        this.total= String.valueOf(num);

//        for(Doctor req:this.doctors) {
//            if(!(req.number_of_plot.compareToIgnoreCase("да")==0)) //totalTime+= req.getDay_of_birth();
//        }

        /*
        if(this.getEndDate().equals(this.beginDate)) {
            this.programmers = (int) Math.ceil((Double.valueOf(totalTime) / (8)));
        }
        else{
                for (LocalDate temp = this.beginDate; !temp.equals(this.endDate); i++) {
                    temp = beginDate.plusDays(i);
                    if (!(temp.getDayOfWeek().equals(DayOfWeek.SUNDAY) | temp.getDayOfWeek().equals(DayOfWeek.SATURDAY)))
                        totalDays += 1;
                }
                this.programmers = (int) Math.ceil((Double.valueOf(totalTime) / (Double.valueOf(totalDays) * 8)));
            }*/
    }

    public String getName() {
        return name;
    }

    public String getNarrow() {
        return narrow;
    }

    public String getStavka() {
        return stavka;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number=number;
    }
    public String getTotal() {
        return total;
    }
    public void setTotal() {
        this.total=total;
    }
    public void setCompany(String name) {
        this.name = name;
    }

    public void setProjectName(String narrow) {
        this.narrow = narrow;
    }


    public List<Doctor> getRequirements() {
        return doctors;
    }

    public void setRequirements(List<Doctor> doctors) {
        this.doctors = doctors;
    }

//    public LocalDate getBirthDate() {
//        return BirthDate;
//    }
//
//    public LocalDate getEmpDate() {
//        return EmpDate;
//    }

    public int getProgrammers() {
        return programmers;
    }

//    public void setBirthDate(String BirthDate) {
//        this.BirthDate = LocalDate.parse(BirthDate,DateTimeFormatter.ofPattern("yyyy.MM.dd"));
//    }
//
//    public void setEndDate(String EmpDate) {
//        this.EmpDate = LocalDate.parse(EmpDate,DateTimeFormatter.ofPattern("yyyy.MM.dd"));;
//    }

    public void setStavka(String stavka) {
        this.stavka = stavka;
    }
}
