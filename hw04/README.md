## Assignment 4 – Views and Constraint Layouts
### Team startstarstar Members: Thanh Hyunh, Youngjin Shin

#### Accelerometer App
1.  What design question do you need to ask before beginning?
  - How often should the app take in data from the accelerometer?
  - How should the UI be designed to make the SeekBar intuitive for the user to set the "significant" movement threshold?
2.  What are the functions that need to be written?
  - Calculating net acceleration without gravity
  - Calculating of net acceleration
  - Detection of Significant Movement
  - Update and read the threshold value from the SeekBar.
  - Function to display Toast messages.
3. What exceptions do you need to handle and how should you handle them?
  - No accelerometer sensor available on the device: Inform the user via a Toast or dialog.
  - SeekBar initialization failure: Use a default threshold value and notify the user.
4. What types of feedback do you need to provide the user as they interact with your App?
  - Visual feedback on the current set threshold from the SeekBar.
  - Toast notifications for significant movements.
  - Logs for recorded movements.

| mainActivity(init) | significant movement Toast | adjusted seekbar |
| - | - | - |
| ![Screenshot_1697583699](https://github.com/yjshin229/CS501/assets/78634177/1a66da47-2af5-46e7-bfb3-9fe9c9ea8b09)| ![Screenshot_1697582553](https://github.com/yjshin229/CS501/assets/78634177/0ead56c7-cfde-4d9a-9582-dda5389d451c)| ![Screenshot_1697583363](https://github.com/yjshin229/CS501/assets/78634177/c75e9559-6505-47d4-a326-77444ed11f85)|


#### Flashlight App
1. What design questions do you need to ask before beginning?
  - How should the app visually communicate to the user when the flashlight is on/off?
  - How can the layout let the user be able to conveniently turn on and off the switch and write “on” and “off”?
2. What are the functions that need to be written?
  - Toggle flashlight on/off.
  - Monitor Action TextBox for text input.
  - Detect fling gestures and their direction.
3. What exceptions do you need to handle and how should you handle them?
  - No flashlight on the device: Notify the user and disable the toggle switch.
  - Fling gesture detection failure: Use alternate methods like the text box for interaction or notify the user.
4. What types of feedback do you need to provide the user as they interact with your App?
  - Visual feedback indicating whether the flashlight turned on or off.
  - Toggle switch state changes.
  - Toast notifications for errors or flashlight state information.

| mainActivity(init) | toggleOn | toggleOff | lightOff using textinput |
| - | - | - | - |
|![Screenshot_1697586180](https://github.com/yjshin229/CS501/assets/78634177/e40b69b2-13de-4c7d-9861-b22be918c767)| ![Screenshot_1697586117](https://github.com/yjshin229/CS501/assets/78634177/acebe6eb-9e0d-4e2a-91bb-08c7c1cc2631) | ![Screenshot_1697586114](https://github.com/yjshin229/CS501/assets/78634177/b75e8cef-dff8-4cb2-8c6e-d82843f82a4f) | ![Screenshot_1697586123](https://github.com/yjshin229/CS501/assets/78634177/7d454013-62ce-45cb-a70d-ebf3c257da2a)


    
#### Direction Fling App 
1. What design questions do you need to ask before beginning?
  - What kind of content or images will be used for each activity to make them distinct?
2. What are the functions that need to be written?
  - Detect on flick gestures and their direction.
  - Detect device shake.
3. What exceptions do you need to handle and how should you handle them?
  - Fling gesture detection failure: Use buttons or other interaction methods as backup.
  - Device shake detection failure: Notify the user.
4. What types of feedback do you need to provide the user as they interact with your App?
  - Transition animations or visual cues when switching between activities.
  - Shaking animation for the shaking feature.

#### Hangman App
1. What design questions do you need to ask before beginning?
  - What visual style and theme should the game have?
  - How will the hangman figure be represented visually as the user makes incorrect guesses?
2. What are the functions that need to be written?
  - Initialize the game state and select a random word from wordbank.
  - Detect and process letter button clicks.
  - Implement hint functions.
  - Start a new game.
3. What exceptions do you need to handle and how should you handle them?
  - Word selection error: Fallback to a default word list or notify the user.
4. What types of feedback do you need to provide the user as they interact with your App?
  - Visual updates of the hangman figure.
  - Updated word display as letters are guessed.
  - Toast notifications or pop-ups for hints and game status updates.



