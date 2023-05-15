FILE STRUCTURE
We divided the files into three directories: AppUI, API, AppUtils.

AppUI: This directory contains all the files for creating the front end are. Anything to do with UI will be in here. App.java is the main file that calls the implementation of the whole application.

API: These include all the API implementations. This is where files that run the Whisper API and ChatGPT are.

AppUtils: This directory contains all the files related to the middleware. For example, loading and storing user questions and answers.

Other Directories:

UserData: The prompt history is stored here in a .txt file. Each prompt is separated by new lines.

test: Testing for the app is here.



HOW TO RUN THE APP IN VSCODE
To run the desktop application, go to the AppUI directory. In the directory, click on the  java file named, “App.java”. On the upper right corner, click on the “run” button (looks like a play button) and the app should launch. 



HOW TO RUN THE APP OTHER
Using your respective IDE manager, run the App.java
