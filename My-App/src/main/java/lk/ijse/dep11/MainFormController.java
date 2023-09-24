package lk.ijse.dep11;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep11.tm.Employer;

import java.util.List;

public class MainFormController {

    public AnchorPane root;
    public TextField txtName;
    public TextField txtID;
    public TextField txtContact;
    public Button btnNew;
    public TableView <Employer>tblCustomer;
    public Button btnSave;
    public Button btnDelete;
    public TextField txtSearch;

    public void initialize(){
        for (Control control: new Control[]{txtID,txtContact,txtName,btnSave,btnDelete}){
            control.setDisable(true);
        }
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((o,old,current)->{
            if(current==null){
                btnDelete.setDisable(true);
            } else{
                txtID.setText(current.getId());
                txtName.setText(current.getName());
                txtContact.setText(current.getContact());
                btnDelete.setDisable(false);
            }
        });

        tblCustomer.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.DELETE){
                btnDelete.fire();
            }
        });
    }


    public void btnNewOnAciton(ActionEvent actionEvent) {
        txtID.setText(getNewEmployeeId());
        for(Control control: new Control[]{txtName,txtContact,btnSave}){
            if(control instanceof TextField) ((TextField) control).clear();
            control.setDisable(false);
        }
        txtName.requestFocus();
        tblCustomer.getSelectionModel().clearSelection();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!isDataValid()) return;
        List<Employer> list = getEmployee();
        for (Employer employer : list) {
            if(tblCustomer.getSelectionModel().getSelectedItem()==employer) continue;
            if (employer.getContact().equals(txtContact.getText())) {
                new Alert(Alert.AlertType.ERROR, "Contact number already exists ").show();
                txtContact.requestFocus();
                txtContact.selectAll();
                return;
            }
        }

        if(tblCustomer.getSelectionModel().isEmpty()){
            Employer employer = new Employer(txtID.getText(),txtName.getText(),txtContact.getText());
            getEmployee().add(employer);
            btnNew.fire();

        } else{
            Employer employer = tblCustomer.getSelectionModel().getSelectedItem();
            employer.setName(txtName.getText().strip());
            employer.setContact(txtContact.getText().strip());
            tblCustomer.refresh();
            btnNew.fire();

        }
    }

    public void btnDeleteOnACtion(ActionEvent actionEvent) {
        getEmployee().remove(tblCustomer.getSelectionModel().getSelectedItem());
        btnNew.fire();
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

