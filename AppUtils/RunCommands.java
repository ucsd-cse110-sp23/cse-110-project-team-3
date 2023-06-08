import java.io.IOException;

import Mediator.Mediator;
import RecordHistory.RecordHistory;

class RunCommands {
    public void runVoiceCommand(Mediator mediator, Sidebar sidebar) throws IOException{
        mediator.updateQuestionAndAnswer();
        
        VoiceCommands vc = new VoiceCommands(mediator.getQuestion());
        
        /* Question Command */
        if (vc.isQuestionCommand()) {
            mediator.generateAnswer();
            mediator.updateQuestionAndAnswer();
            RecordHistory rh = new RecordHistory();
            rh.sendToFile(mediator.getQuestion(), mediator.getAnswer(), "UserData/prompt_history.txt", mediator);
            sidebar.addPrompt(mediator.getQuestion() + ": " + mediator.getAnswer());
        }

        /* Delete Prompt Command */
        else if (vc.isDeletePromptCommand()) { 
            mediator.setNewAnswer("");
            mediator.updateQuestionAndAnswer();
            PanelList list = sidebar.list;
            list.removeCompletedPrompts();
            list.savePrompts();
            sidebar.repaint();
        }

        /* Clear All Command */
        else if (vc.isClearAllCommand()) {
            mediator.setNewAnswer("");
            mediator.updateQuestionAndAnswer();
            ClearHistory clearHistory = new ClearHistory();
            clearHistory.clearHistory();
            sidebar.list.removeAll();
            sidebar.repaint();
        } 

        /* Send Email Command */
        else if (vc.isSendEmailCommand()) {
            mediator.setNewQuestion("Send email to " + vc.getEmailAddress());
            mediator.setNewAnswer("");
            mediator.updateQuestionAndAnswer();
            PanelList list = sidebar.list;
            list.sendSelectedEmail(vc.getEmailAddress());
        }

        /* Setup Email Command */ 
        else if (vc.isSetupEmailCommand()) {
            mediator.setNewAnswer("");
            mediator.updateQuestionAndAnswer();
            new EmailSetUpPopUp(mediator);
        }

        /* Create Email Command */ 
        else if (vc.isCreateEmailCommand()) {
            mediator.generateAnswer();
            mediator.updateQuestionAndAnswer();
            RecordHistory rh = new RecordHistory();
            rh.sendToFile(mediator.getQuestion(), mediator.getAnswer(), "UserData/prompt_history.txt", mediator);
            sidebar.addPrompt(mediator.getAnswer());
        }
        
        /* Error Statement */ 
        else {
            mediator.setNewQuestion("Error: bad command, you said, \"" + mediator.getNewQuestion() + "\"");
            mediator.setNewAnswer("Valid Commands: " +
                                  "Question [your question here], " +
                                  "Delete prompt, " + 
                                  "Clear all, " + 
                                  "Set up email, " + 
                                  "Send email to [email address here]");
            mediator.updateQuestionAndAnswer();
        }
    }
}
 