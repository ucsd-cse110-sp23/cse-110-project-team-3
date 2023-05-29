import Mediator.Mediator;
import RecordHistory.RecordHistory;

class RunCommands {
    public void runVoiceCommand(Mediator mediator, PromptBody promptBody) {
        mediator.updateQuestionAndAnswer();
        
        VoiceCommands vc = new VoiceCommands(mediator.getQuestion());
        
        // if command is "question"
        if (vc.isQuestionCommand()) {
            mediator.generateAnswer();
            mediator.updateQuestionAndAnswer();
            RecordHistory rh = new RecordHistory();
            rh.sendToFile(mediator.getQuestion(), mediator.getAnswer(), "UserData/prompt_history.txt");
        }

        // if command is "delete prompt"
        else if (vc.isDeletePromptCommand()) { 
            mediator.setNewAnswer("");
            mediator.updateQuestionAndAnswer();
            PanelList list = promptBody.getPanelList();
            list.removeCompletedPrompts();
            list.savePrompts();
            promptBody.repaint();
        }

        // if command is clear all
        else if (vc.isClearAllCommand()) {
            mediator.setNewAnswer("");
            mediator.updateQuestionAndAnswer();
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
 