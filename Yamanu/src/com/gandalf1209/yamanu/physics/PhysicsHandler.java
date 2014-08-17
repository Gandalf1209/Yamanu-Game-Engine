package com.gandalf1209.yamanu.physics;

import com.gandalf1209.yamanu.game.GameObject;

public class PhysicsHandler {

	public Thread pThread;
	
	public PhysicsHandler() {
		pThread = new Thread("Physics Handler Thread") {
			public void run() {
				while (true) {
					runPhysics();
				}
			}
		};
		pThread.start();
	}
	
	private static void runPhysics() {
		for (int i = 0; i < GameObject.getCount(); i++) {
			GameObject current = null;
			if (GameObject.getObjects().get(i).isSmart()) {
				current = GameObject.getObjects().get(i);
			}
			if (current != null) {
				for (int j = 0; j < GameObject.getCount(); j++) {
					GameObject target = GameObject.getObjects().get(j);
					if (i != j && target.canCollide() && current.canCollide()) {
						if (current.getX() + current.getWidth() > target.getX()
								&& current.getX() - current.getWidth() < target.getX()
								&& current.getY() + current.getHeight() > target.getY()
								&& current.getY() - current.getHeight() < target.getY()) {
							current.setSpeed(current.getOriginalSpeed() - (target.getFriction() / 10));
							target.setSpeed(current.getSpeed());
							target.move(current.getXDir(), current.getYDir());
						} else {
							current.setSpeed(current.getOriginalSpeed());
						}
					}
				}
			}
		}
	}
	
}
