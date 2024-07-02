package s3ich4n.spring6;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import s3ich4n.spring6.data.OrderRepository;

import javax.sql.DataSource;

@Configuration
public class DataConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());            // 어느 데이터소스를 쓰느냐?
        emf.setPackagesToScan("s3ich4n.spring6");   // 어느 패키지 밑에 있느냐?
        // JPA에 원하는 벤더 및 관련 정보를 추가
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{
            setDatabase(Database.H2);
            setGenerateDdl(true);
            setShowSql(true);
        }});

        return emf;
    }

    // 얘는 팩토리 빈이라 파라미터를 다르게 처리해야댐
    @Bean
    public OrderRepository orderRepository(EntityManagerFactory emf) {
        return new OrderRepository(emf);
    }
}
