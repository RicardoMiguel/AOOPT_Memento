# Memento exercise - Stack with undo and redo operations

Implement the Stack class with the same functionalities as in the Command exercise (undo and redo), but using the alternative design pattern: Memento. The Originator and Caretaker roles will be played by the same class - the Stack.

In this version, each 'pop' and 'push' command, before changing the stack, should create a memento - object holding a copy of the stack current state (a collection of its numbers) - and store it in a first list of mementos

The 'undo' operation should take the last memento (from the the first list of mementos) and pass it to a special function using this memento to replace the current stack state.

Before doing so, a new memento, representing the current stack state, should be created and stored in a second list of mementos. This second list will be used by the 'redo' operations.

In general, (1)'pop', (2)'push', (3)'redo' and (4)'undo' operations create a new memento (representing the current stack state), but (1-3) store it in the first list, and (4) in the second list of mementos.
(3-4) restores the stack state from a memento read from one of the mementos lists.

Below is presented an example of same operations as in the Command exercise and the content of internal stack lists:

´´´
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
´´´
