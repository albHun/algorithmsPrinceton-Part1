import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
   private class Node{
	   Item item;
	   Node next;
	   Node pre;
   }
   
   private Node first;
   private Node last;
   
   public Deque(){
	   first = null;
	   last = first;
   }   // construct an empty deque
   
   public boolean isEmpty(){
	   return first == null;
   }                 // is the deque empty?
   
   public int size(){
	   int count = 0;
	   Node iter = first;
	   while (iter != null){
		   iter = iter.next;
		   count ++;
	   }
	   return count;
   }                        // return the number of items on the deque
   
   public void addFirst(Item item){
	   if (item == null) throw new java.lang.NullPointerException();
	   else{
		   Node oldfirst = first;
		   first = new Node();
	       first.item = item;
		   first.pre = null;
		   if (last == null){
			   last = first;
		   }
		   else {
			   first.next = oldfirst;
			   oldfirst.pre = first;
		   }
	   }
   }          // add the item to the front
   
   public void addLast(Item item){
	   if (item == null) throw new java.lang.NullPointerException();
	   else{
		   Node oldLast = last;
		   last = new Node();
		   last.item = item;
		   last.next = null;
		   if (isEmpty()) first = last;
	       else {
			   oldLast.next = last;
			   last.pre = oldLast;
		   }
	   }
   }           // add the item to the end
   
   public Item removeFirst(){
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   else{
		   Node oldfirst = first;
		   first = first.next;
		   if (isEmpty()) last = null;
		   else first.pre = null;
		   return oldfirst.item;
	   }
   }                // remove and return the item from the front
   
   public Item removeLast(){
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   else if (first.next == null){
		   Item temp = first.item;
		   first = null;
		   last = null;
		   return temp;
	   }
	   else {
		   Item temp = last.item;
		   last = last.pre;
		   last.next = null;
		   return temp;
	   }
   }                 // remove and return the item from the end
   
   public Iterator<Item> iterator(){ return new ListIterator(); }   // return an iterator over items in order from front to end
   
   private class ListIterator implements Iterator<Item>{
	   private Node current = first;
	   
	   public boolean hasNext(){ return current != null; }
	   public void remove(){ throw new UnsupportedOperationException(); }
	   public Item next(){
		   if (!hasNext()) throw new java.util.NoSuchElementException();
		   Item item = current.item;
		   current = current.next;
		   return item;
	   }
   }
   
   public static void main(String[] args){
	   Deque<String> test = new Deque<String>();
	   System.out.println(test.isEmpty());
	   System.out.println(test.size());
	   String[] str = new String[] {"Qiao", "Chu", "Jiang"};
	   test.addFirst(str[0]);
	   test.addLast(str[2]);
	   test.addFirst(str[1]);
	   
	   Iterator<String> itest = test.iterator();
	   while (itest.hasNext()){
		   String s = itest.next();
		   System.out.println(s);
	   }
   }   // unit testing
}