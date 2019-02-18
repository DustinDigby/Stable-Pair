import java.io.*;
//Project 1 for LDS
//10/23/2018
//@author Dustin D. Digby

public class Project1 {

    private static int total;
    private static boolean allHavePair;
    private static StackInterface myStack = new ArrayStack();

    //Sees if the pair made will be stable or not
    public boolean isStable(Person personA, Person personB) {
        boolean stable = false;
        //A stable pair has a total number of equal to or less then 1 when subtracted
        if (personA.preference1[personB.number] + personB.preference1[personA.number] < total) {
            return true;
        } else {
            //Makes them the least favorite if they are not stable
            personA.preference1[personB.number] = total + 1;
            personB.preference1[personA.number] = total + 1;

            //See's if someone has already gone through every person, so that nobody that they match with will make it stable
            for (int i = 0; i < total; i++) {
                int prefA = personA.preference1[i];
                int prefB = personB.preference1[i];
                if (prefA == total + 1 || prefB == total + 1) {
                    stable = false;
                } else {
                    stable = true;
                }
            }
            if (!stable) {
                System.out.println("No stable pairing exists");
                System.exit(0);
            }
            return false;
        }
    }

    //Finds the stable pairs for groupA and groupB
    private void findPair(Person groupA[], Person groupB[]) {
        int favorite;
        StableGroup group;
        try {
            while (!allHavePair) {
                //Reads through each person in groupA
                for (int i = 0; i < total; i++) {
                    //See if the person in gorupA has a pair
                    if (!groupA[i].hasPair) {
                        favorite = groupA[i].findFavorite(groupA[i]);

                        //See if the person in groupB has a pair and if they are stable together in a group
                        if (!groupB[favorite].hasPair && isStable(groupA[i], groupB[favorite])) {

                            //Create a Person Object that is a group of two people
                            group = new StableGroup(groupA[i], groupB[favorite]);
                            myStack.push(group);
                            groupA[i].hasPair = true;
                            groupB[favorite].hasPair = true;

                            //If the person in groupB already has a partner.
                        } else if (groupB[favorite].hasPair) {
                            for (int p = 0; p <= myStack.size() + 1; p++) {
                                StableGroup changed = (StableGroup) myStack.top();
                                myStack.pop();
                                changed.personA.hasPair = false;
                                changed.personB.hasPair = false;
                                if (changed.personB == groupB[favorite]) {
                                    compare(changed.personA, groupA[i], groupB[favorite]);
                                    break;
                                }
                            }
                            //If the group is not stable make the preference the least favorite now
                        } else {
                            groupA[i].preference1[favorite] = total + 1;
                            groupB[favorite].preference1[groupA[i].number] = total + 1;
                        }
                    }
                }
                findAllHavePair(groupA, groupB);//Will run until everyone has a stable pair
            }
            print();//Prints out the content
        } catch (StackOverflowException e) {
            e.printStackTrace();
        } catch (StackUnderflowException e) {
            e.printStackTrace();
        }
    }

    //Compares two people to in groupA with groupB
    //The favorite of the two according to personB will make a stable group with them
    private void compare(Person choiceA, Person choiceB, Person decider) {
        try {
            if (decider.preference1[choiceA.number] < decider.preference1[choiceB.number]) {
                //The more favorite of the two according to the decider is chosen,
                //then the new group of the decider and the more favorite person is made and saved.
                //The person that is the less favorite of the two will have the index of the decider in their preference array
                //increased to be now the least favorite in their preferences.
                isStable(choiceB,decider);
                choiceB.preference1[decider.number] = total + 1;
                decider.preference1[choiceB.number] = total + 1;
                choiceA.hasPair = true;
                choiceB.hasPair = false;
                decider.hasPair = true;
                StableGroup group = new StableGroup(choiceA, decider);
                myStack.push(group);
            } else {
                isStable(choiceA,decider);
                choiceA.preference1[decider.number] = total + 1;
                decider.preference1[choiceA.number] = total + 1;
                choiceA.hasPair = false;
                choiceB.hasPair = true;
                decider.hasPair = true;
                StableGroup group = new StableGroup(choiceB, decider);
                myStack.push(group);
            }
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }
    }

    //Method to see if every person has a stable pair
    public void findAllHavePair(Person groupA[], Person groupB[]) {
        for (int i = 0; i < total; i++) {
            if (groupA[i].hasPair && groupB[i].hasPair) {
                allHavePair = true;
            } else {
                allHavePair = false;
                break;
            }
        }
    }

    //Method to read in the textfile
    public void readIn() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Project1TestData.txt")));
            total = Integer.parseInt(reader.readLine());
            int position = 0;
            Person groupA[] = new Person[total];
            Person groupB[] = new Person[total];
            String name;
            //Reads in the text file and makes them each into Person objects and then placed in two arrays, with one
            //being groupA and the other being groupB
            while ((name = reader.readLine()) != null) {
                if (position < total) {
                    Person person = new Person(name, position, reader.readLine().split("\t"));
                    groupA[position] = person;

                } else {
                    int secondPosition = position - total;
                    Person person = new Person(name, secondPosition, reader.readLine().split("\t"));
                    groupB[secondPosition] = person;
                }
                position++;
            }

            findPair(groupA, groupB);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method that will print out the stack and also keep the stack the same after popping each of the
    //groups.
    public void print() {
        try {
            StackInterface newStack = new ArrayStack();
            for (int i = 0; i < total; i++) {
                StableGroup group;
                group = (StableGroup) myStack.top();
                myStack.pop();
                newStack.push(group);//keeps the object that was pushed off
                System.out.println("Team " + i + ": " + group.personA.name + " and " + group.personB.name);
            }
            myStack = newStack;//sets the new stack to be myStack
            newStack = null;//gets rid of all of the objects in newStack
        } catch (StackUnderflowException e) {
            e.printStackTrace();
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }
    }

    //Main method of the code
    public static void main(String[] args) {
        Project1 project1 = new Project1();
        project1.readIn();

        System.exit(0);
    }
}