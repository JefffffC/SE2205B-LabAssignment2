public class LinkedListQueue<E> implements Queue<E> {
  private SinglyLinkedList<E> storageList;
  public LinkedListQueue(){
	storageList = new SinglyLinkedList<E>();
  }
  public int size(){
	return storageList.size();
  }
  public boolean isEmpty(){
	return storageList.isEmpty();
  }
  public E first(){
	return storageList.first();
  }
  public void enqueue(E node){
	storageList.addLast(node);
  }
  public E dequeue(){
	return storageList.removeFirst();
  }
}
