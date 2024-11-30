package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TupleTest {
    @Test
    void ensureTwoTuplesWithSameDataEquals(){
        Tuple tuple1 = new Tuple(123456789, 1);
        Tuple tuple2 = new Tuple(123456789, 1);
        assertEquals(tuple1, tuple2);
    }
    @Test void ensureTuplesWithDifferentIDsNotEquals() {
        Tuple tuple1 = new Tuple(123456789, 1);
        Tuple tuple2 = new Tuple(987654321, 1);
        assertNotEquals(tuple1, tuple2);
    }
    @Test void ensureTuplesWithDifferentNumbersOfPropertiesNotEquals(){
        Tuple tuple1 = new Tuple(123456789, 1);
        Tuple tuple2 = new Tuple(123456789, 2);
        assertNotEquals(tuple1, tuple2);
    }

    @Test void ensureTupleDoesNotEqualNull() {
        Tuple tuple1 = new Tuple(123456789, 1);
        assertNotEquals(tuple1, null);
    }

    @Test void ensureTupleDoesNotEqualOtherObject() {
        Tuple tuple1 = new Tuple(123456789, 1);
        assertNotEquals(tuple1, new Object());
    }

    @Test void ensureTheSameObjectIsEqual() {
        Tuple tuple1 = new Tuple(123456789, 1);
        assertEquals(tuple1,tuple1);
    }
    @Test void ensureCloneWorks(){
        Tuple tuple1 = new Tuple(123456789, 1);
        Tuple clone = tuple1.clone();
        assertEquals(tuple1, clone);
    }
}