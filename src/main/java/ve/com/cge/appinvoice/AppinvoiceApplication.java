package ve.com.cge.appinvoice;

//import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableAutoConfiguration

@RestController
@SpringBootApplication
public class AppinvoiceApplication {
    
        @RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(AppinvoiceApplication.class, args);
	}
        
        //@Bean
        //@ConfigurationProperties(prefix="app.datasource")
        //public DataSource dataSource() {
        //    return new FancyDataSource();
        //}

}
