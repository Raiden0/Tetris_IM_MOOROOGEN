package com.esiea.mooroogen;

import java.awt.Color;
import java.util.Random;

import map.Window;

public class Main {
	
	public static Window win;
	
	public static void main(String[] args) {
		win = new Window();
	}
	
	public static Window getFrame(){
		return win;
	}
	public static int rand (int min, int max){
		return (int) (((Math.random())*max)+ min);
	}
	public static int rand(int max){
		return rand(1,max);
	}
	public static Color randomColor(){
		Random randomGenerator = new Random();
		int red	=randomGenerator.nextInt(255);
		int green=randomGenerator.nextInt(255);
		int blue=randomGenerator.nextInt(255);
		
		return new Color(red,green,blue).darker();
	}

}
