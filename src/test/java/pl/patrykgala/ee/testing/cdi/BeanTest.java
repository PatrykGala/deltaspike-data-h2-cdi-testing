package pl.patrykgala.ee.testing.cdi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.IntStream;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.apache.deltaspike.testcontrol.api.mock.DynamicMockManager;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class BeanTest {

    @Inject
    private Bean bean;

    @Inject
    DynamicMockManager dynamicMockManager;

    @Test
    public void testInjections() {
        assertNotNull(bean);
        assertNotNull(bean.provider);
    }

    @Test
    public void testIncrementation() {
        IntStream.range(1, 10)
                 .forEach(i -> assertEquals(bean.getNumber(), i));

    }
}