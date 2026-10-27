package interpreter.model.type;

public record BoolValue(boolean b) implements IValue {
    @Override
    public IType getType(){
        return new BoolType();
    }
    public String toString(){
        return Boolean.toString(b);
    }
}
