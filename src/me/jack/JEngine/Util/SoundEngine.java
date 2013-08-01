package me.jack.JEngine.Util;

import org.newdawn.slick.Sound;

import java.util.HashMap;

/**
 * Author: Jack Date: 18/04/13
 */
public class SoundEngine {
    private static SoundEngine instance;

    private HashMap<String, Sound> sounds = new HashMap<String, Sound>();

    private boolean disabled = false;

    public static SoundEngine getInstance() {
        if (instance == null) {
            return instance = new SoundEngine();
        } else {
            return instance;
        }
    }

    public void addSound(String key, Sound sound) {
        sounds.put(key, sound);
    }


    public Sound getSound(String key) {
        if (sounds.containsKey(key)) {
            return sounds.get(key);
        } else {
            System.out.println("Error: The sound " + key + " does not exist");
            return null;
        }
    }

    public void setDisabled(boolean disable) {
        if (disable)
            System.out.println("Disabling sound");
        else
            System.out.println("Enabling sound");
        this.disabled = disable;
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void play(String key) {
        if (disabled)
            return;
        if (sounds.containsKey(key)) {
            sounds.get(key).play();
        } else {
            System.out.println("Error: The sound " + key + " does not exist");
        }

    }

}
