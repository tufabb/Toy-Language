package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;

public record WhileStmt(IExp cond,IStmt body) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException{
        var symTable=state.getSymTable();
        var heap=state.getHeap();
        var exeStack=state.getExeStack();
        IValue value=cond.eval(symTable,heap);
        if(! (value instanceof BoolValue bool))
            throw new StatementException("while: "+ cond+" is not of type boolean");
        if(bool.b())
            {
                exeStack.push(this);
                exeStack.push(body);
            }

        return state;
    }
    @Override
    public String toString(){return "(while ("+cond+") "+body+")";}
}
