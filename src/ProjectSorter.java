import java.util.Comparator;
public class ProjectSorter implements Comparator<Project>{
    private String howToSort;
    private String upOrDown;
    ProjectSorter(String howToSort, String upOrDown) {
        this.howToSort = howToSort;
        this.upOrDown = upOrDown;
    }
    @Override
    public int compare(Project o1, Project o2) {
        boolean flag = upOrDown.equals("убывание");
     switch (howToSort){
         case"Специальность":
             if(flag) return (-1)*o1.getName().compareToIgnoreCase(o2.getName());
             else return o1.getName().compareToIgnoreCase(o2.getName());
         case"Узкий":
             if(flag) return (-1)*o1.getNarrow().compareToIgnoreCase(o2.getNarrow());
             else return o1.getNarrow().compareToIgnoreCase(o2.getNarrow());
         case"Количество":
             if(flag) return (-1)*o1.getNumber().compareToIgnoreCase(o2.getNumber());
             else return o1.getNumber().compareToIgnoreCase(o2.getNumber());
         case"Ставка":
             if(flag) return (-1)*o1.getStavka().compareToIgnoreCase(o2.getStavka());
             else return o1.getStavka().compareToIgnoreCase(o2.getStavka());
         case"Общие затраты":
             if(flag) return (-1)*o1.getTotal().compareToIgnoreCase(o2.getTotal());
             else return o1.getTotal().compareToIgnoreCase(o2.getTotal());
     }
        return 0;
    }
}
