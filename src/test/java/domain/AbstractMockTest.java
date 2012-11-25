package domain;

import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.internal.ExpectationBuilder;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public abstract class AbstractMockTest {

    public JUnit4Mockery context = new JUnit4Mockery();

    protected void checking(ExpectationBuilder expectations) {
        context.checking(expectations);
    }
}
