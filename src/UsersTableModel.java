import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class UsersTableModel implements TableModel {
    List<AuthorizedUser> users = new ArrayList<>();
    File usersFile;

    public  UsersTableModel(File usersFile) {
        this.usersFile = usersFile;
        this.users = Manipulate.readUsers(this.usersFile);
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Логин";
            case 1: return "Пароль";
            case 2: return "Роль";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
                return String.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return users.get(rowIndex).getLogin();
            case 1: return users.get(rowIndex).getPassword();
            case 2: return users.get(rowIndex).getRole();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: users.get(rowIndex).setLogin((String) aValue); break;
            case 1: users.get(rowIndex).setPassword((String) aValue); break;
            case 2: users.get(rowIndex).setRole((String) aValue); break;
        }
        Manipulate.writeUsers(this.users,this.usersFile);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
