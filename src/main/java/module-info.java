module edu.psu.fractal {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.psu.fractal to javafx.fxml;
    exports edu.psu.fractal;
}