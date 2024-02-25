package client;

import adt.LinkedList;
import adt.ListInterface;
import adt.MapInterface;
import adt.SortedList;
import adt.SortedListInterface;
import entity.Poll;
import entity.Votee;
import entity.Voter;
import java.util.Scanner;

/**
 *
 * @author Lai Chee Sheng
 */
public class VoterMenu {
    private static Scanner sc = new Scanner(System.in);
    
    
    public static void main(String[] args) {

        //Menu
        //default Value
        int choice;

        do {
            System.out.println("================== VOTER MENU =================");
            System.out.println("|| 1.Register Voter");
            System.out.println("|| 2.Login/Logout Voter");
            System.out.println("|| 3.Cast Vote");
            System.out.println("|| 4.View total number of voters participated");
            System.out.println("|| 5.Modify Account Details");
            System.out.println("|| 6.Delete Account");
            System.out.println("|| 7.Back to Main Menu");
            System.out.println("===============================================");
            System.out.print("==>Enter your choice(1-7): ");
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    //Register Voter
                    registerVoter();
                    break;

                case 2:
                    //Login Voter
                    loginVoter();
                    break;

                case 3:
                    //Check if user logged in
                    if (!IdolVoting.isIsLogged()) {
                        loginNotify();
                    } else {
                        if(selectPoll()){
                            //cast Vote
                            castVote();
                        }
                    }
                    break;

                case 4:
                    if (!IdolVoting.isIsLogged()) {
                        loginNotify();
                    } else {
                        //View number of voters 
                        int numberOfVoter = IdolVoting.getVoterLinkedList().getNumberOfEntries(); //Obtain total voter involved 
                        System.out.println("The total number of voters currently is " + numberOfVoter + ".\n");
                        
                        System.out.printf(" %-8s %-20s\n", " ID ", " Name ");
                        System.out.printf(" %-8s %-20s\n", " ======= ", " =================== ");
                        for(int i = 1; i <= IdolVoting.getVoterLinkedList().getNumberOfEntries(); i++){  
                            System.out.printf(" %-8s %-20s\n", IdolVoting.getVoterLinkedList().getEntry(i).getVoterID(),IdolVoting.getVoterLinkedList().getEntry(i).getUsername());
                        }
                    }
                    break;

                case 5:
                    if (!IdolVoting.isIsLogged()) {
                        loginNotify();
                    } else {
                        // Modify voter's details from the list
                        updateVoterMenu();
                    }
                    break;

                case 6:
                    if (!IdolVoting.isIsLogged()) {
                        loginNotify();
                    } else {
                        // Remove a voter from the list
                        deleteVoterSelf();
                    }
                    break;

                case 7:
                    // Back to User Menu;
                    IdolVoting.main(null);
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 7);

        System.out.println("Back to User Menu...");
    }
    
    

    public static void registerVoter() {
        System.out.println("\n==========USER REGISTRATION=============");
        System.out.printf("||Username: ");
        String tempUsername = sc.nextLine();
        System.out.printf("||Password: ");
        String tempPassword = sc.nextLine();
        System.out.println("");
        System.out.printf("||Real Name: ");
        String tempName = sc.nextLine();
        System.out.printf("||Email: ");
        String tempEmail = sc.nextLine();

        Voter tempVoter = new Voter("V1003", tempName, tempEmail, tempUsername, tempPassword);
        IdolVoting.getVoterLinkedList().add(tempVoter);

        System.out.println("\n You has successfully register an account. ");
    }

    public static void loginVoter() {
        int loginTry = 0;
        //Check if user logged in
        if (!IdolVoting.isIsLogged()) {
            //login only attempt less than 3 times
            while (!IdolVoting.isIsLogged() && loginTry < 3) {
                System.out.println("=============LOGIN USER=============");
                System.out.printf("|| Username  : ");
                String tempUsername = sc.nextLine();
                System.out.printf("|| Password  : ");
                String tempPassword = sc.nextLine();

                //Verify login detail with linked list contain
                for (int i = 1; i <= IdolVoting.getVoterLinkedList().getNumberOfEntries(); i++) {
                    if (IdolVoting.getVoterLinkedList().getEntry(i).validateAccount(tempUsername, tempPassword)) {
                        IdolVoting.setIsLogged(true);
                        IdolVoting.setCurVoter(IdolVoting.getVoterLinkedList().getEntry(i));
                        System.out.println("Login Successful!!");
                        IdolVoting.setCurVoterIndex(i);
                        break;
                    }
                }
                if (!IdolVoting.isIsLogged()) {
                    System.out.println("Login Failed, try again...(Attempt " + (loginTry + 1) + ")\n");
                    loginTry++;
                }

            }
        } else {
            //logout voter
            logoutVoter();
        }
        System.out.println("\nBack to menu....");

    }

    public static void logoutVoter() {
        System.out.printf("Do you wish to logout? (\"Y\" to log out): ");
        char logoutAccChoice = sc.next().charAt(0);

        if (logoutAccChoice == 'Y' || logoutAccChoice == 'y') {
            System.out.println("Voter " + IdolVoting.getVoterLinkedList().getEntry(IdolVoting.getCurVoterIndex()).getVoterName()
                    + "(ID: " + IdolVoting.getVoterLinkedList().getEntry(IdolVoting.getCurVoterIndex()).getVoterID() + ") has successfully logout.");
            IdolVoting.setIsLogged(false);
        }
    }

    public static boolean selectPoll() {
        if (IdolVoting.getPollLinkedList().isEmpty()) { //if poll empty
            System.out.println("There is no poll available to vote currently... (back menu)");
            return false;
        } else {
            System.out.println("===========Poll List============");
            for (int i = 1; i <= IdolVoting.getPollLinkedList().getNumberOfEntries(); i++) {
                System.out.println("||" + i + ") " + IdolVoting.getPollLinkedList().getEntry(i).getName());
            }

            System.out.print("=> Select the poll to vote ( 1 - " + IdolVoting.getPollLinkedList().getNumberOfEntries() + " ): ");
            IdolVoting.setCurVotingPollIndex(sc.nextInt());
            return true;
        }
    }

    public static boolean checkVoted() {
        //Get list of voted Voter from votedList(from poll)
        Poll curVotingPoll = IdolVoting.getPollLinkedList().getEntry(IdolVoting.getCurVotingPollIndex());

        //Check and compare the voterID(get from main) with the voterId of list of voted Voter using contain
        for (MapInterface.Entry<Votee, SortedListInterface<Voter>> entry : curVotingPoll.getVotedList().entrySet()) {
            Votee votee = entry.getKey();
            SortedListInterface<Voter> votedList = entry.getValue();

            if (votedList.contains(IdolVoting.getCurVoter())) {
                System.out.println("Voter " + IdolVoting.getCurVoter().getVoterID() + " has already voted!");
                return false;
            } else { //add vote if not yet vote 
                IdolVoting.getPollLinkedList().getEntry(IdolVoting.getCurVotingPollIndex()).getVotedList();

                //need increase vote number of voteCount in pollStatus
                System.out.println("Voter " + IdolVoting.getCurVoter().getVoterID() + " has been verified and can vote.");
                return true;
            }
        }
        return false;
    }

    public static void castVote() {
        if (checkVoted()) { //if voter already voted on specified poll
            System.out.println("Voter " + IdolVoting.getCurVoter().getVoterID() + " has already voted!");

        } else { //add vote if not yet vote 
            voteCastableVotee();
        }

    }

    public static void voteCastableVotee() {
        Poll curVotingPoll = IdolVoting.getPollLinkedList().getEntry(IdolVoting.getCurVotingPollIndex());

        ListInterface<Votee> voteeList = curVotingPoll.getVoteeList();

        System.out.println("Idol List");
        System.out.println("=============================");
        for (int i = 1; i <= voteeList.size(); i++) {
            System.out.println((i) + ") " + voteeList.getEntry(i).getName());
        }

        System.out.printf("Cast your vote(1-" + voteeList.size() + "): ");
        int idolChoice = sc.nextInt();
        sc.nextLine();

        //add vote number to Poll
        curVotingPoll.addVote(voteeList.getEntry(idolChoice));

        //add voter record into votedList(from Poll class)
        curVotingPoll.addVoter(voteeList.getEntry(idolChoice), IdolVoting.getCurVoter());

        System.out.println("You has successfully voted for " + voteeList.getEntry(idolChoice).getName() + ".");

    }

    public static void updateVoterMenu() {
        int updateChoice;
        do {
            //User Info
            System.out.println("\n===============USER INFO================");
            System.out.println("|| ID        : " + IdolVoting.getCurVoter().getVoterID());
            System.out.println("|| Username  : " + IdolVoting.getCurVoter().getUsername());
            System.out.println("|| Email     : " + IdolVoting.getCurVoter().getEmail());
            System.out.println("|| Real Name : " + IdolVoting.getCurVoter().getVoterName());

            //Selection Menu
            System.out.println("-------------Update Voter Info------------");
            System.out.println("|| 1) Username");
            System.out.println("|| 2) Email");
            System.out.println("|| 3) Password");
            System.out.println("|| 4) Back to menu");
            System.out.println("==========================================");
            System.out.printf("| Enter your choice (1-4): ");
            updateChoice = sc.nextInt();
            sc.nextLine();

            switch (updateChoice) {
                case 1:
                case 2:
                case 3:
                    updateVoter(updateChoice);
                    break;

                case 4:
                    System.out.println("Back to Menu...");
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (updateChoice != 4);
    }

    public static void updateVoter(int updateOption) {
        //declare temporary info
        
        String tempUpdatedUsername = IdolVoting.getCurVoter().getUsername();
        String tempUpdatedEmail = IdolVoting.getCurVoter().getEmail();
        String tempUpdatedPassword = IdolVoting.getCurVoter().getPassword();

        switch (updateOption) {
            case 1:
                System.out.printf("\nNew Username: ");
                tempUpdatedUsername = sc.nextLine();
                break;
            case 2:
                System.out.printf("\nNew Email: ");
                tempUpdatedEmail = sc.nextLine();
                break;
            case 3:
                System.out.printf("\nNew Password: ");
                tempUpdatedPassword = sc.nextLine();
                break;
            default:
                break;
        }

        Voter tempUpdatedVoter = new Voter(IdolVoting.getCurVoter().getVoterID(), IdolVoting.getCurVoter().getVoterName(), tempUpdatedEmail, tempUpdatedUsername, tempUpdatedPassword);

        //Replace new info into existed Voter
        IdolVoting.getVoterLinkedList().replace(IdolVoting.getCurVoterIndex(), tempUpdatedVoter);

        //Set updated Voter as logged user
        IdolVoting.setCurVoter( IdolVoting.getVoterLinkedList().getEntry(IdolVoting.getCurVoterIndex()));
        System.out.println("\nVoter Detail changed successfully. ");

    }

    public static void deleteVoterSelf() {
        System.out.println("\n===========Account Delete Confirmation===========");
        System.out.println("|| Once you delete your account, you won't     ||");
        System.out.println("|| be able to view or vote for the idol.       ||");
        System.out.println("|| ____________________________________________||");
        System.out.printf("|| Are you sure to delete? (\"Y\" to confirm): ");
        char deleteAccChoice = sc.next().charAt(0);

        if (deleteAccChoice == 'Y' || deleteAccChoice == 'y') {
            System.out.println("Voter " + IdolVoting.getVoterLinkedList().getEntry(IdolVoting.getCurVoterIndex()).getVoterName()
                    + "(ID: " + IdolVoting.getVoterLinkedList().getEntry(IdolVoting.getCurVoterIndex()).getVoterID() + ") has successfully deleted.");
            IdolVoting.getVoterLinkedList().remove(IdolVoting.getCurVoterIndex());
            IdolVoting.setIsLogged(false); //Logout user
            IdolVoting.setCurVoterIndex(0);

        }
        System.out.println("\nBack to menu....");
    }

    public static void loginNotify() {
        System.out.println("===LOGIN REQUIRED================");
        System.out.println("| Please proceed to login first |");
        System.out.println("=================================\n");
    }
}