import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class Recorder {
    private TargetDataLine targetDataLine;
    private AudioFormat audioFormat;

    

    Recorder(){
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

                File audioFile = new File("AppUtils/recording.wav");
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


    // public static void main(String[] args) {
    //     Recorder recorder = new Recorder();
    //     JFrame frame = new JFrame("Recorder");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setSize(300, 300);
    //     frame.setLayout(new FlowLayout());

    //     JButton startButton = new JButton("Start");
    //     JButton stopButton = new JButton("Stop");

    //     startButton.addActionListener(
    //         new ActionListener() {
    //             public void actionPerformed(ActionEvent e) {
    //                 recorder.startListening();
    //             }
    //         }
    //     );

    //     stopButton.addActionListener(
    //         new ActionListener() {
    //             public void actionPerformed(ActionEvent e) {
    //                 recorder.stopListening();
    //             }
    //         }
    //     );

    //     frame.add(startButton);
    //     frame.add(stopButton);
    //     frame.setVisible(true);
    // }
}
