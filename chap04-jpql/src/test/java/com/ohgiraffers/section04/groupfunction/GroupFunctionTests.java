package com.ohgiraffers.section04.groupfunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupFunctionTests {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }
    @BeforeEach
    public void initManager(){
        entityManager = entityManagerFactory.createEntityManager();
    }
    @AfterEach
    public void closeManager(){
        entityManager.close();
    }
    @AfterAll
    public static void closeFactory(){
        entityManagerFactory.close();
    }

    /*
    * JPQL의 그룹함수는 CONUT, MAXM, MINM, SUM, ANG로 SQL의 그룹함수와 별반 차이가 없다.
    * 단 몇가지 주의 사항이 있다.
    * 1. 그룹함수의 반환 타입은 결과 값이 정수면 LONG, 실수명 DOUBLE로 반환된다.
    * 2. 값이 없는 상태에서는 COUNT를 제외한 그룹 함수는 NULL이 되고 COUNT만 0이된다.
    *  따라서 반환 값을 담기위해 선언하는 변수 타입을 기본자료형으로 하게되면, 조회결과를 언박싱 할때 NPE가 발생한다.
    * */

    @Test
    public void 특정_카테고리의_등록된_메뉴_수_조회(){
        int categoryCodeParam = 4;

        String jpql = "SELECT COUNT(m.menuPrice) FROM menu_section04 m WHERE m.categoryCode=  :categoryCode";
        long count0fMenu = entityManager.createQuery(jpql, long.class)
                .setParameter("categoryCode", categoryCodeParam)
                .getSingleResult();

        Assertions.assertTrue(count0fMenu >= 0);
        System.out.println(count0fMenu);
    }
    @Test
    public void count를_제외한_다른_그룹함수의_조회결과가_없는_경우_테스트() {

        //given
        int categoryCodeParameter = 2;

        //when
        String jpql = "SELECT SUM(m.menuPrice) FROM menu_section04 m WHERE m.categoryCode = :categoryCode";

        // 롱타입은 null 을 받을 수없다
//        long SUM =  entityManager.createQuery(jpql, Long.class).setParameter("categoryCode", categoryCodeParameter).getSingleResult();


        //then
        assertThrows(NullPointerException.class, () -> {
            /* 반환 값을 담을 변수의 타입을 기본 자료형으로 하는 경우 Wrapper 타입을 언박싱 하는 과정에서 NPE이 발생하게 된다. */
            long sumOfPrice = entityManager.createQuery(jpql, Long.class)
                    .setParameter("categoryCode", categoryCodeParameter)
                    .getSingleResult();

        });

        assertDoesNotThrow(() -> {
            /* 반환 값을 담는 변수를 Wrapper 타입으로 선언해야 null 값이 반환 되어도 NPE가 발생하지 않는다. */
            Long sumOfPrice = entityManager.createQuery(jpql, Long.class)
                    .setParameter("categoryCode", categoryCodeParameter)
                    .getSingleResult();

        });


    }

    @Test
    public void groupby절과_having절을_사용한_조회_테스트() {

        //given
        int minPrice = 50000;
        long minpriceLong = 50000L;

        //when
        String jpql = "SELECT m.categoryCode, SUM(m.menuPrice)"
                + " FROM menu_section04 m"
                + " GROUP BY m.categoryCode"
                + " HAVING SUM(m.menuPrice) >= :minPrice";

        List<Object[]> sumPriceOfCategoryList = entityManager.createQuery(jpql, Object[].class)
                .setParameter("minPrice", minPrice)
                .getResultList();

        List<Object[]> sumPriceOfCategoryListToLong = entityManager.createQuery(jpql, Object[].class)
                .setParameter("minPrice", minpriceLong)
                .getResultList();

        //

        //then
        assertNotNull(sumPriceOfCategoryList);
        sumPriceOfCategoryList.forEach(row -> {
            Arrays.stream(row).forEach(System.out::println);
        });

        assertNotNull(sumPriceOfCategoryList);
        sumPriceOfCategoryListToLong.forEach(row -> {
            Arrays.stream(row).forEach(System.out::println);
        });
    }
}
