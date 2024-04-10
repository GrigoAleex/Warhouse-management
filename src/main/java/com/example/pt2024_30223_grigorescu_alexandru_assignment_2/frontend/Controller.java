package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.requests.PolynomialCalculateRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Session;

//TODO: rename params and methods
public class Controller {
    private Boolean writeP = true;

    @FXML
    private Text p;

    @FXML
    private TextField input;

    @FXML
    private Text q;

    @FXML
    private ComboBox<ComboBoxOption> dropdown;

    @FXML
    private Text AlertBox;

    private void setDropdown() {
        ObservableList<ComboBoxOption> options = FXCollections.observableArrayList(
                new ComboBoxOption("Adună", "add"),
                new ComboBoxOption("Scade", "subtract"),
                new ComboBoxOption("Înmulțeste", "multiply"),
                new ComboBoxOption("Împarte", "divide"),
                new ComboBoxOption("Derivează", "derive"),
                new ComboBoxOption("Integrează", "integrate")
        );

        dropdown.setItems(options);
        if(Session.containsKey("operation")) dropdown.getSelectionModel().select(Integer.parseInt(Session.get("operation")));
    }

    private void setFields() {
        p.setFill(Colors.GREEN);
        if (Session.containsKey("p")) p.setText(Session.get("p"));
        if (Session.containsKey("q")) q.setText(Session.get("q"));

        input.textProperty().addListener((observable, oldValue, newValue) -> {
            String value = newValue.isEmpty() ? "..." : newValue;
            if (writeP) p.setText(value);
            else q.setText(value);
        });

        setDropdown();
    }

    private void setAlertBox() {
        if (Session.containsKey("error")) {
            AlertBox.setText(Session.get("error"));
            AlertBox.setFill(Colors.RED);
        } else if (Session.containsKey("result")) {
            AlertBox.setText("Rezultatul este: " + Session.get("result"));
            AlertBox.setFill(Colors.GREEN);
        }
    }

    public void initialize() {
//        setFields();
//        setAlertBox();
    }

    @FXML
    void writeP1() {
        if (!writeP) triggerWriteZone();
        input.setText(p.getText());
    }

    @FXML
    void writeP2() {
        if(writeP) triggerWriteZone();
        input.setText(q.getText());
    }

    @FXML
    void submit() {
        if (dropdown.getSelectionModel().getSelectedIndex() == -1) {
            AlertBox.setText("Trebuie aleasă o operație");
            AlertBox.setFill(Colors.RED);
            return;
        }
        System.out.println(dropdown.getSelectionModel().getSelectedIndex());

        Session.set("p", p.getText());
        Session.set("q", q.getText());
        Session.set("operation", String.valueOf(dropdown.getSelectionModel().getSelectedIndex()));
        PolynomialCalculateRequest request = new PolynomialCalculateRequest(p.getText(), q.getText(), dropdown.getValue().getValue());
        Router.go("calculate", request);
    }

    private void triggerWriteZone() {
        if (writeP) {
            q.setFill(Colors.GREEN);
            p.setFill(Color.WHITE);
        } else {
            p.setFill(Colors.GREEN);
            q.setFill(Color.WHITE);
        }

        writeP = !writeP;
    }
}
