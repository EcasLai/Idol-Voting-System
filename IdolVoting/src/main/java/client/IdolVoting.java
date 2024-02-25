package client;

// Import ADT
import adt.ArrayList;
import adt.LinkedList;
import adt.ListInterface;
import adt.SortedList;
// Import Entity
import entity.Admin;
import entity.Poll;
import entity.Votee;
import entity.Voter;
// Import Util
import java.util.Scanner;

/**
 * @author Joshua Koh Min En
 * @author Lai Chee Sheng
 * @author Lim Kang Yi
 * @author Shia Chai Fen
 */
public class IdolVoting { 
    // Global util
    private static Scanner sc = new Scanner(System.in);
    // Global Variables
    private static LinkedList<Voter> voterLinkedList  = new LinkedList<>();
    private static LinkedList<Poll> pollLinkedList  = new LinkedList<>();
    private static int curVotingPollIndex;
    private static int curVoterIndex; 
    // Global Status
    private static boolean isLogged = false;
    // Global Entity Object
    private static Voter curVoter; 
    private static Admin admin;
    
    
    public static void main(String[] args) {
        // CONSTANT
        String[] MAIN_MENU_ITEM_ARR = {"Admin", "Voter", "Poll", "Report"};
        
        // Variable Initialization
        admin = initAdmin();
        voterLinkedList = initVoter();
        pollLinkedList = initPollList();
        
        // Status Variables
        boolean notExitMain = true;     // Flag for main menu choice
        
        while(notExitMain) {
            // Print Main Menu
            displayMainMenu(MAIN_MENU_ITEM_ARR);
            
            // Get main menu choice
            System.out.print("Please select choice (1 - " + MAIN_MENU_ITEM_ARR.length + "): ");
            int choiceMainMenu = sc.nextInt();
            sc.nextLine();
            
            
            switch(choiceMainMenu) {
                case 0:
                    // Exit Program
                    notExitMain = false;
                    break;
                case 1: 
                    // Admin Module
                    AdminMenu.main(null);
                    break;
                case 2:
                    // Voter Module
                    VoterMenu.main(null);
                    break;
                case 3:
                    // Poll Module
                    PollMenu.main(null);
                    break;
                case 4:
                    // Report Module
                    ReportModule.main(null);
                    break;
                default:
                    break;
            }
        }
    }
    
    
    // Init Methods
    public static Admin initAdmin() {
        return new Admin();
    }
    
    public static LinkedList<Voter> initVoter() {
        LinkedList<Voter> voterLinkedList = new LinkedList<>();
        
        Voter voter1 = new Voter("V1001","Joshua", "joshua@gmail.com", "Joshhh", "123");
        Voter voter2 = new Voter("V1002","User404", "user404@gmail.com", "Invalid", "404");
        Voter voter3 = new Voter("V1003","KokLoong", "kokloong@gmail.com", "KL", "123");
        Voter voter4 = new Voter("V1004","Tiffany", "shiacf@gmail.com", "Jossadah", "123");
        Voter voter5 = new Voter("V1005","geeeeee", "gegeg@gmail.com", "Jwas", "123");
        voterLinkedList.add(voter1);
        voterLinkedList.add(voter2);  
        voterLinkedList.add(voter3);  
        voterLinkedList.add(voter4);  
        voterLinkedList.add(voter5);  
        
        return voterLinkedList;
        
    }
    
        public static LinkedList<Poll> initPollList(){
        LinkedList<Poll> pollList = new LinkedList<>();
        
        // Init Votee List in every Poll
        ListInterface<Votee> voteeList1 = new ArrayList<>();
        
        Poll p1 = new Poll("NKL GF Audition");
        Poll p2 = new Poll("Sing Selection");
        Poll p3 = new Poll("Hwasa Selection");
        Poll p4 = new Poll("WheeIn Audition");
        
        Votee vt1 = new Votee("Kendell Andrin", "Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.");
        Votee vt2 = new Votee("Choi Shan Yeh", "Bring money money money to you and your family");
        Votee vt3 = new Votee("Toh Deh Gong", "Bring peace to your family and home");
        Votee vt4 = new Votee("Zhou Gong", "Harh? Bla Bla Blaaaaa");
        Votee vt5 = new Votee("Nah Tok Gong", "Selamat Sejahtera Guysss");
        Votee vt6 = new Votee("Soon Wu Kong", "I am the BEST Singer in the world!");
        Votee vt7 = new Votee("Lee Eun Ji", "Annyeonghasaeyo");
        Votee vt8 = new Votee("Dua Beh Gong", "Vote me if you like R&B song");
        Votee vt9 = new Votee("Teh Zhu Gong", "hahaha");
        Votee vt10 = new Votee("Thomas Soh", "Support Local Singer!!!");
        
        p2.open();
        p2.addVotee(vt1);
        p2.addVotee(vt2);
        p2.addVotee(vt3);
        p2.addVotee(vt4);
        
        p3.open();
        p3.addVotee(vt5);
        p3.addVotee(vt6);
        p3.addVotee(vt7);
        
        p4.open();
        p4.addVotee(vt8);
        p4.addVotee(vt9);
        
        p1.open();
        p1.addVotee(vt10);
        
        
        for(int i=0; i<457; i++) {
            p2.addVote(vt1);
        }
        
        for(int i=0; i<1093; i++) {
            p2.addVote(vt2);
        }
        
        for(int i=0; i<245; i++) {
            p2.addVote(vt3);
        }
        
        for(int i=0; i<87; i++) {
            p2.addVote(vt4);
        }
        
        for(int i=0; i<1087; i++) {
            p3.addVote(vt5);
        }
        
        for(int i=0; i<8887; i++) {
            p3.addVote(vt6);
        }
        
        for(int i=0; i<8541; i++) {
            p3.addVote(vt7);
        }
        
        for(int i=0; i<5454; i++) {
            p4.addVote(vt8);
        }
        
        for(int i=0; i<6754; i++) {
            p4.addVote(vt9);
        }
        
        for(int i=0; i<346; i++) {
            p1.addVote(vt10);
        }
        
        pollList.add(p1);
        pollList.add(p2);
        pollList.add(p3);
        pollList.add(p4);
        
        pollList.getEntry(4).open();
        
        return pollList;
    }
    
    
    // Display Methods
    public static void displayMainMenu(String[] menuItemArr) {
        // Display Main Menu
        System.out.println("");
        
        System.out.println("Welcome to TARUMT Idol Voting System");
        
        for(int i=0; i<menuItemArr.length; i++) {
            System.out.println((i+1) + ". " + menuItemArr[i]);
        }
    }
    
    public static void displayAvailablePolls() {
        // Variables Init
        ArrayList<Poll> availablePollList = getAvailablePolls();
                    
        // Display the list of available polls
        System.out.println("\nBelow is the list of available Polls: ");
        System.out.printf("%4s %-15s\n","No.", "Poll Name");
        for(int i=0; i<availablePollList.size(); i++) {
            System.out.printf("%4s %-15s\n", (i+1) + ". ", availablePollList.get(i).getName());
        }
        System.out.println("");
    }
    
    public static void displayClosedPolls() {
        // Variables Init
        ArrayList<Poll> availablePollList = getClosedPolls();
                    
        // Display the list of available polls
        System.out.println("\nBelow is the list of available Polls: ");
        System.out.printf("%4s %-15s\n","No.", "Poll Name");
        for(int i=0; i<availablePollList.size(); i++) {
            System.out.printf("%4s %-15s\n", (i+1) + ". ", availablePollList.get(i).getName());
        }
        System.out.println("");
    }
    
    public static void displayAllPolls() {
        // Variables Init
        ArrayList<Poll> pollList = getAllPolls();
                    
        // Display the list of all polls
        System.out.println("\nBelow is the list of all Polls: ");
        System.out.printf("| %4s | %-15s | %11s |\n","No.", "Poll Name", "Total Votes");
        System.out.printf("|%38s|\n", AdminMenu.centerString("", 38, '-'));
        for(int i=0; i<pollList.size(); i++) {
            System.out.printf("| %4s | %-15s | %11s |\n", (i+1) + ". ", pollList.get(i).getName(), pollList.get(i).getTotalVotes());
        }
        System.out.println("");
    }
    
    public static void displayVoteeList(ListInterface<Votee> voteeList) {
        // Display the list of matchced votee
        
        // Header
        System.out.println("\nBelow is the list of Votee in the poll:");
        System.out.printf("| %4s | %-8s | %-22s | %-75s |\n","No.", "Votee ID", "Votee Name", "Votee Description");
        System.out.printf("| %118s |\n", AdminMenu.centerString("", 118, '-'));
        
        // Content
        if(voteeList.isEmpty()) {
            AdminMenu.displayErrMessage("Votee Not Found", "There is no Votee in the list");
        } else {
            for(int i=1; i<=voteeList.size(); i++) {
                System.out.printf("| %4s | %-8s | %-22s | %-75s |\n", i + ". ", voteeList.getEntry(i).getId(), voteeList.getEntry(i).getName(), voteeList.getEntry(i).getDescription());
            }
        }
        
        System.out.println("\n");
    }
    
    public static void displayResult(ArrayList<Votee> voteeList, int pollIndex, char chooseRankType) {
        //Display Result after the Poll is end (The result can't be review if the poll still processing)
        if (voteeList != null || !voteeList.isEmpty()) {
            switch (Character.toUpperCase(chooseRankType)) {
                case 'A':   //Default Rankin List
                    System.out.println("*********************************************************************************************");
                    System.out.println("*             R E S U L T  F O R  S I N G I N G  I D O L  C O M P E T I T I O N             *");
                    System.out.println("*********************************************************************************************");
                    System.out.printf("%-10s %-20s %-6s", "No", "Name", "Votes");
                    System.out.println("\n");
                    break;

                case 'B':    // Descending Ranking Result (The Most Famous to the Least)
                    System.out.println("*******************************************************************************************************");
                    System.out.println("*          R A N K I N G   R E S U L T  F O R  S I N G I N G  I D O L  C O M P E T I T I O N          *");
                    System.out.println("*******************************************************************************************************");
                    System.out.printf("%-10s %-20s %-6s", "Rank", "Name", "Votes");
                    System.out.println("\n");
                    break;
                    
                case 'C':   // Ascending Ranking Result (The Least Famous to the Most)
                    System.out.println("*************************************************************************");
                    System.out.println("*             S I N G I N G  I D O L  C O M P E T I T I O N             *");
                    System.out.println("*************************************************************************");
                    System.out.printf("%-10s %-20s %-6s", "No", "Name", "Votes");
                    System.out.println("\n");
                    break;
                    
                default: 
                    System.out.println("******************************");
                    System.out.println("      Invalid Character!      ");
                    System.out.println("******************************");
            }

        for(int i = 0; i< voteeList.size();i++){
                    System.out.printf("%-10s %-20s %-6d\n", (i+1) + ". " ,voteeList.get(i).getName(), pollLinkedList.get(pollIndex).getPollStatus().getVoteCount().get(voteeList.get(i)));
                }
            }
        else{
            System.out.println("***********************************************");
            System.out.println("*             The List is EMPTY!              *");
            System.out.println("***********************************************");
        }
    }
    
    
    // Utils Methods
    public static ArrayList<Poll> getAvailablePolls() {
        // Variables Init
        ArrayList<Poll> availablePollList = new ArrayList<>();

        // Get list of available polls
        for(int i=1; i<=pollLinkedList.getNumberOfEntries(); i++) {
            if(pollLinkedList.getEntry(i).isOpen()) {
                availablePollList.add(pollLinkedList.getEntry(i));
            }
        }
        
        return availablePollList;
    }
    
    public static ArrayList<Poll> getClosedPolls() {
        // Variables Init
        ArrayList<Poll> closedPollList = new ArrayList<>();

        // Get list of available polls
        for(int i=1; i<=pollLinkedList.size(); i++) {
            if(!pollLinkedList.getEntry(i).isOpen()) {
                closedPollList.add(pollLinkedList.getEntry(i));
            }
        }
        
        return closedPollList;
    }
    
    public static ArrayList<Poll> getAllPolls() {
        // Variables Init
        ArrayList<Poll> availablePollList = new ArrayList<>();

        // Get list of available polls
        for(int i=1; i<=pollLinkedList.getNumberOfEntries(); i++) {
                availablePollList.add(pollLinkedList.getEntry(i));
        }
        
        return availablePollList;
    }
    
    public static int getCurVotingPollIndexAvailable() {
        boolean validCurVotingPollIndex = false;
        
        while(!validCurVotingPollIndex) {
            ArrayList<Poll> availablePollList = getAvailablePolls();
                    
            // Display Available Polls
            displayAvailablePolls();

            // Get input from user
            System.out.print("Please select a poll to proceed: ");
            int curVotingPollIndexInput = sc.nextInt();
            sc.nextLine();
            
            if(curVotingPollIndexInput > 0 && curVotingPollIndexInput <= availablePollList.size()) {
                // Proceed
                curVotingPollIndex = curVotingPollIndexInput - 1;
                return curVotingPollIndex;
                
            } else {
                // If invalid endPollInput

                System.err.println("\nPlease enter a number that is in the range of (1 - " + availablePollList.size() + "). Please try again\n");
            }
        }
        
        return -1;
    }
    
    public static int getCurVotingPollIndexAll() {
        boolean validCurVotingPollIndex = false;
        
        while(!validCurVotingPollIndex) {
            ArrayList<Poll> allPollList = getAllPolls();
                    
            // Display Available Polls
            displayAllPolls();

            // Get input from user
            System.out.print("Please select a poll to proceed: ");
            int curVotingPollIndexInput = sc.nextInt();
            sc.nextLine();
            
            if(curVotingPollIndexInput > 0 && curVotingPollIndexInput <= allPollList.size()) {
                // Proceed
                curVotingPollIndex = curVotingPollIndexInput - 1;
                return curVotingPollIndex;
                
            } else {
                // If invalid endPollInput

                System.err.println("\nPlease enter a number that is in the range of (1 - " + allPollList.size() + "). Please try again\n");
            }
        }
        
        return -1;
    }
    
    
    // Getter
    public static LinkedList<Voter> getVoterLinkedList() {
        return voterLinkedList;
    }

    public static LinkedList<Poll> getPollLinkedList() {
        return pollLinkedList;
    }

    public static int getCurVoterIndex() {
        return curVoterIndex;
    }

    public static boolean isIsLogged() {
        return isLogged;
    }

    public static Voter getCurVoter() {
        return curVoter;
    }

    public static Admin getAdmin() {
        return admin;
    }

    public static int getCurVotingPollIndex() {
        return curVotingPollIndex;
    }
    
    
    // Setter
    public static void setVoterLinkedList(LinkedList<Voter> voterLinkedList) {
        IdolVoting.voterLinkedList = voterLinkedList;
    }

    public static void setPollLinkedList(LinkedList<Poll> pollLinkedList) {
        IdolVoting.pollLinkedList = pollLinkedList;
    }

    public static void setCurVotingPollIndex(int curVotingPollIndex) {
        IdolVoting.curVotingPollIndex = curVotingPollIndex;
    }

    public static void setCurVoterIndex(int curVoterIndex) {
        IdolVoting.curVoterIndex = curVoterIndex;
    }

    public static void setIsLogged(boolean isLogged) {
        IdolVoting.isLogged = isLogged;
    }

    public static void setCurVoter(Voter curVoter) {
        IdolVoting.curVoter = curVoter;
    }

    public static void setAdmin(Admin admin) {
        IdolVoting.admin = admin;
    }
}
