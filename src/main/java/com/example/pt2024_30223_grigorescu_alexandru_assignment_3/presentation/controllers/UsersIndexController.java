package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.controllers;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.User;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.UserEditRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.UserDAO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersIndexController implements Initializable {

    @FXML
    private Text employeeName;

    @FXML
    private ImageView employeeImage;

    @FXML
    private Text employeeRole;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, Number> idColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private TableColumn<User, Boolean> actionColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        phoneColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhoneNumber()));
        actionColumn.setCellFactory(new Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>>() {
            @Override
            public TableCell<User, Boolean> call(TableColumn<User, Boolean> param) {
                return new TwoButtonCell<>(); // Returnează o nouă instanță de TableCell personalizat
            }
        });

        actionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, Boolean>, ObservableValue<Boolean>>() {
            @Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<User, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });

        table.setItems(getUsers());
    }

    private ObservableList<User> getUsers() {
        return FXCollections.observableArrayList(new UserDAO().all());
    }

    @FXML
    void goProducts() {
        Router.go("products.index");
    }

    @FXML
    void goClients() {
        Router.go("users.index");
    }

    @FXML
    void goOrders() {
        Router.go("orders.index");
    }

    @FXML
    void goLogs() {
        Router.go("logs.index");
    }

    @FXML
    void goEmployees() {
        Router.go("employees.index");
    }

    @FXML
    void goCreateOrder() {
        Router.go("orders.create");
    }

    @FXML
    void createClient(ActionEvent event) {
        Router.go("users.create");
    }

    public class ButtonCell<S, T> extends TableCell<S, T> {
        private final Button button;
        public ButtonCell() {
            this.button = new Button("Edit"); // Creează un buton cu textul "Edit"
            this.button.setOnAction(event -> {
                // Acțiunea care va fi executată când se face clic pe buton
                // De exemplu, poți deschide o fereastră de editare pentru elementul din această linie
                S rowData = getTableRow().getItem();
                if (rowData instanceof User) {
                    int id = ((User) rowData).getId();
                    System.out.println("Edit button clicked in row with ID: " + id);
                }
            });
        }

        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null); // Dacă celula este goală, nu afișa nimic
            } else {
                setGraphic(button); // Altfel, afișează butonul
            }
        }
    }

    public class TwoButtonCell<S, T> extends TableCell<S, T> {
        private final Button editButton;
        private final Button deleteButton;

        public TwoButtonCell() {
            this.editButton = new Button("Edit");
            this.deleteButton = new Button("Delete");

            // Set actions for buttons
            this.editButton.setOnAction(event -> {
                // Action for edit button
                S rowData = getTableRow().getItem();
                if (rowData instanceof User) {
                    int id = ((User) rowData).getId();
                    Router.go("users.edit", new UserEditRequest(id));
                }
            });

            this.deleteButton.setOnAction(event -> {
                S rowData = getTableRow().getItem();
                if (rowData instanceof User) {
                    ((User) rowData).delete();
                    table.setItems(getUsers());
                }
            });
        }

        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null); // If the cell is empty, do not display anything
            } else {
                setGraphic(new HBox(editButton, deleteButton)); // Display both buttons in the cell
            }
        }
    }

}
