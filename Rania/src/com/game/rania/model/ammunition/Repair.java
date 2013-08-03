package com.game.rania.model.ammunition;

import com.badlogic.gdx.graphics.Color;
import com.game.rania.model.animator.AnimatorColor;
import com.game.rania.model.animator.AnimatorFloat;
import com.game.rania.model.animator.AnimatorVector2;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Repair extends Ammunition
{

  protected static final float repairTime = 2.0f;
  protected Object             attacker, target;

  public Repair(Object attacker, Object target, Color repairColor)
  {
    super(repairTime, RegionID.REPAIR, 0, 0);
    this.attacker = attacker;
    this.target = target;
    color.set(repairColor);
    zIndex = 110;
    vAlign = Align.CENTER;
    addAnimator(new AnimatorFloat(angle, 360.0f, 0.0f, repairTime));
    addAnimator(new AnimatorVector2(scale, 0, 0, repairTime));
    addAnimator(new AnimatorColor(color, 0, 0, repairTime));
  }

  @Override
  public void update(float delta)
  {
    super.update(delta);
    position.set(target.position);
  }
}
