package com.nosugarice.speedtest.nosugare.jmh.speed;

import com.nosugarice.mybatis.builder.MapperBuilder;
import com.nosugarice.mybatis.config.MapperBuilderConfigBuilder;
import com.nosugarice.mybatis.config.MetadataBuildingContext;
import com.nosugarice.mybatis.criteria.CriteriaBuilder;
import com.nosugarice.mybatis.criteria.tocolumn.LambdaQuery;
import com.nosugarice.mybatis.domain.PageImpl;
import com.nosugarice.speedtest.DataSourceBuild;
import com.nosugarice.speedtest.base.StatusEnum;
import com.nosugarice.speedtest.nosugare.jmh.mapper.Student1Mapper;
import com.nosugarice.speedtest.nosugare.jmh.mode.Student1;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author dingjingyang@foxmail.com
 * @date 2022/1/16
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 3)
public class NoSugarMapperSpeed {

    private Student1Mapper studentMapper;

    @Setup
    public void setup() {
        Environment environment = new Environment("test", new JdbcTransactionFactory(), getDataSource());
        Configuration configuration = new Configuration(environment);
        configuration.setCacheEnabled(false);
        configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);

        MapperBuilderConfigBuilder configBuilder = new MapperBuilderConfigBuilder(null, configuration.getVariables());
        MetadataBuildingContext metadataBuildingContext = new MetadataBuildingContext(configuration, configBuilder.build());

        configuration.addMapper(Student1Mapper.class);
        new MapperBuilder(metadataBuildingContext).process(Student1Mapper.class);

        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(configuration).openSession();
        studentMapper = sqlSession.getMapper(Student1Mapper.class);
    }

    @Benchmark
    public void insert() {
        Student1 student = new Student1();
        student.setName("王小二");
        student.setAge(18);
        student.setSex(0);
        student.setSno("203030");
        student.setPhone("186");
        student.setAddress("南京");
        student.setCardBalance(new BigDecimal("0"));
        student.setStatus(StatusEnum.ON);
        student.setCreatedAt(LocalDateTime.now());

        studentMapper.insert(student);
    }

    @Benchmark
    public Optional<Student1> selectById() {
        return studentMapper.selectById("de00000001");
    }

    @Benchmark
    public void selectListByCriteriaP5() {
        Student1 student = new Student1();
        student.setId("de00000001");
        student.setName("张三");
        student.setPhone("1860738239");
        student.setAddress("南京");

        LambdaQuery<Student1> query = CriteriaBuilder.lambdaQuery(student);
        query.startsWith(Student1::getName, "王");

        studentMapper.selectList(query);
    }

    @Benchmark
    public void selectListByCriteriaP9() {
        Student1 student = new Student1();
        student.setId("de00000001");
        student.setName("张三");
        student.setPhone("1860738239");
        student.setAddress("南京");

        LambdaQuery<Student1> query = CriteriaBuilder.lambdaQuery(student);
        query.startsWith(Student1::getName, "王")
                .lessThan(Student1::getAge, 20)
                .equalTo(Student1::getSex, 0)
                .lessThan(Student1::getSno, "d2020")
                .greaterThan(Student1::getCardBalance, new BigDecimal("1000"))
                //.equalTo(Student1::getStatus, StatusEnum.ON)
                .lessThan(Student1::getCreatedAt, LocalDateTime.now());

        studentMapper.selectList(query);
    }

    @Benchmark
    public void selectPage() {
        Student1 student = new Student1();
        student.setId("de00000001");
        student.setName("张三");
        student.setPhone("1860738239");
        student.setAddress("南京");

        LambdaQuery<Student1> query = CriteriaBuilder.lambdaQuery(student);
        query.startsWith(Student1::getName, "王")
                .lessThan(Student1::getAge, 20)
                .equalTo(Student1::getSex, 0)
                .lessThan(Student1::getSno, "d2020")
                .greaterThan(Student1::getCardBalance, new BigDecimal("1000"))
                //.equalTo(Student1::getStatus, StatusEnum.ON)
                .lessThan(Student1::getCreatedAt, LocalDateTime.now());

        studentMapper.selectPage(query, new PageImpl<>(2, 2));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(NoSugarMapperSpeed.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    private DataSource getDataSource() {
        return new DataSourceBuild()
                .withJdbcDriver("org.hsqldb.jdbcDriver")
                .withJdbcUrl("jdbc:hsqldb:mem:aname")
                .withUsername("sa")
                .withPassword("")
                .withScriptPath("/student1_schema.sql")
                .build();
    }

}
