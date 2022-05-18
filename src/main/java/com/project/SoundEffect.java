package com.project;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public enum SoundEffect {
   HIT("sounds/hit.wav"), // A Ball being hit
   SINK("sounds/sink.wav"), // A Ball falling in a Pocket
   QUE("sounds/queue.wav"); // The queue Ball being hit.

   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }

   public static Volume volume = Volume.MEDIUM;
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
         clip.setFramePosition(0); // rewind to the beginning
         clip.start(); // Start playing
      }
   }

}