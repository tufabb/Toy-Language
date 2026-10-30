package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.MyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {
    private  IList<String> out;

    @BeforeEach
    public void setUp() {
        this.out = new MyList<>();
    }

    @Test
    void getCorrectAddedElement() throws MyException {
        out.add("a");
        out.add("b");
        out.add("c");
        out.add("d");
        out.add("e");
        out.add("f");
        out.add("g");
        assertEquals("e", out.get(4));

    }

    @Test
    void getExceptionIndexOut() {
        out.add("a");
        out.add("b");
        out.add("c");
        out.add("d");
        out.add("e");
        out.add("f");
        out.add("g");
        var ex = assertThrows(ADTException.class, () -> out.get(44));
        assertEquals("index out of bounds", ex.getMessage());
    }

    @Test
    void getCheckSize() {
        out.add("a");
        out.add("b");
        out.add("c");
        out.add("d");
        out.add("e");
        out.add("f");
        out.add("g");
        assertNotEquals(22, out.size());
        out.add("e");
        assertEquals(8, out.size());
    }
}