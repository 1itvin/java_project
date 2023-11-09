import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Manipulate {
    public static List<Project> readProjects(File projectsFile) {
        List<Project> projects = new ArrayList<>();
        try(ObjectInputStream ibs = new ObjectInputStream(new FileInputStream(projectsFile))) {
            projects = (ArrayList<Project>) ibs.readObject();
            //return projects;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ошибка чтения.");
            projects = new ArrayList<>();
        } finally {
            return projects;
        }
    }

    public static List<AuthorizedUser> readUsers( File usersFile) {
        List<AuthorizedUser> users = new ArrayList<>();
        try(ObjectInputStream ibs = new ObjectInputStream(new FileInputStream(usersFile))) {
            users = (ArrayList<AuthorizedUser>) ibs.readObject();
            return users;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ошибка чтения.");
        }
        return null;
    }

    public static void writeProjects(List<Project> projects, File projectsFile) {
        try(ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream(projectsFile))) {
            obs.writeObject(projects);
        } catch (Exception e ) {
            JOptionPane.showMessageDialog(null, "Ошибка записи");
        }
    }

    public static void writeUsers(List<AuthorizedUser> users, File usersFile) {
        try(ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream(usersFile))) {
            obs.writeObject(users);
        } catch (Exception e ) {
            JOptionPane.showMessageDialog(null, "Ошибка записи");
        }
    }
}
