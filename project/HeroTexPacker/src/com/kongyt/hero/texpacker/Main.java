package com.kongyt.hero.texpacker;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] inputs = {
				"hero_common",
				"hero_loading",
				"hero_menu",
			};
			
			String input = "E:/git¿â/hero/imagepacker/input/", output = "E:/git¿â/hero/imagepacker/output/", packFileName = "hero";
		
			Settings settings = new Settings();
			settings.filterMin = TextureFilter.Linear;
			settings.filterMag = TextureFilter.Linear;
			settings.stripWhitespaceX = true;
			settings.stripWhitespaceY = true;
			settings.ignoreBlankImages = false;
			settings.jpegQuality = 1f;
			settings.maxWidth = 1024;

			for (int i = 0; i < inputs.length; i++) {
				String tmp = inputs[i];
				TexturePacker2.process(settings, input + tmp, output + tmp, tmp);
			}
	}

}
