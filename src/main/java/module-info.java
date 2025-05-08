module com.zackystardust {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop; // For AWT dependencies

    opens com.zackystardust to javafx.fxml;
    exports com.zackystardust;
}