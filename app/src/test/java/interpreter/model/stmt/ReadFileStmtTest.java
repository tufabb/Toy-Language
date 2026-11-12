package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileStmtTest {
    @TempDir
    Path dir; // a new empty folder for every test
    private PrgState state;
    private ValueExp file; // the expression "<dir>/numbers.in"
    @BeforeEach
    void setUp() throws Exception {
        Path f = dir.resolve("numbers.in");
        Files.writeString(f, "7\n");
        file = new ValueExp(new StringValue(f.toString()));
        state = PrgState.of(new NopStmt());
        new VarDeclStmt("x", new IntType()).execute(state);
    }
    @Test
    void correctOrderTest() throws MyException {
        new OpenRFileStmt(file).execute(state);
        new ReadFileStmt(file, "x").execute(state);
        assertEquals(new IntValue(7), state.getSymTable().lookup("x"));
        new ReadFileStmt(file, "x").execute(state);
        assertEquals(new IntValue(0), state.getSymTable().lookup("x"));
        new CloseRFileStmt(file).execute(state);

    }
    @Test
    void variableNotDeclaredTest() throws MyException{
        new OpenRFileStmt(file).execute(state);
        var ex=new ReadFileStmt(file,"y");
        assertThrows(StatementException.class,()->ex.execute(state));
        new CloseRFileStmt(file).execute(state);
    }
    @Test
    void varNotIntTest() throws MyException{
        new OpenRFileStmt(file).execute(state);
        new VarDeclStmt("y", new BoolType()).execute(state);
        var ex=new ReadFileStmt(file, "y");
        assertThrows(StatementException.class,()->ex.execute(state));
        new CloseRFileStmt(file).execute(state);
    }
    @Test
    void fileNotOpenTest() throws MyException{
        var ex= new ReadFileStmt(file, "x");
        assertThrows(StatementException.class,()->ex.execute(state));
    }
    @Test
    void lineNotIntTest() throws MyException,Exception{
        Path f = dir.resolve("numbers.in");
        Files.writeString(f, "a\n");
        new OpenRFileStmt(file).execute(state);
        var ex=new ReadFileStmt(file, "x");

        assertThrows(StatementException.class,()->ex.execute(state));
        new CloseRFileStmt(file).execute(state);

    }

}