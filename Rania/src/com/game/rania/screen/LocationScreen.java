package com.game.rania.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.game.rania.RaniaGame;
import com.game.rania.controller.MainController;
import com.game.rania.controller.ClientController;
import com.game.rania.controller.ShipController;
import com.game.rania.model.ParallaxLayer;
import com.game.rania.model.ParallaxObject;
import com.game.rania.model.Player;
import com.game.rania.model.Radar;
import com.game.rania.model.Star;
import com.game.rania.model.Text;
import com.game.rania.model.User;
import com.game.rania.model.Location;
import com.game.rania.model.Planet;
import com.game.rania.model.PlanetSprite;
import com.game.rania.model.element.Font;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.ui.Message;
import com.game.rania.model.ui.PressedButton;
import com.game.rania.model.ui.TouchAction;
import com.game.rania.view.MainView;

public class LocationScreen implements Screen{
	
	private MainView view = null;
	private MainController controller = null;
	private ClientController nList = null;
	private PressedButton btnMenu = null;
	private PressedButton btnCancel = null;
	private PressedButton btnDisconnect = null;
	private float Width;
	private float Height;
	private float halfWidth;
	private float halfHeight;
	
	public LocationScreen(){
		view = RaniaGame.mView;
		controller = RaniaGame.mController;
		nList = RaniaGame.mClient;
		controller.init();
	}

	@Override
	public void show() {
		Width = view.getCamera().getWidth();
		Height = view.getCamera().getHeight();
		halfWidth = Width/2.0f;
		halfHeight = Height/2.0f;
		view.loadTexture("data/sprites/star.png", RegionID.STAR);
		for (int i = 0; i < 18; i++)
			view.loadTexture("data/sprites/planets.png", RegionID.fromInt(RegionID.PLANET_0.ordinal() + i), i % 5 * 204, i / 5 * 204, 204, 204);
		
		for (int i = 0; i < 8; i++)
			view.loadTexture("data/backgrounds/nebulas.png", RegionID.fromInt(RegionID.NEBULA_0.ordinal() + i), i % 4 * 256, i / 4 * 256, 256, 256);

		view.loadTexture("data/sprites/radar.png", RegionID.RADAR);
		view.loadTexture("data/sprites/SpaceShip.png", RegionID.SHIP);
		view.loadTexture("data/backgrounds/space.png", RegionID.BACKGROUND_SPACE);
		view.getTexture(RegionID.BACKGROUND_SPACE).setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		view.loadTexture("data/backgrounds/stars.png", RegionID.BACKGROUND_STARS);
		view.getTexture(RegionID.BACKGROUND_STARS).setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		Player player = nList.getPlayerData();
		Location location = RaniaGame.mClient.getLocation(player.idLocation);
		if (location == null)
			return;
		
		Radar radar = new Radar(player,
								(view.getHUDCamera().getWidth() - view.getTextureRegion(RegionID.RADAR).getRegionWidth()) * 0.5f,
								(view.getHUDCamera().getHeight() - view.getTextureRegion(RegionID.RADAR).getRegionHeight()) * 0.5f,
								1500.0f, 1.0f);
		
		Star star = new Star(RegionID.STAR, location.starRadius);
		radar.addObject(star);
		controller.addStaticObject(new ParallaxLayer(RegionID.BACKGROUND_SPACE, 250, 300, -0.35f));
		controller.addStaticObject(new ParallaxLayer(RegionID.BACKGROUND_STARS, -150, 0, -0.25f));
		controller.addStaticObject(new ParallaxObject(RegionID.NEBULA_3, 500, 500, 45, 3, 3, -0.4f));
		controller.addStaticObject(new ParallaxObject(RegionID.NEBULA_5, -500, 500, -45, 5, 5, -0.4f));
		controller.addStaticObject(new ParallaxObject(RegionID.NEBULA_6, 500, -500, 0, 4, 4, -0.4f));
		controller.addStaticObject(new ParallaxObject(RegionID.NEBULA_7, -500, -500, 200, 2, 2, -0.4f));

		controller.addStaticObject(star);

		nList.updateCurrentLocation();
		for (Planet planet : nList.getPlanets().values()) {
			PlanetSprite pSprite = new PlanetSprite(planet);
			radar.addObject(pSprite);
			controller.addDynamicObject(pSprite);
		}

		for (User user : nList.getUsers().values()) {
			radar.addObject(user);
			controller.addDynamicObject(user);
		}

		controller.addDynamicHUDObject(radar);
		controller.setPlayer(player);
		controller.addProcessor(new ShipController(player));
		
		view.loadTexture("data/gui/fs_menu.png", RegionID.BTN_FS_MENU);
		view.loadTexture("data/gui/fs_back.png", RegionID.BTN_FS_BACK);
		view.loadTexture("data/gui/fs_exit.png", RegionID.BTN_FS_EXIT);
		btnDisconnect = new PressedButton(RegionID.BTN_FS_EXIT,
				  RegionID.BTN_FS_EXIT,
				  halfWidth * 0.0f, halfHeight * 0.137f,
				  new TouchAction() {
					@Override
					public void execute(boolean touch) {	
								dispose();
								RaniaGame.mGame.setScreen(new MainMenu());
							}});
		btnMenu = new PressedButton(RegionID.BTN_FS_MENU,
				  RegionID.BTN_FS_MENU,
				  halfWidth * 0.9333f, -halfHeight * 0.8815f,
				  new TouchAction() {
					@Override
					public void execute(boolean touch) {	
								btnDisconnect.visible = true;
								btnCancel.visible = true;
								btnMenu.visible = false;
							}});
		btnCancel = new PressedButton(RegionID.BTN_FS_BACK,
				  RegionID.BTN_FS_BACK,
				  halfWidth * 0.0f, -halfHeight * 0.137f,
				  new TouchAction() {
					@Override
					public void execute(boolean touch) {	
								btnDisconnect.visible = false;
								btnCancel.visible = false;
								btnMenu.visible = true;
							}});
		btnDisconnect.visible = false;
		btnCancel.visible = false;
		btnMenu.visible = true;
		controller.addStaticHUDObject(btnMenu);
		controller.addStaticHUDObject(btnDisconnect);
		controller.addStaticHUDObject(btnCancel);
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		controller.clear();
		view.clear();
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		controller.update(deltaTime);
		view.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}
}
