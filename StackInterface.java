public interface StackInterface {

    StableGroup pop()throws StackUnderflowException;

    Object top()throws StackUnderflowException;

    void push(Object element) throws StackOverflowException;

    boolean isFull();

    boolean isEmpty();

    void push(Person person, Person person1)throws StackOverflowException;

    int size();
}
