import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /** если вдруг ошибка чтения то раскоментируй
         * upd. сейчас если ошибка чтения то скипай дальше оно само создаст новый текстовый файл
         */
        /*try(ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream("projectsInfo.bin"))) {
            Doctor[] docs = new Doctor[]{new Doctor("flo","2222.12.12","3214.05.05","Нет")};
            obs.writeObject(new ArrayList<>(Arrays.asList(docs) ));
        } catch (IOException e) {
            System.out.println("error");
        }*/
        new ProjectsInfo();
    }
}
