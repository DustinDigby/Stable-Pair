//LDS Project 1
//10/23/2018
//@author Dustin D. Digby
public class Person {

    protected String name;
    protected int number, preference1[];
    protected boolean hasPair;

    public Person(){
        this.name ="";
        this.number =0;
        preference1= new int[0];
        hasPair =false;
    }

    public Person(String name, int number, String preference[]){
        this.name = name;
        this.number = number;
        this.preference1 = changeInt(preference);
        hasPair = false;
    }

    //Method to see what the index of person's most favorite preference is
    public int findFavorite(Person person) {
        int index = 0;
        int min = person.preference1[index];
        for (int i = 0; i < person.preference1.length; i++) {

            if (person.preference1[i] < min) {
                min = person.preference1[i];
                index = i;
            }
        }
        return index;
    }

    //Method to change the String Array into an Array of int's
    private int[] changeInt(String prefrence[]){
        int prefInt[] = new int[prefrence.length];
        for(int i =0; i< prefrence.length; i++){
            prefInt[i] = Integer.parseInt(prefrence[i]);
        }
        return prefInt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int[] getPreference1() {
        return preference1;
    }

    public void setPreference1(int[] preference1) {
        this.preference1 = preference1;
    }

    public void setHasPair(boolean hasPair) {
        this.hasPair = hasPair;
    }

    public boolean isHasPair() {
        return hasPair;
    }
}