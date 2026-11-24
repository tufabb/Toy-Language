package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IHeap;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.value.RefValue;

public record ReadHeapExp(IExp exp) implements IExp {
    @Override
    public IValue eval(IDictionary<String, IValue> symTable, IHeap heap) throws MyException{
      if(!(exp.eval(symTable,heap) instanceof RefValue ref))
          throw new ExpressionException("is not a reference type");
      if(!heap.isDefined(ref.address()))
          throw new ExpressionException("no var with this address");
      return heap.get(ref.address());
    }
    @Override
    public String toString(){return "rh("+exp+")";}
}
