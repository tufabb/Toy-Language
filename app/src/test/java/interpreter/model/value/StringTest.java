package interpreter.model.value;

import interpreter.model.type.StringType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StringTest {

    @Test
    void defaultValue(){
        var a=new StringType();
        assertEquals(new StringValue(""),a.defaultValue());

    }
    @Test
    void areEqualAndHashCode(){
        StringValue s1=new StringValue("salut");
        StringValue s2=new StringValue("salut");
        assertEquals(s1,s2);
        assertEquals(s1.hashCode(),s2.hashCode());
    }
    @Test
    void areNotEqual(){
        StringValue s1=new StringValue("salut");
        StringValue s2=new StringValue("Salut");
        assertNotEquals(s1,s2);
    }

}