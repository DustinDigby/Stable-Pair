public class ArrayStack implements StackInterface {

    protected int top;
    protected Object stack[];

    public ArrayStack(int size){
        stack = new Object[size];
        top =-1;
    }

    public ArrayStack(){
        this(10);
    }

    @Override
    public int size(){
        return top+1;
    }

    @Override
    public StableGroup pop() throws StackUnderflowException {
        if(isEmpty())
            throw new StackUnderflowException("User tried to pop an empty stack");
        stack[top]= null;
        top--;
        return null;
    }

    @Override
    public Object top() throws StackUnderflowException {
        if(isEmpty())
            throw new StackUnderflowException("User tried to examine the top of an empty stack");
        return stack[top];
    }

    @Override
    public void push(Object element) throws StackOverflowException {
        if(isFull())
            throw new StackOverflowException("User tried to push an element onto a full stack");
        top++;
        stack[top]= element;
    }

    @Override
    public boolean isFull() {
        if(top== stack.length-1)
            return true;
        return false;
    }

    @Override
    public boolean isEmpty() {
        if(top == -1)
            return true;
        return false;
    }

    @Override
    public void push(Person person, Person person1) throws StackOverflowException {
        if(isFull())
            throw new StackOverflowException("User tried to push an element onto a full stack");
        top++;
        stack[top]= person;
    }
}