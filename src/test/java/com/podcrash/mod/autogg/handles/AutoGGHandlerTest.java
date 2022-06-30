package com.podcrash.mod.autogg.handles;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.regex.Pattern;


public class AutoGGHandlerTest extends TestCase {
    private final String[] primaryGGStrings = {"gg", "GG", "gf", "Good Game", "Good Fight", "Good Round! :D"};
    
    @Test
    public void testSetMenuItems( ){
        Pattern p = Pattern.compile("localhost");
        System.out.println(p.matcher("localhost").matches());
        
    }
}