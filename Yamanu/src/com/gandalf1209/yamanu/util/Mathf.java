package com.gandalf1209.yamanu.util;

import java.util.Random;

/**
 * Mathematical Shortcuts
 * @author Gandalf1209
 *
 */
public class Mathf {

	/**
	 * Basically the Pythagorean Theorem, finding the absolute
	 * distance between 2 points
	 * @param x Point 1
	 * @param y Point 2
	 * @return Integer
	 */
	public static int length(int x, int y) {
		return (int) Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Generates a random number from 0-num, the parameter required
	 * @param max Max Number
	 * @return Integer (Random Number)
	 */
	public static int randNum(int max) {
		return new Random().nextInt(max);
	}
	
	public static int knifeParty() {
		int num = randNum(9999);
		return randNum(num);
	}
	
	/**
	 * Returns the Surface Area of a square
	 * @param l Length
	 * @return Integer
	 */
	public static int squareSA(int l) {
		return (int) Math.pow(l, 2);
	}
	
	/**
	 * Returns the Perimeter of a square
	 * @param l Length
	 * @return Integer
	 */
	public static int squarePerim(int l) {
		return l * 4;
	}
	
	/**
	 * Returns the Surface Area of a rectangle
	 * @param l Length
	 * @param w Width
	 * @return Integer
	 */
	public static int rectSA(int l, int w) {
		return w * l;
	}
	
	/**
	 * Returns the Perimeter of a rectangle
	 * @param l Length
	 * @param w Width
	 * @return Integer
	 */
	public static int rectPerim(int l, int w) {
		return (l * 2) + (w * 2);
	}
	
	/**
	 * Returns the Surface Area of a circle
	 * @param r Radius
	 * @return Float
	 */
	public static float circleSA(int r) {
		return (float) ((float) Math.pow(r, 2) * Math.PI);
	}
	
	/**
	 * Returns the Circumference of a circle
	 * @param r Radius
	 * @return Float
	 */
	public static float circleCirc(int r) {
		return (float) ((float) 2 * r * Math.PI);
	}
	
	/**
	 * Returns the Perimeter of a triangle
	 * @param a Side 1
	 * @param b Side 2
	 * @param c Side 3
	 * @return Integer
	 */
	public static int triPerim(int a, int b, int c) {
		return a + b + c;
	}
	
	/**
	 * Returns the Surface Area of a triangle
	 * (Returns in float because of division in formula)
	 * @param b Base
	 * @param h Height
	 * @return Float
	 */
	public static float triSA(int b, int h) {
		return (b * h) / 2;
	}
	
}
