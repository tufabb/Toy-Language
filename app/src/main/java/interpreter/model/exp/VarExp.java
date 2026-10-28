package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.IValue;

public record VarExp(String var) implements IExp {
    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {
//        if(symTable.isDefined(var))
//            return symTable.lookup(var);
//        else throw new ExpressionException("variable is not defined");

        return symTable.lookup(var);
    }

    @Override
    public String toString() {
        return var;
    }
}
