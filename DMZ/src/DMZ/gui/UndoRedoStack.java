package DMZ.gui;

//UndoRedoStack is a variation of the Stack class that supports an undo
//operation for reversing the effect of push and pop operations and a redo
//operation that reverses undo operations.  The canUndo method indicates
//whether an undo is legal.  The canRedo operation indicates whether a redo is
//legal (sequences of undo operations can be redone, but once a push or pop
//operation is performed, it is not possible to redo any undo operations until
//another call is made on undo).

import java.util.*;

public class UndoRedoStack<E> extends Stack<E> {
 private Stack undoStack;
 private Stack redoStack;

 

 	public void init(){
 		this.clear();
 		undoStack.clear();
 		redoStack.clear();
	
 	}
 // post: constructs an empty UndoRedoStack
 public E peekUndoStack(){
	 return (E) undoStack.peek();
 }
 public E peekRedoStack(){
	 return (E) redoStack.peek();
 }
  
 public E popUndoStack(){
	 return (E) undoStack.pop();
 }
 public E popRedoStack(){
	 return (E) redoStack.pop();
 }
 public int sizeUndoStack(){
	 return undoStack.size();
 }


 public void stackRedoClear(){
	 redoStack.clear();
 }
 public UndoRedoStack() {
     undoStack = new Stack();
     redoStack = new Stack();
 }
private void pushTosuper(E value){
	super.push(value);
}
 // post: pushes and returns the given value on top of the stack
 public E push(E value) {
     super.push(value);
     undoStack.push("push");
     return value;
 }
public E pushMove(E value,E pointX, E pointY, E releasedX, E releasedY){
	super.push(value);
	super.push(pointX);
	super.push(pointY);
	super.push(releasedX);
	super.push(releasedY);
	undoStack.push("pushMove");
	return value;
}

public E pushRemove(E value){
	super.push(value);
	undoStack.push("pushRemove");
	return value;
}

public E pushRemove(E fromvalue,E value){
		super.push(fromvalue);
		super.push(value);
		undoStack.push("pushRemoveHasFrom");

		return value;
}

public void pushSetLine(E fromValue,E toValue){
	super.push(fromValue);
	super.push(toValue);
	undoStack.push("pushSetLine");

	
}

 // post: pops and returns the value at the top of the stack
 public E pop() {
     E value = super.pop();
     undoStack.push(value);
     undoStack.push("pop");
     redoStack.clear();
     return value;
 }
// label�쓣 ��吏곸��쓣�븣  
 public E popMove(){
	 E value = super.pop();
//	 undoStack.push(value);
	 return value;
 }
 public E popValue(){
	 E value = super.pop();
//	 undoStack.push(value);
	 return value;
 }
 public void popMove1(){
	 undoStack.push("popMove");
	 redoStack.clear();
 }
 public E popRemove(){
	 E value = super.pop();
//	 undoStack.push(value);
	 return value;
 }
 public void popRemove1(){
	 undoStack.push("popRemove");
	 redoStack.clear();
 }
 public void popRemoveHasFrom(){
	 undoStack.push("popRemoveHasFrom");
	 redoStack.clear();
 }
 
 // post: returns whether or not an undo can be done
 public boolean canUndo() {
     return !undoStack.isEmpty();
 }

 // pre : canUndo() (throws IllegalStateException if not)
 // post: undoes the last stack push or pop command
 public void undo() {
	 ParentLabel pl;
     if (!canUndo()) {
         throw new IllegalStateException();
     }
     Object action = undoStack.pop();
     if (action.equals("push")) {
         E value = super.pop();
         redoStack.push(value);
         redoStack.push("push");
     }else if(action.equals("pushMove")){
    	 E releasedpointY = super.pop();
    	 E releassedpointX = super.pop();
    	 E pointY = super.pop();
    	 E pointX = super.pop();
    	 E value = super.pop();
    	 redoStack.push(value);
    	 redoStack.push(pointX);
    	 redoStack.push(pointY);
    	 redoStack.push(releassedpointX);
    	 redoStack.push(releasedpointY);
    	 redoStack.push("pushMove");
    	 
     }else if(action.equals("pushRemove")){
    	 E value = super.pop();
    	 redoStack.push(value);
    	 redoStack.push("pushRemove");
    	     	 
     }else if(action.equals("pushRemoveHasFrom")){
    	 E value = super.pop();
    	 E fromValue = super.pop();
    	 redoStack.push(fromValue);
    	 redoStack.push(value);
    	 redoStack.push("pushRemoveHasFrom");
     }else if(action.equals("pushSetLine")){
    	 E toValue = super.pop();
    	 E fromValue = super.pop();
    	 redoStack.push(fromValue);
    	 redoStack.push(toValue);
    	 redoStack.push("pushSetLine");
     }
/*     else if(action.equals("popMove")){
    	 E pointY = (E) undoStack.pop();
    	 E pointX = (E) undoStack.pop();
    	 E value = (E) undoStack.pop();
    	 super.push(value);
    	 super.push(pointX);
    	 super.push(pointY);
    	 redoStack.push("popMove");
     }else if(action.equals("popRemove")){
    	 E value = (E) undoStack.pop();
    	 super.push(value);
    	 redoStack.push("popRemove");
     }else if(action.equals("popRemoveHasFrom")){
    	 E value = (E) undoStack.pop();
    	 E fromValue = (E) undoStack.pop();
    	 super.push(fromValue);
    	 super.push(value);
    	 redoStack.push("popRemoveHasFrom");
     }*/
     else {
         E value = (E) undoStack.pop();         
         super.push(value);
         redoStack.push("pop");
     }
 }

 // post: returns whether or not a redo can be done
 public boolean canRedo() {
     return !redoStack.isEmpty();
 }

 // pre : canRedo() (throws IllegalStateException if not)
 // post: redoes the last undone operation
 public void redo() {
	 ParentLabel pl;
	 if (!canRedo()) {
         throw new IllegalStateException();
     }
     Object action = redoStack.pop();
     if (action.equals("push")) {
         E value = (E) redoStack.pop();
         super.push(value);
         undoStack.push("push");
     }else if(action.equals("pushMove")){
    	 E releasedpointY = (E) redoStack.pop();
    	 E releassedpointX = (E) redoStack.pop();
    	 E pointY = (E) redoStack.pop();
    	 E pointX = (E) redoStack.pop();
    	 E value = (E) redoStack.pop();
    	 super.push(value);
    	 super.push(pointX);
    	 super.push(pointY);
    	 super.push(releassedpointX);
    	 super.push(releasedpointY);
    	 undoStack.push("pushMove");
    	 
     }else if(action.equals("pushRemove")){
    	 E value = (E)redoStack.pop();
    	 super.push(value);
    	 undoStack.push("pushRemove");
     }else if(action.equals("pushRemoveHasFrom")){
    	 E value = (E)redoStack.pop();
    	 E fromValue = (E)redoStack.pop();
    	 super.push(fromValue);
    	 super.push(value);
    	 undoStack.push("pushRemoveHasFrom");
     }else if(action.equals("pushSetLine")){
    	 E toValue = (E)redoStack.pop();
    	 E fromValue = (E)redoStack.pop();
    	 super.push(fromValue);
    	 super.push(toValue);
    	 undoStack.push("pushSetLine");
     }
/*     else if(action.equals("popMove")){
    	 E pointY = (E) undoStack.pop();
    	 E pointX = (E) undoStack.pop();
    	 E value = (E) undoStack.pop();
    	 super.push(value);
    	 super.push(pointX);
    	 super.push(pointY);
    	 redoStack.push("popMove");
     }else if(action.equals("popRemove")){
    	 E value = (E) undoStack.pop();
    	 super.push(value);
    	 redoStack.push("popRemove");
     }else if(action.equals("popRemoveHasFrom")){
    	 E value = (E) undoStack.pop();
    	 E fromValue = (E) undoStack.pop();
    	 super.push(fromValue);
    	 super.push(value);
    	 redoStack.push("popRemoveHasFrom");
     }*/
     else {
         E value = super.pop();
         undoStack.push(value);
         undoStack.push("pop");
     }
 }
 
 
}