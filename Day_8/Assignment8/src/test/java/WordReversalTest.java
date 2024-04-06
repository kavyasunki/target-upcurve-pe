import com.targetindia.pack1.WordReversal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordReversalTest {

    @Test
    void testReverseWords_SingleWordInput() {
        assertEquals("World", WordReversal.reverseWords("World"));
    }

    @Test
    void testReverseWords_MultipleWordsInput() {
        assertEquals("test a is This", WordReversal.reverseWords("This is a test"));
    }

    @Test
    void testReverseWords_LeadingAndTrailingSpaces() {
        assertEquals("World Hello", WordReversal.reverseWords("  Hello World "));
    }

    @Test
    void testReverseWords_ExtraSpacesBetweenWords() {
        assertEquals("test a is This", WordReversal.reverseWords("This   is   a    test"));
    }

    @Test
    void testReverseWords_EmptyStringInput() {
        assertEquals("", WordReversal.reverseWords(""));
    }

    @Test
    void testReverseWords_SpecialCharactersInput() {
        assertEquals("world! Hello, Welcome", WordReversal.reverseWords("Welcome, Hello! world"));
    }
}
