import connector.Connector;
import connector.IConnectMethod;
import general.AbstractMockTest;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RunnerTest extends AbstractMockTest {

   IConnectMethod method = context.mock(IConnectMethod.class);

    @Before
    public void
    connectMethodWithConnector() {
        Connector.setConnectMethod(method);
    }

    @Test
    public void
    allDistinctShouldInvokeConnector92Times() {
        expectAllDistinctSolutions();

        assertEquals(92, Runner.INSTANCE.allDistinctSolutions().size());
    }

    @Test
    public void
    allUniqueShouldInvokeConnector92Times() {
        expectAllUniqueSolutions();

        assertEquals(12, Runner.INSTANCE.allUniqueSolutions().size());
    }

    private void expectAllDistinctSolutions() {
        checking(new Expectations() {
            {
                exactly(92).of(method).invoke(with(aNonNull(String.class)));
            }
        });
    }

    private void expectAllUniqueSolutions() {
        checking(new Expectations() {
            {
                exactly(12).of(method).invoke(with(aNonNull(String.class)));
            }
        });
    }
}
