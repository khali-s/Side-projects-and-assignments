public class metroLine{ 
//this is the linked list object that has the first and last station implemented as head and tail
//String lineColor;
metroStation head ;
metroStation tail;
int size=0; //initialize it to 0


public void addFirst(metroStation a){
  if( this.head == null ){
    this.tail = a; // if this is the first element  then it is both the head and tail could've also checked if size == 0 instead
    this.head = a;
    
  }
  else{
    a.setNext(this.head); //set the head as next
    this.head = a; //make the head the first element

  }
  size = size + 1; //update size
}


}