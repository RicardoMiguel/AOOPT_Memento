# Memento exercise - Stack with undo and redo operations

Implement the Stack class with the same functionalities as in the Command exercise (undo and redo), but using the alternative design pattern: Memento. The Originator and Caretaker roles will be played by the same class - the Stack.

In this version, each 'pop' and 'push' command, before changing the stack, should create a memento - object holding a copy of the stack current state (a collection of its numbers) - and store it in a first list of mementos

The 'undo' operation should take the last memento (from the the first list of mementos) and pass it to a special function using this memento to replace the current stack state.

Before doing so, a new memento, representing the current stack state, should be created and stored in a second list of mementos. This second list will be used by the 'redo' operations.

In general, (1)'pop', (2)'push', (3)'redo' and (4)'undo' operations create a new memento (representing the current stack state), but (1-3) store it in the first list, and (4) in the second list of mementos.
(3-4) restores the stack state from a memento read from one of the mementos lists.

Below is presented an example of same operations as in the Command exercise and the content of internal stack lists:

```
Stack S = new Stack(); S={}, 1={}, 2={},
S.push(2); S={2}, 1={M{}}, 2={},
S.push(4);S={2, 4}, 1={M{}, M{2}}, 2={},
S.push(6); S={2, 4, 6}, 1={M{}, M{2}, M{2,4}}, 2={},
S.undo(); S={2, 4},1={M{}, M{2}}, 2={M{2,4,6}}
S.undo(); S={2}, 1={M{}}, 2={M{2,4,6}, M{2,4}}
S.redo(); S={2, 4}, 1={M{}, M{2}}, 2={M{2,4,6}}
S.push(10); S={2, 4, 10}, 1={M{}, M{2}, M{2,4}}, 2={}
* S.redo() => impossible, since no previous 'undo' memento
S.pop(); S={2, 4}, 1={M{}, M{2}, M{2,4}, M{2,4,10}}, 2={}
S.undo(); S={2, 4, 10}, 1={M{}, M{2}, M{2,4}}, 2={M{2,4}}
S.redo(); S={2, 4},1={M{}, M{2}, M{2,4}, M{2,4,10}}, 2={}
```

Original code:
```
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;

public class StackUndoRedo extends JFrame{
private IStack stack=new Stack();
JTextField message = new JTextField("<empty>");
public static void main(String[] args) {
new StackUndoRedo().setVisible(true);
}
StackUndoRedo() {
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
getContentPane().setLayout(new GridLayout(2,3));
JButton b=new JButton("Push"); add(b);
b.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
String s=JOptionPane.showInputDialog(null, "Give a number: ");
if (s==null) return;
int i=0;
try {
i=Integer.parseInt(s);
stack.push(i);
refresh();
}
catch (Exception ex) { JOptionPane.showMessageDialog(null, "Bad number"); }
}
});
b=new JButton("Undo"); add(b);
b.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
stack.undo();
refresh();
}
});
add(new JLabel("Top of the stack: "));
b=new JButton("Pop"); add(b);
b.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (stack.empty())
JOptionPane.showMessageDialog(null, "Stack empty !");
else { stack.pop(); refresh(); }
}
});
b=new JButton("Redo"); add(b);
b.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
stack.redo();
refresh();
}
});
add(message);

pack();
}
private void refresh(){
if (stack.empty()) message.setText("<empty>");
else message.setText(stack.top()+"");
}
}

interface IStack {
void push(int i);
void pop();
int top();
boolean empty();
void undo();
void redo();
}

interface Command {
void undo();
void redo();
}

class Stack implements IStack {
...
}
```
