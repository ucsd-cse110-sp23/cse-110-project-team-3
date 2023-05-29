import Mediator.Mediator;
import RecordHistory.RecordHistory;

class RunCommands {
    public void runVoiceCommand(Mediator mediator, PromptBody promptBody) {
        mediator.generateAnswer();
        mediator.updateQuestionAndAnswer();
        
        VoiceCommands vc = new VoiceCommands(mediator.getQuestion());
        
        // if command is "question"
        if (vc.isQuestionCommand()) {               
            RecordHistory rh = new RecordHistory();
            rh.sendToFile(mediator.getQuestion(), mediator.getAnswer(), "UserData/prompt_history.txt");
        }

        // if command is "delete prompt"
        else if (vc.isDeletePromptCommand()) { 
            PanelList list = promptBody.getPanelList();
            list.removeCompletedPrompts();
            promptBody.repaint();
        }

        // if command is clear all
        else if (vc.isClearAllCommand()) {
            ClearHistory clearHistory = new ClearHistory();
            clearHistory.clearHistory();
            promptBody.list.removeAll();
            promptBody.repaint();
        } 
        
        else {
            // TODO call error message
        }
    }
}
 