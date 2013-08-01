package me.jack.JEngine.AI.tests;

import me.jack.JEngine.JEngine;

/**
 * Author: Jack
 * Date: 19/07/13
 */
public class Main {


    public static void main(String[] args){
        JEngine.start(new AStarTests("AI Tests"), 800, 600);
    }
}
