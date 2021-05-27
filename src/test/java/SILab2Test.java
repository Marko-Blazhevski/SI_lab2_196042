import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SILab2Test {

    private final SILab2 lab2 = new SILab2();

    @Test
    void everyBranchTest(){
        RuntimeException ex;

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(-1,0,0))));
        assertEquals(ex.getMessage(), "The hours are smaller than the minimum");

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(25,0,0))));
        assertEquals(ex.getMessage(), "The hours are grater than the maximum");

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(0,-1,0))));
        assertEquals(ex.getMessage(), "The minutes are not valid!");

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(0,0,-1))));
        assertEquals(ex.getMessage(), "The seconds are not valid");

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(24,1,1))));
        assertEquals(ex.getMessage(), "The time is greater than the maximum");

        assertEquals(List.of(3630,86400) , lab2.function(List.of(new Time(1,0,30) , new Time(24,0,0))));

    }


    @Test
    void multipleConditionTest(){
        RuntimeException ex;

        // if (hr < 0 || hr > 24) 7 linija
        // T || X ; F || T ; F || F

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(-1,0,0))));
        assertEquals(ex.getMessage(), "The hours are smaller than the minimum");

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(25,0,0))));
        assertEquals(ex.getMessage(), "The hours are grater than the maximum");

        assertEquals(List.of(60) , lab2.function(List.of(new Time(0,1,0))));


        // if (min < 0 || min > 59) 12 linija
        // T || X ; F || T ; F || F

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(1,-1,0))));
        assertEquals(ex.getMessage(), "The minutes are not valid!");

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(1,70,0))));
        assertEquals(ex.getMessage(), "The minutes are not valid!");

        assertEquals(List.of(1800) , lab2.function(List.of(new Time(0,30,0))));


        // if (sec >= 0 && sec <= 59) 14 linija
        // F && X; T && F; T && T

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(0,0,-1))));
        assertEquals(ex.getMessage(), "The seconds are not valid");

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(0,0,70))));
        assertEquals(ex.getMessage(), "The seconds are not valid");

        assertEquals(List.of(30) , lab2.function(List.of(new Time(0,0,30))));


        // else if (hr == 24 && min == 0 && sec == 0) 17 linija
        // F && X && X; T && F && X; T && T && F; T && T && T;

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(-10,0,0))));
        assertEquals(ex.getMessage(), "The hours are smaller than the minimum");

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(24,10,0))));
        assertEquals(ex.getMessage(), "The time is greater than the maximum");

        ex = assertThrows(RuntimeException.class , () -> lab2.function(List.of(new Time(24,0,10))));
        assertEquals(ex.getMessage(), "The time is greater than the maximum");

        assertEquals(List.of(86400) , lab2.function(List.of(new Time(24,0,0))));

    }
}