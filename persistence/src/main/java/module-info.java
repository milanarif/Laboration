module persistence {
    requires jakarta.persistence;
    requires javax.persistence;

    opens dao to com.google.gson, jakarta.persistence;
    opens entity to com.google.gson, jakarta.persistence;

    exports dao;
    exports entity;
}