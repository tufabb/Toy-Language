package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class OpenRFileStmtTest {
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
    void addsTheFileTest() throws MyException {
        new OpenRFileStmt(file).execute(state);
        assertFalse(state.getFileTable().isEmpty());
        new CloseRFileStmt(file).execute(state);

    }
    @Test
    void nonStringExpTest() throws MyException{
        var ex=new OpenRFileStmt(new ValueExp(new BoolValue(true)));
        assertThrows(StatementException.class,()->ex.execute(state));
    }
    @Test
    void fileAlreadyOpne() throws MyException{
        new OpenRFileStmt(file).execute(state);
        var ex=new OpenRFileStmt(file);
        assertThrows(StatementException.class,()->ex.execute(state));
        new CloseRFileStmt(file).execute(state);
    }
    @Test
    void missingFile() throws MyException{
        var ex=new OpenRFileStmt(new ValueExp(new StringValue("salut")));
        assertThrows(StatementException.class,()->ex.execute(state));
    }


}