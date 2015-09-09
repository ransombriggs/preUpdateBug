import org.junit.Test;
import play.test.FunctionalTest;

public class FlushFunctionalTest extends FunctionalTest {

    @Test
    public void controllerShouldHaveFlushModeCommit() {
        assertEquals(GET("/").out.toString(), "COMMIT");
    }

}
