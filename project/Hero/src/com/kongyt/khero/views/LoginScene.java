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
import com.kongyt.khero.managers.RM;
import com.kongyt.khero.net.BaseModule;
import com.kongyt.khero.net.Net;

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
		
		TextureAtlas atlas = RM.instance().getTexAtls("loading/loading.atlas");
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
		
		TextField.TextFieldStyle tfstyle = new TextField.TextFieldStyle();
		Texture tex = new Texture(
				Gdx.files.internal("images/common/text_field_cursor.png"));
		TextureRegion tr = new TextureRegion(tex);
		tfstyle.cursor = new TextureRegionDrawable(tr);
		


		tex = new Texture(Gdx.files.internal("images/common/text_field_bg.png"));
		tr = new TextureRegion(tex);
		tfstyle.background = new TextureRegionDrawable(tr);
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
		tex = new Texture(Gdx.files.internal("images/common/btn_bg.png"));
		tr = new TextureRegion(tex);
		tbstyle.up = new TextureRegionDrawable(tr);
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
		
//		SceneData sceneData = new SceneData();
//		sceneData.sceneId = SV.SCENE_MENU;
//		GM.instance().changeScene(sceneData);
	}
	
	private void debug(){
		baseModule.sendDebugCommand("testDebug");
	}
}
