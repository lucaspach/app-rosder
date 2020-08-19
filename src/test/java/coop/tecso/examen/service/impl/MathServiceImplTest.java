package coop.tecso.examen.service.impl;

import org.junit.*;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MathServiceImplTest {

    private MathServiceImpl mathServImpl;

    @Before // antes de cada test, se va a ejecutar
    public void setUp() throws Exception {
        mathServImpl = new MathServiceImpl();
    }

    @BeforeClass // al comienzo de todo, se ejecuta una sola vez
    public static void setUp2() throws Exception {

    }

    @After // justo despues de cada test
    public void tearDown() throws Exception {
    }

    @AfterClass // al final de todo, se ejecuta una sola vez
    public static void tearDown2() throws Exception {
    }

    @Test
    public void testSumarIsOK() {
        //given: dado ciertas condiciones, precondiciones
        //when: llamar a la función que estoy testeando
        //then: las validaciones y asserts
        //given
        /*BigDecimal amount1 = new BigDecimal("1.83");
        BigDecimal amount2 = new BigDecimal("0.17");
        //when
        BigDecimal result = mathServImpl.sumar(amount1, amount2);
        //then
        assertEquals(new BigDecimal("2.00"), result);*/
        //investigar mockito
    }
}