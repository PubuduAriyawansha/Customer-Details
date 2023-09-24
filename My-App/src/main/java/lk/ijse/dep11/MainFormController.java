package lk.ijse.dep11;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep11.tm.Employer;

import java.util.List;

public class MainFormController {

    public AnchorPane root;
    public TextField txtName;
    public TextField txtID;
    public TextField txtContact;
    public Button btnNew;
    public TableView tblCustomer;
    public Button btnSave;
    public Button btnDelete;
    public TextField txtSearch;



    public void btnNewOnAciton(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnACtion(ActionEvent actionEvent) {
    }
    private List<Employer> getEmployee(){
        return tblCustomer.getItems();
    }
    public boolean isDataValid(){
        if(!txtName.getText().strip().matches("^[A-Za-z ]+$")) {
            txtName.requestFocus();
            txtName.selectAll();
            return false;
        } else if (!txtContact.getText().strip().matches("0\\d{2}-\\d{7}")) {
            txtContact.requestFocus();
            txtContact.selectAll();
            return false;
        }
        return true;
    }
    private String getNewEmployeeId(){
        if(getEmployee().isEmpty()) return "E-001";
        String lastId = getEmployee().get(getEmployee().size()-1).getId();
        int id = Integer.parseInt(lastId.substring(2))+1;
        return String.format("E-%03d",id);
    }

}

