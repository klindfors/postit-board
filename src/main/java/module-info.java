module org.example.postit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.desktop;


    opens org.example.postit to javafx.fxml;
    exports org.example.postit;

}