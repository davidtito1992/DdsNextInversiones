package calculatorTest;

import org.junit.Test;
import calculator.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MultiplyTest {

    @Test
    public void shouldMultiply() throws Exception {
        assertThat(new Multiply().operate(new Value("2"), new Value("3")), is(new Value("6")));
    }
}