module com.example.pt2024_30223_grigorescu_alexandru_assignment_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.pt2024_30223_grigorescu_alexandru_assignment_3 to javafx.fxml;
    opens com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.controllers to javafx.fxml;

    exports com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.controllers;
    exports com.example.pt2024_30223_grigorescu_alexandru_assignment_3;
}