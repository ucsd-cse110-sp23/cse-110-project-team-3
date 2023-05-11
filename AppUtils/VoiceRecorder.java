import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class VoiceRecorder {
    public TargetDataLine targetDataLine;
    public AudioFormat audioFormat;

    

    VoiceRecorder(){
        audioFormat = getAudioFormat();
    }

    private AudioFormat getAudioFormat() {
        float sampleRate = 44100;
        int sampleSizeInBits = 16;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = false;
    
        return new AudioFormat(
          sampleRate,
          sampleSizeInBits,
          channels,
          signed,
          bigEndian
        );
    }

    public void startListening() {

        Thread t = new Thread(
            () -> {
            try {
                DataLine.Info dataLineInfo = new DataLine.Info(
                    TargetDataLine.class,
                    audioFormat
                );
                targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
                targetDataLine.open(audioFormat);
                targetDataLine.start();

                AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);

                File audioFile = new File("UserData/recording.wav");
                AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, audioFile);
            } catch (Exception ex) {
            ex.printStackTrace();
            }
        }
        );
        t.start();
    }

    // stops recording when user clicks pause
    public void stopListening() {
        targetDataLine.stop();
        targetDataLine.close();
    }

}
