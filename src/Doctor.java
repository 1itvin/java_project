import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Doctor implements Serializable {
    String fio;
    //String day_of_birth;
    String number_of_plot;
    //String date_of_employment;

    LocalDate date_of_birth;
    LocalDate date_of_employment;

    public Doctor(String fio, String  begin , String end, String isDone) {
        this.fio = fio;
        this.date_of_birth = LocalDate.parse(begin, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.date_of_employment = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.number_of_plot = isDone;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getNumber_of_plot() {
        return number_of_plot;
    }

    public String isNarrowNum() {
        return "-";
    }


    public LocalDate getDay_of_birth() {
        return date_of_birth;
    }

    public void setDay_of_birth(LocalDate day_of_birth) {
        this.date_of_birth = day_of_birth;
    }

    public void setDone(String done) {
        number_of_plot = done;
    }

    public LocalDate getDate_of_employment() {
        return date_of_employment;
    }

    public void setDate_of_employment(LocalDate date_of_employment) {
        this.date_of_employment = date_of_employment;
    }
}
