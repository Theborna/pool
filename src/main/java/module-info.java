module com.homework {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    opens com.project to javafx.fxml;
    opens com.project.controllers to javafx.fxml;
    exports com.project;
}
