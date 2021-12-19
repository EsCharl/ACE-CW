import java.util.*;

public class FrequentFlyerProgram {

    private final TreeSet<Passenger> treeSet;
    int referenceID;

    public FrequentFlyerProgram(){
        this.treeSet = new TreeSet<>(new Passenger());
        this.referenceID = 0;
    }

    public int getReferenceID(){
        return referenceID++;
    }

    static class Passenger implements Comparator<Passenger> {

        String firstName;
        String lastName;
        String flyerStatus;
        String confirmationCode;
        int flyerStatusCode;
        int waitingListTime;
        int referenceID;

        String[] flyerStatusList = {"Silver","Gold","Platinum"};

        public Passenger(){}

        public Passenger(String firstName,String lastName,String confirmationCode,int flyerStatusCode,int waitingListTime,int referenceID){
            this.firstName = firstName;
            this.lastName = lastName;
            this.confirmationCode = confirmationCode;
            this.flyerStatusCode = flyerStatusCode;
            this.waitingListTime = waitingListTime;
            this.flyerStatus = flyerStatusList[flyerStatusCode];
            this.referenceID = referenceID;
        }

        @Override
        public int compare(Passenger o1, Passenger o2) {
            if(o1.flyerStatusCode != o2.flyerStatusCode){
                return o2.flyerStatusCode - o1.flyerStatusCode;
            }
            else if (o1.waitingListTime != o2.waitingListTime){
                return o2.waitingListTime - o1.waitingListTime;
            }
            else {
                return o1.referenceID - o2.referenceID;
            }
        }
    }

    public void addPassenger(String firstName,String lastName,int flyerStatusCode,int waitingListTime) {
        int referenceID = getReferenceID();
        char[] flyerStatusList = {'S','G','P'};
        String flyerCode = String.format("%d",flyerStatusCode);
        String reference = String.format("%02d",referenceID);
        String waitingTime = String.format("%02d",waitingListTime);
        String confirmationCode = flyerStatusList[flyerStatusCode] + flyerCode + reference + waitingTime;
        boolean notExist = treeSet.add(new Passenger(firstName, lastName, confirmationCode, flyerStatusCode, waitingListTime, referenceID));

        if(notExist) {
            System.out.format("Passenger %s %s with Confirmation Code %s has been added to the queue.\n", firstName, lastName, confirmationCode);
        }
        else
            System.out.format("Passenger with Confirmation Code %s already exist in queue.\n",confirmationCode);
    }

    public void removePassenger(String confirmationCode){
        int flyerCode = Integer.parseInt(confirmationCode.substring(1, 2));
        int reference = Integer.parseInt(confirmationCode.substring(2, 4));
        int waitingTime = Integer.parseInt(confirmationCode.substring(4, 6));
        boolean exists = treeSet.remove(new Passenger(null, null, confirmationCode, flyerCode, waitingTime, reference));

        if(exists) {
            System.out.format("Passenger with Confirmation Code %s has been removed from the queue.\n",confirmationCode);
        }
        else
            System.out.format("Passenger with Confirmation Code %s does not exist in queue.\n",confirmationCode);
    }

    public void printList(){
        if(!treeSet.isEmpty()) {
            System.out.format("%-2s  %-12s%-12s%-14s%-14s%-12s\n","ID", "First Name", "Last Name", "Booking Code", "Flyer Status", "Waiting Time");
            for (Passenger current : treeSet) {
                System.out.format("%02d  %-12s%-12s%-14s%-14s%-12d\n",current.referenceID, current.firstName, current.lastName, current.confirmationCode, current.flyerStatus, current.waitingListTime);
            }
        }
        else{
            System.out.println("No Passengers Left on Upgrade List");
        }
    }

    public void getPassengerList(int upgrades){
        for(int i = upgrades; i > 0; i--) {
            if(!treeSet.isEmpty()) {
                Passenger passenger = treeSet.pollFirst();
                assert passenger != null;
                System.out.format("Passenger %s %s with %s flyer status has selected from the queue.\n", passenger.firstName, passenger.lastName, passenger.flyerStatus);
            }
            else {
                System.out.println("No Passengers Left on Upgrade List");
                break;
            }
        }
    }

    public void initialisePassengerList(FrequentFlyerProgram frequentFlyerProgram){
        frequentFlyerProgram.addPassenger("Charles","Leong",1,10);
        frequentFlyerProgram.addPassenger("Tan","Xian",2,20);
        frequentFlyerProgram.addPassenger("John","Cena",0,20);
        frequentFlyerProgram.addPassenger("Alton","John",2,10);
        frequentFlyerProgram.addPassenger("John","John",1,20);
        frequentFlyerProgram.addPassenger("Smith","John",0,15);
        frequentFlyerProgram.addPassenger("Harry","Potter",0,25);
        frequentFlyerProgram.addPassenger("Albus","Snape",2,12);
        frequentFlyerProgram.addPassenger("Merry","Santa",1,27);
        frequentFlyerProgram.addPassenger("John","Smith",0,10);
        frequentFlyerProgram.addPassenger("John","Smith",0,10);
    }

    public static void main(String[] args) {

        FrequentFlyerProgram frequentFlyerProgram = new FrequentFlyerProgram();
        frequentFlyerProgram.initialisePassengerList(frequentFlyerProgram);
        int choice,flyerStatusCode,waitingListTime,upgrades;
        String firstName,lastName,confirmationCode;
        boolean done = false;

        System.out.println("\nWelcome to Chew Language Airlines Booking Service");
        do{
            System.out.println("\nEnter Your Choice:");
            System.out.println("1.Add New Upgrade Request");
            System.out.println("2.Remove Upgrade Request");
            System.out.println("3.Show List of Passengers");
            System.out.println("4.Get List of Highest Priority Passengers");
            System.out.println("5.Quit Program");

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter Passenger First Name:");
                    firstName = input.next();
                    System.out.println("Enter Passenger Last Name:");
                    lastName = input.next();
                    do {
                        System.out.println("Enter Passenger Flyer Status (0-Silver|1-Gold|2-Platinum):");
                        flyerStatusCode = input.nextInt();
                    } while (flyerStatusCode < 0 || flyerStatusCode > 2);
                    do {
                        System.out.println("Enter Passenger Waiting Time:");
                        waitingListTime = input.nextInt();
                    } while (waitingListTime < 0);
                        frequentFlyerProgram.addPassenger(firstName, lastName, flyerStatusCode, waitingListTime);
                }
                case 2 -> {
                    do {
                        System.out.println("Enter Passenger Confirmation Code:");
                        confirmationCode = input.next();
                    } while (confirmationCode.length() != 6);
                    frequentFlyerProgram.removePassenger(confirmationCode);
                }
                case 3 -> frequentFlyerProgram.printList();
                case 4 -> {
                    do {
                        System.out.println("Enter Number of Passenger Upgrades:");
                        upgrades = input.nextInt();
                    } while (upgrades <= 0);
                    frequentFlyerProgram.getPassengerList(upgrades);
                }
                case 5 -> {
                    done = true;
                    System.out.println("Thank You for Using Chew Language Airlines Booking Service");
                }
                default -> System.out.println("Invalid Input");
            }
        } while (!done);
    }
}
