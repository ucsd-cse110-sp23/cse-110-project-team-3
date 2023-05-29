import Mediator.Mediator;
import RecordHistory.RecordHistory;

class RunCommands {
    public void runVoiceCommand(Mediator mediator, PromptBody promptBody) {
        if (mediator.getIsConfirmed()) {
            mediator.generateAnswer();
            mediator.updateQuestionAndAnswer();
            mediator.setIsConfirmedFalse();
    
            VoiceCommands vc = new VoiceCommands(mediator.getQuestion());
             // if is question
            if (vc.isQuestionCommand()) {               
                RecordHistory rh = new RecordHistory();
                rh.sendToFile(mediator.getQuestion(), mediator.getAnswer(), "UserData/prompt_history.txt");
            }
    
            // if is delete prompt
            else if (vc.isDeletePromptCommand()) { 
                PanelList list = promptBody.getPanelList();
                list.removeCompletedPrompts();
                promptBody.repaint();
            }
    
            // if is delete all
            else if (vc.isDeleteAllCommand()) {
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
    
}
 