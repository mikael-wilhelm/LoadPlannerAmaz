package se.exjob.startupManageBean;

import com.googlecode.flyway.core.Flyway;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.net.URI;
import java.sql.Driver;


@ManagedBean(eager = true)
@ApplicationScoped
public class StartUp {
    public StartUp(){
        try {
            URI dbUri = new URI(System.getenv("SHARED_DATABASE_URL"));
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
            Driver driver = new org.postgresql.Driver();
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource(driver,dbUrl );
            String password = dbUri.getUserInfo().split(":")[1];
            dataSource.setUsername(dbUri.getUserInfo().split(":")[0]);
            dataSource.setPassword(password);

            Flyway flyway = new Flyway();
            flyway.setBaseDir("db/migration");
            flyway.setDataSource(dataSource);
            flyway.migrate();
        } catch (Exception ignored) {

        }
    }
}
