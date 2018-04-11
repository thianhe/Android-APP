package com.paperscissorsstonegame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thian He on 11/4/2018.
 */

public class NewMethodTest {
    private NewMethod cp;
    @Before
    public void setUp(){
        cp = new NewMethod();
    }
    @After
    public void tearDown(){
        cp = null;
    }
    @Test
    public void testGetComputerPlay(){
        assertEquals("剪刀",cp.getComputerPlay(1));
        assertEquals("石頭",cp.getComputerPlay(2));
        assertEquals("布",cp.getComputerPlay(3));
    }

    @Test
    public void testGeTResult(){
        assertEquals("判定輸贏：雙方平手！",cp.getResult(1,1));
        assertEquals("判定輸贏：很可惜，你輸了！",cp.getResult(1,2));
        assertEquals("判定輸贏：恭喜，你贏了！",cp.getResult(1,3));

        assertEquals("判定輸贏：恭喜，你贏了！",cp.getResult(2,1));
        assertEquals("判定輸贏：雙方平手！",cp.getResult(2,2));
        assertEquals("判定輸贏：很可惜，你輸了！",cp.getResult(2,3));

        assertEquals("判定輸贏：很可惜，你輸了！",cp.getResult(3,1));
        assertEquals("判定輸贏：恭喜，你贏了！",cp.getResult(3,2));
        assertEquals("判定輸贏：雙方平手！",cp.getResult(3,3));
    }
}
