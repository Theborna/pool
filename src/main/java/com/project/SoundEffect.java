package com.project;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * This enum encapsulates all the sound effects of a game, so as to separate the
 * sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundEffect.init() to
 * pre-load all the
 * sound files, so that the play is not paused while loading the file for the
 * first time.
 * 4. You can use the static variable SoundEffect.volume to mute the sound.
 */
public enum SoundEffect {
   HIT("sounds/hit.wav"), // A Ball being hit
   SINK("sounds/sink.wav"), // A Ball falling in a Pocket
   QUE("sounds/queue.wav"); // The queue Ball being hit.

   // Nested class for specifying volume
   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }

   public static Volume volume = Volume.LOW;
   private Clip clip;

   SoundEffect(String soundFileName) {
      try {
         File dot = new File(".");
         String soundFileLocation = dot.getCanonicalPath() + "/" + soundFileName;
         File soundFile = new File(soundFileLocation);
         if (soundFile.exists()) {
            System.out.println("File exists");
            if (soundFile.canRead())
               System.out.println("Can Read...");
         }
         URL url = this.getClass().getResource(soundFileName);
         System.out.println(" URL = " + url.getPath());
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         clip = AudioSystem.getClip();
         clip.open(audioInputStream);
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(0);
      }
   }

   public void play() {
      if (volume != Volume.MUTE) {
         if (clip.isRunning())
            clip.stop(); // Stop the player if it is still running
         clip.setFramePosition(0); // rewind to the beginning
         clip.start(); // Start playing
      }
   }

   static void init() {
      values();
   }

}
