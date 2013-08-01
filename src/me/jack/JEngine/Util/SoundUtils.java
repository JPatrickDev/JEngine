package me.jack.JEngine.Util;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundUtils {

	/**
	 * Loads a Sound
	 * @param res The sound to load
	 * @return The loaded sound, or null if there is an error loading
	 */
	public static Sound loadSound(String res) {
		Sound s = null;
		try {
			s = new Sound(res);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * Plays a sound
	 * @param s The sound to play
	 * @param pitch The pitch to play the Sound at
	 * @param volume The volume to play the Sound at
	 */
	public static void playSound(Sound s, float pitch, float volume) {
		s.play(pitch, volume);
	}
	
	/**
	 * Plays a sound
	 * @param s The path to the sound to play
	 * @param pitch The pitch to play the Sound at
	 * @param volume The volume to play the Sound at
	 */
	public static void playSound(String s, float pitch, float volume) {
		playSound(loadSound(s), pitch, volume);
	}
}
