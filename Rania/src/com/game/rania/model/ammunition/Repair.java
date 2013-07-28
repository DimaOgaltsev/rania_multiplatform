package com.game.rania.model.ammunition;

import com.badlogic.gdx.graphics.Color;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Repair extends Ammunition{

	protected static final float repairTime = 2.0f;
	protected Object attacker, target;
	
	public Repair(Object attacker, Object target, Color repairColor){
		super(repairTime, RegionID.REPAIR, 0, 0);
		this.attacker = attacker;
		this.target = target;
		color.set(repairColor);
		vAlign = Align.CENTER;
	}
	
	protected static final float rotateTime = 2.0f;
	
	@Override
	public void update(float delta){
		super.update(delta);
		position.set(target.position);
		if (region != null) {
			if (dTime < rotateTime) {
				float progress = dTime/rotateTime;
				float rotate = 3.14159f*2.0f*progress;
				color.a = (1.0f-progress);
				scale.x = (1.0f-progress);
				scale.y = (1.0f-progress);
				angle = (float)Math.toDegrees(rotate);
			}
		}
	}
}