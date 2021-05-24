import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SILab2Test {


    @Test
    public void test1() {
        //Every branch / Multiple Condition if(i < timesList.size()) -> T, if(hr < 0 || hr > 24)->TX
        //Multiple condition if(hr < 0)->T
        Time time = new Time(-3,10,20);
        List<Time> TimesList = new ArrayList<>();
        TimesList.add(time);
        try {
            SILab2.function(TimesList);
        } catch (Exception e){
            assertEquals(e.getMessage(),"The hours are smaller than the minimum");
        }
    }

    @Test
    public void test2() {
        //Everybranch/ Multiple conditions if(hr < 0 || hr > 24) -> FT
        //Multiple condition if(hr<0) -> F
        Time Time = new Time(33,10,20);
        List<Time> TimesList = new ArrayList<>();
        TimesList.add(Time);
        try {
            SILab2.function(TimesList);
        } catch (Exception e){
            assertEquals(e.getMessage(),"The hours are grater than the maximum");
        }
    }

    @Test
    public void test3() {
        //Every branch/ multiple condition if(hr<0 || hr > 24) -> FF
        //Multiple condition if(min<0 || min>59) -> FT
        //Multiple condition if(hr<24) ->T, if(min < 0 || min > 59) -> TX
        Time Time = new Time(13,-3,25);
        Time Time2 = new Time(13,85,-3);
        List<Time> TimesList = new ArrayList<>();
        TimesList.add(Time);
        TimesList.add(Time2);
        try {
            SILab2.function(TimesList);
        } catch (Exception e){
            assertEquals(e.getMessage(),"The minutes are not valid!");
        }
    }

    @Test
    public void test4() {
        //Every branch/Multiple condition if(min < 0 || min > 59) FF
        // Multiple condition if(sec >= 0 && sec <= 59) -> TT
        Time Time = new Time(13,2,21);
        List<Time> TimesList = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        TimesList.add(Time);
        result = SILab2.function(TimesList);
        assertEquals(result.get(0).intValue(),Time.getHours()*3600 + Time.getMinutes()*60 + Time.getSeconds());
    }

    @Test
    public void test5() {
        //Every branch & Multiple condition if(sec >= 0 && sec <= 59) -> TF
        //Multiple condition if(sec >= 0 && sec <= 59) FX
        //Multiple condition if(hr == 24 && min == 0 && sec == 0) ->FXX
        Time Time = new Time(13,2,76);
        Time Time2 = new Time(13,0,-3);
        List<Time> TimesList = new ArrayList<>();
        TimesList.add(Time);
        TimesList.add(Time2);
        try {
            SILab2.function(TimesList);
        } catch (Exception e){
            assertEquals(e.getMessage(),"The seconds are not valid");
        }
    }

    @Test
    public void test6() {
        //Every branch & Multiple Condition if(hr < 24) -> F
        // Multiple condition if(hr == 24 && min == 0 && sec == 0) -> TTT
        Time Time = new Time(24,0,0);
        List<Time> TimesList = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        TimesList.add(Time);
        result = SILab2.function(TimesList);
        assertEquals(result.get(0).intValue(),Time.getHours()*3600 + Time.getMinutes()*60 + Time.getSeconds());
    }

    @Test
    public void test7() {
        //Every branch & Multiple condition if(hr == 24 && min == 0 && sec == 0) -> TTF,
        //and mulitple condition for if(hr == 24 && min == 0 && sec == 0) -> TFX
        Time Time = new Time(24,2,0);
        Time Time2 = new Time(24,0,-3);
        Time Time3 = new Time(24,85,1);
        List<Time> TimesList = new ArrayList<>();
        TimesList.add(Time);
        TimesList.add(Time2);
        TimesList.add(Time3);
        try {
            SILab2.function(TimesList);
        } catch (Exception e){
            assertEquals(e.getMessage(),"The time is greater than the maximum");
        }
    }
    //Multiple Condtion кои не беа опфатени во претходниот критериот Every branch од test1-test7
    @Test
    public void test8() {
        //i < timesList.size() --> F with Multiple condition
        List<Time> TimesList = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        result = SILab2.function(TimesList);
        assertEquals(result.size(),0);
    }
}

