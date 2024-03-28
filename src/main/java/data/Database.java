package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    Connection connect;

    public Database(){
        connect = this.getConnection();
    }

    public Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver"; // Cambiado el driver para MySQL
            String server = "localhost";
            String port = "3306"; // Puerto predeterminado de MySQL
            String user = "root";
            String password = "root";
            String database = "facturacionelectronica"; //Nombre de la base de datos

            // URL de conexión para MySQL
            String URL_conexion = "jdbc:mysql://" + server + ":" + port + "/" + database + "?user=" + user + "&password=" + password + "&useSSL=false&serverTimezone=UTC";

            Class.forName(driver);
            return DriverManager.getConnection(URL_conexion);
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // Crear una instancia de la clase Database
        Database db = new Database();

        // Probar la conexión
        if (db.getConnection() != null) {
            System.out.println("Conexión exitosa!");
        } else {
            System.err.println("No se pudo establecer la conexión.");
        }
    }

}
