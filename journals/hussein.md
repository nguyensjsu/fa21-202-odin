## Weekly Scrum Report

### Week 1  (Nov. 14, 2021)

- Met at 4pm to kick off the class project.
- Everyone agreed to work on a search algorithms visualizer using processing.
- Assigned XP roles

#### XP Core Values: Respect

- The team met together over zoom and showed up on time.
- Everyone voiced their ideas and suggestions without interruptions.
- No one talked over others.
- Everyone’s concerns and questions were answered.

### Week 2  (Nov. 21, 2021)

- Aidan to implement state pattern
- Daniel to implement command pattern
- Aidan is working on Dijkstra’s algorithm
- Hussein to work on UI (add buttons and control panels stuff)
- Rounak to look at the velocity and hours work excel sheet

#### XP Core Values: Respect

- Everyone worked on their tasks.
- Questions were answered.
- Everyone joined meeting on time.

---

## Daily Scrum Report

### Odin, Sprint #1
Hussein Adams

- What I did since the last daily scrum:
    - NA - First daily scrum and sprint.

- What I plan to do today:
    - Update readme file with template/skeleton info needed.
    - Setup repo with gitignore.

- What Blockers I have:
    - None

### Odin, Sprint #1
Hussein Adams

- What I did since the last daily scrum:
    - Set up the repo with .ginignore and readme file
    - esearched Processing and associated libraries.

- What I plan to do today:
    - Develop a barebone project to get started.
    - Designed the keyboard controls and click handlers using observer and chain of responsibility patterns.

- What Blockers I have:
    - None


### Odin, Sprint #1
Hussein Adams

- What I did since the last daily scrum:
    - Developed a skeleton project that contained code for keyboard and click handlers.

- What I plan to do today:
    - Develop the display views using the composite pattern.
    - Add control panel display and grid display on the same main display using composite pattern

- What Blockers I have:
    - None.


### Odin, Sprint #1
Hussein Adams

- What I did since the last daily scrum:
    - Added control panel to the main display on the right side of the grid display.
    - Implemented Composite pattern to draw display.

- What I plan to do today:
    - Research GUI libraries
    - Add buttons to the control panel

- What Blockers I have:
    - None.

### Odin, Sprint #1
Hussein Adams

- What I did since the last daily scrum:
    - Researched GUI libraries.
    - Decided to implement my own as a simple version that works with the composite pattern.
    - Added buttons to the control panel display.
    - Cleanup work/bug fixes.

- What I plan to do today:
    - Continue working on the control panel by adding buttons and labels.

- What Blockers I have:
    - None.

### Odin, Sprint #2
Hussein Adams

- What I did since the last daily scrum:
    - Added buttons to select search algorithms. 
    - Created Label class to add labels and used as indicator to show which algorithm is used.
    - Research how to enhance the low resolution graphics (using processing pixelDensity(2))

- What I plan to do today:
    - Clean up and some code refactoring

- What Blockers I have:
    - None.


### Odin, Sprint #2
Hussein Adams

- What I did since the last daily scrum:
    - Moved draw calls for various components to their specific class draw methods.
    - Code clean up and bug fixing.

- What I plan to do today:
    - Clean up and some code refactoring

- What Blockers I have:
    - Waiting on the team to implement the state and command patterns.

### Odin, Sprint #3
Hussein Adams

- What I did since the last daily scrum:
    - Provided the team with high level design of state and command patterns needed.

- What I plan to do today:
    - Clean up and some code refactoring
    - Fix code integration bugs from the state pattern, and the command pattern.
    - Added functionality to the buttons using the command pattern that was implemented by Daniel.

- What Blockers I have:
    - None.

### Odin, Sprint #3
Hussein Adams

- What I did since the last daily scrum:
    - Added functionality to the different buttons in the control panel.
    - Added handlers for allowing to select start location, end location, clearing grid..etc

- What I plan to do today:
    - Provide UI Wireframe
    - Update readme.md with required information

- What Blockers I have:
    - None.

---

## Design Notes

### Keyboard Controls
- We need to listen to the keyboard and relay any commands to the keyboard listeners.

### Pattern:
- Observer pattern used

### Architecture
![alt text]()


### Click Handler
- We need a way to relay mouse clicks to the relevant component. For example, the grid listens for clicks and also, the control panel listens for clicks as well.

### Patterns
- Chain of responsibility

- Each component that listens for clicks will move the click along its chain of components if the click was not intended for it.

### Architecture
![alt text]()


### Display and Nested Views
We need a way to handle displays and drawing nested views as well.

### Patterns:
Composite pattern

Views will have nested views, these views can be added to a parent view at run time using the composite pattern.

### Architecture
![alt text]()
