public class metroStation{ //this is the node object nodes
metroStation next; // it points to the next station
String name; //its element is a name 


public metroStation(String pName){
  
  this.name = pName;


}

 //this method sets the next node 
public void setNext( metroStation a){

  this.next = a;

}
//this node gets element name 
public String getName(){

  return this.name;
} 











}