package com.kongyt.khero.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kongyt.khero.common.SceneData;
import com.kongyt.khero.managers.GM;
import com.kongyt.khero.managers.RM;
import com.kongyt.khero.net.BaseModule;
import com.kongyt.khero.net.Net;
import com.kongyt.khero.utils.SV;

public class LoginScene extends BaseScene {

	private Image bg;
	
	private BitmapFont labelFont;
	private BitmapFont textFieldFont;
	
	private Label usernameL;
	private Label passwordL;
	
	private TextField usernameTF;
	private TextField passwordTF;
	
	private TextButton loginTB;
	private TextButton registerTB;
	private TextButton debugTB;
	
	BaseModule baseModule = new BaseModule();
	
	public LoginScene(){
		Net.instance().registerModule(baseModule);		
		
		TextureAtlas atlas = RM.instance().getTexAtls("hero_loading/hero_loading.atlas");
		Sprite tmp1 = atlas.createSprite("loading_bg");
		bg = new Image(tmp1);
		uiStage.addActor(bg);
		
		labelFont = new BitmapFont(Gdx.files.internal("fonts/labelFont.fnt"));
		
		LabelStyle lstyle = new LabelStyle(labelFont, new Color(1,1,1,1));
		usernameL = new Label("ÓÃ»§Ãû", lstyle);
		usernameL.setPosition(240, 240);
		uiStage.addActor(usernameL);
		
		passwordL = new Label("ÃÜ    Âë", lstyle);
		passwordL.setPosition(240, 180);
		uiStage.addActor(passwordL);
		
		
		
		textFieldFont = new BitmapFont(Gdx.files.internal("fonts/text_field_font.fnt"));
		textFieldFont.setScale(0.5f);
		
		atlas = RM.instance().getTexAtls("hero_common/hero_common.atlas");
		
		TextField.TextFieldStyle tfstyle = new TextField.TextFieldStyle();
		tfstyle.cursor = new TextureRegionDrawable(atlas.createSprite("text_field_cursor"));
		


		tfstyle.background = new TextureRegionDrawable(atlas.createSprite("text_field_bg"));
		tfstyle.font = textFieldFont;
		tfstyle.fontColor = new Color(0, 0, 0, 1);
		 
		usernameTF = new TextField("", tfstyle);
		usernameTF.setPosition(360, 240);
		usernameTF.setSize(160, 36);
		uiStage.addActor(usernameTF);
		
		passwordTF = new TextField("", tfstyle);
		passwordTF.setPasswordCharacter('*');
		passwordTF.setPasswordMode(true);
		passwordTF.setPosition(360, 180);
		passwordTF.setSize(160, 36);
		uiStage.addActor(passwordTF);

		TextButtonStyle tbstyle = new TextButtonStyle();
		tbstyle.up = new TextureRegionDrawable(atlas.createSprite("btn_bg"));
		tbstyle.font = labelFont;
		registerTB = new TextButton("×¢²á", tbstyle);
		registerTB.setSize(80, 36);
		registerTB.setPosition(200, 100);
		registerTB.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub				
				register();				
				super.clicked(event, x, y);				
			}			
		});
		uiStage.addActor(registerTB);
		
		loginTB = new TextButton("µÇÂ½", tbstyle);
		loginTB.setSize(80, 36);
		loginTB.setPosition(360, 100);
		loginTB.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub				
				login();				
				super.clicked(event, x, y);				
			}			
		});
		uiStage.addActor(loginTB);
		
		debugTB = new TextButton("Debug", tbstyle);
		debugTB.setSize(80, 36);
		debugTB.setPosition(400, 50);
		debugTB.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub				
				debug();				
				super.clicked(event, x, y);				
			}			
		});
		uiStage.addActor(debugTB);

	}
	
	
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		if(baseModule.isLogin){
			SceneData sceneData = new SceneData();
			sceneData.sceneId = SV.SCENE_MENU;
			GM.instance().changeScene(sceneData);
		}
	}



	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(uiStage);
	}


	private void register(){
		String username = usernameTF.getText();
		String password = passwordTF.getText();
		
		baseModule.sendRegisterRequest(username, password);
	}

	private void login(){
		String username = usernameTF.getText();
		String password = passwordTF.getText();
		
		baseModule.sendLoginRequest(username, password);
		

	}
	
	private void debug(){
		baseModule.sendDebugCommand("testDebug");
	}
}
