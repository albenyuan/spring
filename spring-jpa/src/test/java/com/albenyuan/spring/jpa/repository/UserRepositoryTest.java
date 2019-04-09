package com.albenyuan.spring.jpa.repository;

import com.albenyuan.spring.jpa.SpringJpaApplicationTests;
import com.albenyuan.spring.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

/**
 * @Author Alben Yuan
 * @Date 2019-04-09 08:45
 */
@Slf4j
public class UserRepositoryTest extends SpringJpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    /**
     * findAll(Example)
     * 1、不使用null对应的属性查询
     * 2、等值查询查
     * 3、条件并列查询
     */
    @Test
    public void testFindAllExample() {
        User user = new User();
        user.setId(1L);
        user.setPassword("123456");
        Example<User> userExample = Example.of(user);

        log.info("example: {}", userExample);
        List<User> users = userRepository.findAll(userExample);
        log.info("size of users is: {}", users.size());
        users.forEach(u -> log.info("users: {}={}", u.getId(), u));
    }

    /**
     * 1、不使用null对应的属性查询
     * 2、等值查询
     * 3、并列查询
     * 4、确保返回值最多为一个，否则会报异常
     * <code>org.springframework.dao.IncorrectResultSizeDataAccessException: query did not return a unique result: 2; nested exception is javax.persistence.NonUniqueResultException: query did not return a unique result: 2</code>
     */
    @Test
    public void testFindOneExample() {
        User user = new User();
//        user.setId(1L);
//        user.setPassword("123456");
        user.setEmail("albenyuan@aliyun.com");
        Example<User> userExample = Example.of(user);
        log.info("example: {}", userExample);
        Optional<User> userOptional = userRepository.findOne(userExample);
        log.info("find user: {}", userOptional.isPresent());
        log.info("the user is: {}", userOptional.get());
    }

    @Test
    public void testExampleMatcher() {

    }
}
