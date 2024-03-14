module com.example.motlohelwa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.motlohelwa to javafx.fxml;
    exports com.example.motlohelwa;
}