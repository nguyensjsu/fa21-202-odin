# Odin - Search Algorithms Visualizer

## Topic

- Interactive Application with Processing and Java

## Team Members

- Rounak Salim
- Aidan Jones
- Daniel Yeung
- Hussein Adams

## Areas of Contributions

- Hussein Adams

  - Developed keyboard handler using observer pattern.
  - Developed click handler using chain of responsibility pattern.
  - Developed UI nested views and managed drawing on display using Composite pattern.
  - Developed and owned the UI component (Grid, Control Panel, Buttons, Labels)
  - Fixed numerous feature integration and UI bugs.
  - Created UI wireframe.

- Rounak Salim

  - Developed the search algorithm strategy pattern.
  - Developed the BFS and DFS search algorithms.
  - Kept track of velocity and burn down rate.

- Aidan Jones

  - Developed the state pattern.
  - Developed Dijkstra's search algorithm.
  - Created the overall architecture diagram.

- Daniel Yeung
  - Developed the command pattern to assign functionality to buttons and labels.

## Project Summary

The project goal is to develop a search algorithm visualizer application using the [processing](https://www.processing.org/) framework. It provides several search algorithms BFS, DFS and Dijkstra. By triggering the application, it will search with different paths from the starting cell until reaching the ending cell through traveling the grids. Hence, user can easily understand different algorithms' logic by watching the visualized processing paths.

## Architecture

### High Level Architecture Without Software Interfaces

![architecture without interfaces](images/architecture_no_sw_interfaces.png)

### Architecture With Software Interfaces

![architecture with interfaces](images/architecture_sw_interfaces.png)

## Features Summary
#### Display
- A grid to show search state (visited, empty, obstacle, start, end)
- A controls panel that will contain buttons and labels.
#### User Input
- User can draw obstacles on grid
- User can click on buttons to control search state
#### Search Algorithms
- Supports multiple algorithms with ability to add new ones easily.

## Links

- User Story Video: https://youtu.be/d9P7PsuO3aw
- Demo Video: https://youtu.be/D5RwkgOYTWs
- Extra Credit Agile Video: None

- Presentation Slides: https://docs.google.com/presentation/d/12jAUqIlyf_p0HFNFEZI7R5iePW8-i7_SB6a_-zKlDuc/edit?usp=sharing
- Velocity and burn down chart: https://docs.google.com/spreadsheets/d/1AfH8bgbeG_QFUU0F9KdxXLE9ieewj1DtV_e5osFAVTI/edit?usp=sharing

### Journals
- Hussein's Journal: https://github.com/nguyensjsu/fa21-202-odin/blob/main/journals/hussein.md
- Aidan's Journal: https://github.com/nguyensjsu/fa21-202-odin/blob/main/journals/aidan.md
- Rounak's Journal: https://github.com/nguyensjsu/fa21-202-odin/blob/main/journals/rounak.md
- Daniel's Journal: https://github.com/nguyensjsu/fa21-202-odin/blob/main/journals/daniel.md

## Setup

- [Processing](https://www.processing.org/) is required to run the project
- Processing-java is also required
  - Open Processing
  - Click on Tools -> install processing-java

## Running the application
```bash
processing-java --force "--sketch=<path to/fa21-202-odin/main/>" "--output=<path to/fa21-202-odin/main/out>" --run
```

## UI Design

UI Wireframe

![alt text](https://github.com/nguyensjsu/fa21-202-odin/blob/main/images/ui_wireframe.jpg)

## Meeting Notes

#### November 14th, 2021

- Project proposal:

  - Search algorithms visualization
  - https://qiao.github.io/PathFinding.js/visual/

- Proposed technologies: Processing, Java

- Suggested algorithms to implement:

  - BFS, DFS, Dijkstra’s (strategy pattern?)
  - Adding new algos should be easy

- Patterns that can be used in the project:

  - Strategy pattern for different search algorithms
  - State pattern to indicate current state of search
  - Possibly observer for displaying the animation
  - Possibly command pattern for menu items to select search

- XP core values
  - Aidan: Communication
  - Hussein: Respect
  - Rounak: Simplicity
  - Daniel: Feedback

#### November 18th, 2021

- Rounak to build a barebone project and push it by eod
- Aidan to look into search algorithms (Dijkstra’s) algorithm
- Hussein to also research how to import custom java classes into .pde files
- Design meeting after we set up a barebone project.


#### November 23rd, 2021

- Hussein to draw UI wireframe
- Aiden to draw architecture diagrams
- Meet on Dec 3rd to record demo
- Hussein to record 1 minute user story
- Everyone to work on slides and finish them before Dec 3rd
- Final bug fixing and feature integration work
