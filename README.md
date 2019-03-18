# GeneralFileStructure
  For the convenience of file traversal in data system, giving the introduction with my implementation code in JAVA

  In this chacpter of my github, you will know problems I faced in my recently project, and why I choose these ways to be my final solutions. Also, I will list the benefits and suitable scenario for this GeneralFileStructure code.

# Problems Description 
  Most notepad provides editor with simple way but not so intuitive to see. We do some practical ways for displaying the notepad by using mobile phone technical. Such as strip, star shape, now here's the problem. Before we begin to design the notepad displaying, we have to conquer the data structure about its backend design.

# Tools Introduction
Tools and testbed enviornment I use lists below.

Netbeans IDE 8.2
JAVA Programming language


# Design Description 
  For the notepad displaying design, we promote the unlimited file structure. For example, choosing strip shape to display, we have to make the nodes in a maximum amount, even in an unlimited amounts. I had chosen this structure in one of my projects such as mindmap. 
   About the data structure, I use general tree as my notepad data structure. That benefits for some features. Such as using of displaying shape, tree traversal is just like the arrangement for displaying algorithm. Once we finished the traversal engineer, we also finished the frontend algorithm designing! So, the information of structure anout this project lists below.

Using "id" and "Linkid" in String data type to link or unlink on each of nodes.
General tree data structure : unlimited linking nodes for each of levels.
Complete thinking with composing of functions in data structure. Such as Add, Delete, Move, Search...


# Implementation Description 
  At first, giving some example nodes shown below. We test to move node 4 to node 3, then delete node 2 at last.
*
*            0
*          / | \
*         1  2  3
*        / \  \
*       4   5  6
*
  The result of file tree traversal list below.

run:<br>
======Procedure Produce & Add Neurons======<br>
Neuron information, id: 0, linkID: null, Content: I'm root<br>
Neuron information, id: 1, linkID: 0, Content: I'm level1-1<br>
Neuron information, id: 2, linkID: 0, Content: I'm level1-2<br>
Neuron information, id: 3, linkID: 0, Content: I'm level1-3<br>
Neuron information, id: 4, linkID: 1, Content: I'm level1-1-1<br>
Neuron information, id: 5, linkID: 1, Content: I'm level1-1-2<br>
Neuron information, id: 6, linkID: 2, Content: I'm level1-2-1<br>
======Procedure Link======<br>
Mindmap Tree Build Complete!<br>
======Procedure Tree Traversal======<br>
[0, null][1, 0][4, 1]<br>
[0, null][1, 0][5, 1]<br>
[0, null][2, 0][6, 2]<br>
[0, null][3, 0]<br>
======Procedure Move Neurons======<br>
Neuron move sucessfully<br>
======Procedure Link======<br>
Mindmap Tree Build Complete!<br>
======Procedure Tree Traversal======<br>
[0, null][1, 0][5, 1]<br>
[0, null][2, 0][6, 2]<br>
[0, null][3, 0][4, 3]<br>
======Procedure Delete Neurons======<br>
Neuron delete sucessfully<br>
======Procedure Link======<br>
Mindmap Tree Build Complete!<br>
======Procedure Tree Traversal======<br>
[0, null][1, 0][5, 1]<br>
[0, null][3, 0][4, 3]<br>
[0, null][6, 0]<br>
