import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class PostfixTest {
    Parser test = null;
    private static ByteArrayInputStream in;
    private static Scanner scanner;
     
    @Before
    public void testBeforeClass() throws IOException {
        System.out.println("");
        System.out.println("=====================================");
    }

    @Test
    public void testLexicalError() throws IOException {
        System.out.println("≤‚ ‘¥ ∑®¥ÌŒÛ");
        String data = " 1 + 1 +a 1\n";
        System.out.print(" ‰»Î£∫" + data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        test = new Parser();
		test.expr();
    }

    @Test
    public void testSyntaxError() throws IOException {
        System.out.println("≤‚ ‘”Ô∑®¥ÌŒÛ£®∞¸¿®◊Û‘ÀÀ„¡ø»±…Ÿ∫Õ‘ÀÀ„∑˚»± ß£©");
        String data = "1++11+1\n";
        System.out.print(" ‰»Î£∫" + data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        test = new Parser();
		test.expr();
    }

    @Test
    public void testComprehensiveError() throws IOException {
        System.out.println("◊€∫œ≤‚ ‘");
        String data = "+8/9 +6-\n";
        System.out.print(" ‰»Î£∫" + data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        test = new Parser();
		test.expr();
    }

    @After
    public void testAfterClass() throws IOException {
        System.out.println("=====================================");
        System.out.println("");
    }
}
