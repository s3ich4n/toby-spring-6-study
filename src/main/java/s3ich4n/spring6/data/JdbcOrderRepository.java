package s3ich4n.spring6.data;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import s3ich4n.spring6.order.Order;
import s3ich4n.spring6.order.OrderRepository;

import javax.sql.DataSource;

public class JdbcOrderRepository implements OrderRepository {
    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    // Bean 초기화까지 다 끝났을 때 알아서 이걸 돌려준다
    @PostConstruct
    void initDb() {
        jdbcClient.sql("""
            create table orders (id bigint not null, no varchar(255), total numeric(38,2), primary key (id));
            alter table if exists orders drop constraint if exists UK_43egxxciqr9ncgmxbdx2avi8n;
            alter table if exists orders add constraint UK_43egxxciqr9ncgmxbdx2avi8n unique (no);
            create sequence orders_SEQ start with 1 increment by 50;
        """).update();
    }

    // 로직 자체는 data.sql 전부 한번에 넣고 해도되지만, 하나씩 단계별로 검증해도 된다
    // 하나씩하나씩 잘 되는지 보고 넘어가는 편이 마음이 더 놓이기 때문.
    @Override
    public void save(Order order) {
        Long id = jdbcClient.sql("select next value for orders_SEQ").query(Long.class).single();
        order.setId(id);

        jdbcClient.sql("insert into orders (no,total,id) values (?,?,?)")
                .params(order.getNo(), order.getTotal(), id)
                .update();
    }
}
