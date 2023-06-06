package Mediator;
// holds state of question and answer, handles voice recording and api calls

import VoiceRecorder.VoiceRecorder;
import GPT.*;
import Whisper.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import NameValuePair.*;

import org.bson.types.ObjectId;

public class Mediator {

    // server url
    public final String URL = "http://localhost:8101/";

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

    // user id
    ObjectId id;

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
    public void setIsRecording(boolean r) {
        isRecording = r;
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
    public void setNewAnswer(String s) {
        newAnswer = s;
    }
    public void setNewQuestion(String s) {
        newQuestion = s;
    }

    // api calls
    public void generateQuestion() {
        try {
            newQuestion = whisper.generate("UserData/recording.wav");
        } catch (Exception e) {
            //
        }
    }
    public void generateAnswer() throws IOException {
        try{
            List<NameValuePair> toQuery = new ArrayList<NameValuePair>();
            NameValuePair parameter1 = new NameValuePair("cmdType", "chatGpt");
            NameValuePair parameter2 = new NameValuePair("question", newQuestion);

            toQuery.add(parameter1);
            toQuery.add(parameter2);
            
            KeyValuePairHandler mySorter = new KeyValuePairHandler();
            String input = mySorter.getQuery(toQuery);

            TalktoServer speaker = new TalktoServer();
            newAnswer = speaker.sendAndReceive(input);
        }
        catch (Exception ex) {
            System.out.println("Server is down");
        }
    }
        /*try {
            try {
                URL url = new URL(URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(
                    conn.getOutputStream()
                );
                out.write(newQuestion);
                out.flush();
                out.close();
                BufferedReader in = new BufferedReader(
                  new InputStreamReader(conn.getInputStream())
                );
                StringBuilder toReturn = new StringBuilder();
                String toCheck = in.readLine();
                while(toCheck !=null){
                    toReturn.append(toCheck);
                    toReturn.append("\n");
                    toCheck = in.readLine();
                }
                in.close();
                newAnswer = toReturn.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            //
        }
    }
    */

    // ui interactions
    public void updateQuestionAndAnswer() {
        question = newQuestion;
        answer = newAnswer;
    }

    // id 
    public void setId(ObjectId o) {
        this.id = o;
    }
    public ObjectId getId() {
        return this.id;
    }

}
