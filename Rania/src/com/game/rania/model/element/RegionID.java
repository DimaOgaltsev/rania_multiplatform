package com.game.rania.model.element;

public enum RegionID {
	NONE,
	
	//static elements
	BACKGROUND_MENU,
	BACKGROUND_SPACE,
	BACKGROUND_STARS,
	STAR,
	RADAR,
	
	//nebulas
	NEBULA_0,
	NEBULA_1,
	NEBULA_2,
	NEBULA_3,
	NEBULA_4,
	NEBULA_5,
	NEBULA_6,
	NEBULA_7,

	//planets
	PLANET_0,
	PLANET_1,
	PLANET_2,
	PLANET_3,
	PLANET_4,
	PLANET_5,
	PLANET_6,
	PLANET_7,
	PLANET_8,
	PLANET_9,
	PLANET_10,
	PLANET_11,
	PLANET_12,
	PLANET_13,
	PLANET_14,
	PLANET_15,
	PLANET_16,
	PLANET_17,
	
	//dynamic elements
	SHIP,
	
	//gui
	BTNLOGIN_OFF,
	BTNLOGIN_ON,
	BTNREG_OFF,
	BTNREG_ON,
	BTNEXIT_OFF,
	BTNEXIT_ON,
	EDIT_OFF,
	EDIT_ON,
	BTNBACK_OFF,
	BTNBACK_ON,
	
	//emblems
	MORT,
	MORT_ACT,
	ARAHNID,
	ARAHNID_ACT,
	ERBO,
	ERBO_ACT,
	GURDIN,
	GURDIN_ACT,
	SIKTAN,
	SIKTAN_ACT,
	DEMIURG,
	DEMIURG_ACT;
	
	public static RegionID fromInt(int pos){
		return RegionID.values()[pos];
	}
	
	public static int toInt(RegionID id){
		return id.ordinal();
	}
}
