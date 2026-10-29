package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.type.BoolType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArithExpTest {

    private final IDictionary<String, IValue> symTable = new MyDictionary<>();

    private static IExp num(int n) {
        return new ValueExp(new IntValue(n));
    }

    @Test
    void respectsOperatorPrecedenceEncodedInTheTree() throws MyException {
        IExp e = new ArithExp(ArithOp.ADD, num(2),
                new ArithExp(ArithOp.MUL, num(3), num(5)));
        assertEquals(new IntValue(17), e.eval(symTable));
    }

    @Test
    void divisionByZeroThrows() {
        IExp e = new ArithExp(ArithOp.DIV, num(1), num(0));
        var ex = assertThrows(ExpressionException.class, () -> e.eval(symTable));
        assertEquals("division by zero", ex.getMessage());
    }

    @Test
    void divisionByZeroNoThrows() throws MyException {
        IExp e = new ArithExp(ArithOp.DIV, num(10), num(2));

        assertEquals(new IntValue(5), e.eval(symTable));


    }

    @Test
    void firstOperandIntigerThrows() {
        IExp e = new ArithExp(ArithOp.ADD, new ValueExp(new BoolValue(true)), num(2));
        var ex = assertThrows(ExpressionException.class, () -> e.eval(symTable));
        assertEquals("First operand is not an integer", ex.getMessage());
    }

    @Test
    void secondOperandIntigerThrows() {
        IExp e = new ArithExp(ArithOp.SUB, num(2), new ValueExp(new BoolValue(true)));
        var ex = assertThrows(ExpressionException.class, () -> e.eval(symTable));
        assertEquals("Second operand is not an integer", ex.getMessage());
    }

    @Test
    void checkSubOperation() throws MyException {
        IExp e = new ArithExp(ArithOp.SUB, num(3), num(2));
        assertEquals(new IntValue(1), e.eval(symTable));
    }
    @Test
    void checkToString(){
        IExp e = new ArithExp(ArithOp.SUB, num(3), num(2));
        assertEquals("(3 - 2)",e.toString());
    }
}
