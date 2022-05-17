module com.homework {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive java.desktop;

    opens com.project to javafx.fxml;
    opens com.project.controllers to javafx.fxml, com.project.Game;

    exports com.project;
}
