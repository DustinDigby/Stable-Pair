//DLD Project 1
//10/23/2018
//@author Dustin D. Digby
public class StableGroup {
    protected Person personA, personB;

    public StableGroup(){
        this.personA =null;
        this.personB = null;
    }

    public StableGroup(Person personA, Person personB){
        this.personA = personA;
        this.personB = personB;
    }

    public Person getPersonA() {
        return personA;
    }

    public void setPersonA(Person personA) {
        this.personA = personA;
    }

    public Person getPersonB() {
        return personB;
    }

    public void setPersonB(Person personB) {
        this.personB = personB;
    }
}
