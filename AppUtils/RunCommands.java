import Mediator.Mediator;
import RecordHistory.RecordHistory;

class RunCommands {
    public void acceptButtonPressed(Mediator mediator, PromptBody promptBody) {
        if (mediator.getIsConfirmed()) {
            //System.out.println("In mediator.getIsConfirmed()");
            mediator.generateAnswer();
            mediator.updateQuestionAndAnswer();
            mediator.setIsConfirmedFalse();
    
            VoiceCommands vc = new VoiceCommands(mediator.getQuestion());
             // if is question
            if (vc.isQuestionCommand()) {               
                System.out.println("Isquestion");
                RecordHistory rh = new RecordHistory();
                rh.sendToFile(mediator.getQuestion(), mediator.getAnswer(), "UserData/prompt_history.txt");
            }
    
            // if is delete prompt
            else if (vc.isDeletePromptCommand()) { 
                //System.out.println("In isDeletePromptCommand()");
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
 