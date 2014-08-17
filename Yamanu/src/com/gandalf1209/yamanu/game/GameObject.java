package com.gandalf1209.yamanu.game;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class GameObject {

	private int x;
	private int y;
	private int w;
	private int h;
	private boolean canCollide;
	private int friction;
	private boolean smart;
	private Image img;
	
	private static int count = 0;
	private static List<GameObject> objects = new ArrayList<GameObject>();
	
	public GameObject(int x, int y, int w, int h, boolean canCollide, int friction, boolean smart) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.canCollide = canCollide;
		this.friction = friction;
		this.smart = smart;
		
		count++;
		objects.add(this);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return w;
	}

	public void setWidth(int w) {
		this.w = w;
	}

	public int getHeight() {
		return h;
	}

	public void setHeight(int h) {
		this.h = h;
	}

	public boolean canCollide() {
		return canCollide;
	}

	public void setCanCollide(boolean canCollide) {
		this.canCollide = canCollide;
	}

	public int getFriction() {
		return friction;
	}

	public void setFriction(int friction) {
		this.friction = friction;
	}

	public boolean isSmart() {
		return smart;
	}

	public void setSmart(boolean smart) {
		this.smart = smart;
	}
	
	public void setImage(Image img) {
		this.img = img;
	}
	
	public Image getImage() {
		return this.img;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		GameObject.count = count;
	}

	public static List<GameObject> getObjects() {
		return objects;
	}

	public static void setObjects(List<GameObject> objects) {
		GameObject.objects = objects;
	}
	
}
