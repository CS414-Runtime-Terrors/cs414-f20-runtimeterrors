## Development Manual

#### Project Description
This application is a software system designed to play Omega Chess, which is a variation of traditional Chess. We are using Java as the programming language. For graphics, we are using libgdx for our graphics which is an open source game development application framework. We are using JUnit5 for unit testing. 

#### Step 1 - Download IntelliJ
We have been using IntelliJ for development and recommend that since we are more familiar with how to set up the configurations on this platform. You can download IntelliJ at the following link: https://www.jetbrains.com/idea/download/#section=windows

#### Step 2 - Clone the Repo

##### Option 1 - Git Bash
There are many ways to clone to repository. One way is to use a terminal window. Git Bash is highly recommended and easy to use. It can be downloaded from this link https://gitforwindows.org/. Once Git Bash is download, open it up. Then you can clone the repo by typing "git clone https://github.com/CS414-Runtime-Terrors/cs414-f20-runtimeterrors.git" to clone the repository using https method. Then you will have the repository cloned! If you don't specify another name to make the folder with all of the source code, it will be put into a folder called 'cs414-f20-runtimeterrors'. 

Now to open the project after cloning with Git Bash, you need to start up IntelliJ which was downloaded in step 1. On the start up screen choose the 'Open or Import' option. Then you will have to navigate to the folder from the repo you cloned in step 1. Click the folder, and hit OK and it will load the project! It will download all the necessary dependencies and start a build so it could take a little bit. 

##### Option 2 - Directly from IntelliJ
Another way to clone the repo is to do it through IntelliJ directly. You should have it downloaded from step 1 above. Open it and it should bring up the start up screen. From here you can select the bottom option 'Get from Version Control'. It will ask for the repository URL and you can put this in it: https://github.com/CS414-Runtime-Terrors/cs414-f20-runtimeterrors.git. Then you can change the name on the second line if you desire, and hit the Clone button on the bottom! Easy!
 
#### Step 3 - Build the project
When you import the project for the first time, it does a full build of the project. If you need to do another one at some point though all you need to do is go to the top navigation bar and click "Build -> Build Project" or there is a shortcut of the green hammer that can build the project as well.

#### Step 4 - Set up unit tests configuration
The unit test classes are in 'cs414-f20-runtimeterrors ->server -> src -> test'. You'll notice if you right click one of the unit test classes and hit 'Run TestXX', it gives an error saying no tests found. That is because the configurations aren't set up correctly. The easiest way we have found is to set up a configuration to run all of the unit tests. 

Next to the build shortcut button, there should be a dropdown and the first option says 'Edit Configurations'. Click that, and then on the scren that opens click the '+' button to add a new configuration. It will ask what kind of configuration to add. Choose JUnit (notice there is both an Android JUnit option and JUnit - we want just the JUnit). At the top it asks for a name. I would suggest 'AllTests' or something like that. Then change the dropdown for the 'Test Kind' to be 'All in directory'. Then on the directory section browse and find the directory containing the tests (cs414-f20-runtimeterrors -> server -> src -> test -> java -> com -> omegaChess) and choose the omegaChess directory there. Then you will need to choose the module for the section 'Use classpath of module:'. For this, open the dropdown and select the option 'cs414-f20-rumtimeterrors.server.test'. Then hit ok to save the configuration. There could be a better way to set up the unit test configurations but this is how we currently do it.

#### Step 5 - Run all unit tests
Congrats on setting up the unit test configuration! Now we want to run all of the tests! Hit the play/run button next to the configuration that says 'AllTests' or whatever you named it. This will run all of the unit tests. On the bottom in the run section you should be able to see all of the test classes that were run and if they successfully passed all tests or not. 

#### Step 6 - Start Server
Now that we checked our unit tests and they are all passing, we want to actually run the application! To do this in a test environment (running a local server), we need to start the local server first. To do this, navigate to the server code directory (cs414-f20-runtimeterrors -> server -> src -> main -> java -> com.omegaChess -> server. You can tell what file we want to run because it is the only one in the directory with a little green triangle on the class icon. So right click on 'OCMultiServer' and select 'Run OCMultiServer.main()'. This will start the local server. We chose to implement options for using a local server for independent local testing, and then the main server which is used as the production/deployed server.

#### Step 7 - Run DesktopLauncher application
Now for the fun part! Now if you are wanting to do the local testing with the local server, we need to pass a command line argument to desktop launcher to tell it to use the local server. To do this, navigate to the desktop launcher folder ('cs414-f20-runtimeterrors -> desktop -> src -> com.cs414.runtimeterrors.game.desktop'. Then right click on the DesktopLauncher file and select 'Edit DesktopLauncher.main()'. It will bring up a screen and you can add 'true' as a program argument. Then right click on the 'DesktopLauncher' file and select 'Run DesktopLauncher.main()'. This could take a moment to build but then it will bring up our application for you to use! Have fun playing OmegaChess! I also wanted to note that if you know that the production server is running, you can either pass 'false' into DesktopLauncher, or pass no command line arguments and it will attempt to use the production server.
