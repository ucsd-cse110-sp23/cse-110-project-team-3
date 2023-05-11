public class Mediator {

    // voice recording variables
    private VoiceRecorder voiceRecorder;
    private boolean isRecording;

    Mediator() {
        isRecording = false;
        voiceRecorder = new VoiceRecorder();
    }

    // voice recording functions
    public boolean isRecording() {
        return isRecording;
    }
    public void switchRecording() {
        isRecording = !isRecording;
    }
    public void startRecording() {
        voiceRecorder.startListening();
    }
    public void stopRecording() {
        voiceRecorder.stopListening();
    }

}
