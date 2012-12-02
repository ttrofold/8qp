package domain.manager;

import general.AbstractMockTest;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FENManagerTest extends AbstractMockTest {

    @Test
    public void
    allShouldReturn92FENStrings() {
       assertEquals(92, FENManager.distinct().size());
    }

    @Test
    public void
    uniqueShouldReturn12FENStrings() {
       assertEquals(12, FENManager.unique().size());
    }
}
