module com.example.pt2024_30223_grigorescu_alexandru_assignment_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;

    opens com.example.pt2024_30223_grigorescu_alexandru_assignment_2 to javafx.fxml;
    opens com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.controllers to javafx.fxml;

    exports com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.controllers;
    exports com.example.pt2024_30223_grigorescu_alexandru_assignment_2;
}