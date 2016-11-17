package com.kongyt.hero;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.kongyt.khero.KHero;
import com.kongyt.khero.managers.GM;
import com.kongyt.khero.managers.LM;
import com.kongyt.khero.net.Net;
import com.kongyt.khero.utils.SV;

import android.os.Bundle;

public class MainActivity extends AndroidApplication {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GM.instance().registerActivity(this);
		
		
		Game game = new KHero();
		
		this.initialize(game, true);
		
		LM.instance().setLogLevel(SV.LOG_DEBUG);
		
		Net.instance().conn("47.88.23.214", 8888);

	}
}
