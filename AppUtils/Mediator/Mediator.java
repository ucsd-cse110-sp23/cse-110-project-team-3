package Mediator;
// holds state of question and answer, handles voice recording and api calls

import VoiceRecorder.VoiceRecorder;
import GPT.*;
import Whisper.*;

public class Mediator {

    /*
     * The mediator is aware of the following values:
     *  - voice recording
     *  - user's question and gpt's answer
     */

    // voice recording variables
    private VoiceRecorder voiceRecorder;
    private boolean isRecording;

    // api variables
    IGPT gpt;
    IWhisper whisper;

    // question and answer variables
    private String question;
    private String answer;

    // new question and answer variables
    private String newQuestion;
    private String newAnswer;

    public Mediator() {

        isRecording = false;
        voiceRecorder = new VoiceRecorder();

        gpt = new GPT();
        whisper = new Whisper();

        question = "User inputted question \n";
        answer = "Answer to user question";
        
    }

    // voice recording functions
    public boolean isRecording() {
        return isRecording;
    }
    public void switchIsRecording() {
        isRecording = !isRecording;
    }
    public void startRecording() {
        voiceRecorder.startListening();
    }
    public void stopRecording() {
        voiceRecorder.stopListening();
    }

    // question and answer functions
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String s) {
        question = s;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String s) {
        answer = s;
    }

    public String getNewQuestion() {
        return newQuestion;
    }

    // api calls
    public void generateQuestion() {
        try {
            newQuestion = whisper.generate("UserData/recording.wav");
        } catch (Exception e) {
            //
        }
    }
    public void generateAnswer() {
        try {
            newAnswer = gpt.generate(newQuestion);
        } catch (Exception e) {
            //
        }
    }

    // ui interactions
    public void updateQuestionAndAnswer() {
        question = newQuestion;
        answer = newAnswer;
    }

}
