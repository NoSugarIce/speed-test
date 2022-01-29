package com.nosugarice.speedtest.plus.jmh.speed;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nosugarice.speedtest.DataSourceBuild;
import com.nosugarice.speedtest.base.StatusEnum;
import com.nosugarice.speedtest.plus.jmh.mapper.StudentMapper;
import com.nosugarice.speedtest.plus.jmh.mode.Student;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
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
import java.util.concurrent.TimeUnit;

/**
 * @author dingjingyang@foxmail.com
 * @date 2022/1/16
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 3)
public class PlusMapperSpeed {

    private StudentMapper studentMapper;

    @Setup
    public void setup() {
        Environment environment = new Environment("test", new JdbcTransactionFactory(), getDataSource());

        MybatisSqlSessionFactoryBuilder builder = new MybatisSqlSessionFactoryBuilder();
        MybatisConfiguration configuration = new MybatisConfiguration(environment);
        configuration.setCacheEnabled(false);
        configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);

        configuration.addMapper(StudentMapper.class);

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        configuration.addInterceptor(interceptor);

        SqlSessionFactory sqlSessionFactory = builder.build(configuration);

        studentMapper = sqlSessionFactory.openSession().getMapper(StudentMapper.class);
    }

    @Benchmark
    public void insert() {
        Student student = new Student();
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
    public Student selectById() {
        return studentMapper.selectById("de00000001");
    }

    @Benchmark
    public void selectListByCriteriaP5() {
        Student student = new Student();
        student.setId("de00000001");
        student.setName("张三");
        student.setPhone("1860738239");
        student.setAddress("南京");
        LambdaQueryWrapper<Student> query = Wrappers.lambdaQuery(student);
        query.likeLeft(Student::getName, "王");

        studentMapper.selectList(query);
    }

    @Benchmark
    public void selectListByCriteriaP9() {
        Student student = new Student();
        student.setId("de00000001");
        student.setName("张三");
        student.setPhone("1860738239");
        student.setAddress("南京");
        LambdaQueryWrapper<Student> query = Wrappers.lambdaQuery(student);
        query.likeLeft(Student::getName, "王")
                .le(Student::getAge, 20)
                .eq(Student::getSex, 0)
                .lt(Student::getSno, "d2020")
                .gt(Student::getCardBalance, new BigDecimal("1000"))
                //criteriaQuery.eq(Student::getStatus, StatusEnum.ON);
                .le(Student::getCreatedAt, LocalDateTime.now());

        studentMapper.selectList(query);
    }

    @Benchmark
    public void selectPage() {
        Student student = new Student();
        student.setId("de00000001");
        student.setName("张三");
        student.setPhone("1860738239");
        student.setAddress("南京");

        LambdaQueryWrapper<Student> query = Wrappers.lambdaQuery(student);
        query.likeLeft(Student::getName, "王")
                .le(Student::getAge, 20)
                .eq(Student::getSex, 0)
                .lt(Student::getSno, "d2020")
                .gt(Student::getCardBalance, new BigDecimal("1000"))
                //criteriaQuery.eq(Student::getStatus, StatusEnum.ON);
                .le(Student::getCreatedAt, LocalDateTime.now());

        studentMapper.selectPage(new Page<>(2, 2), query);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PlusMapperSpeed.class.getSimpleName())
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
                .withScriptPath("/student_schema.sql")
                .build();
    }

}
