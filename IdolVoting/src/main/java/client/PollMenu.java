package client;

import adt.SortedList;
import adt.LinkedList;
import adt.ArrayList;
import entity.Poll;
import entity.Voter;
import entity.Votee;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Shia Chai Fen
 */
public class PollMenu {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        char selection;
        char contProcess = 'N';

        int loop = 0;
        // display menu
        do {
            System.out.println("\n\n\n\t------------------------------------------------------------------------------------------");
            System.out.println("\t|                                                                                              |");
            System.out.println("\t|            S I N G I N G    I D O L   V O T I N G   R E S U L T   S Y S T E M                |");
            System.out.println("\t|                                                                                              |");
            System.out.println("\t------------------------------------------------------------------------------------------------");
            System.out.println("\t|                                                                                              |");
            System.out.println("\t|      Select one :                                                                            |");
            System.out.println("\t|         1. Check Default Result                                                              |");
            System.out.println("\t|         2. Final Ranking                                                                     |");
            System.out.println("\t|            a. Descending Order                                                               |");
            System.out.println("\t|            b. Ascending Order                                                                |");
            System.out.println("\t|         3. Back                                                                              |");
            System.out.println("\t------------------------------------------------------------------------------------------------");

            try {
                System.out.print("\nEnter Your Option (1/a/b/3): ");
                selection = input.nextLine().charAt(0);
                int pollIndex;

                switch (selection) {
                    case '1': 
                        pollIndex = IdolVoting.getCurVotingPollIndexAll();
                        
                        ArrayList<Votee> defaultVoteeResult = new ArrayList<>();
                        defaultVoteeResult = IdolVoting.getPollLinkedList().getEntry(pollIndex + 1).defaultRanking();

                        result(defaultVoteeResult, pollIndex, 'A');
                        break;

                    case 'a':
                    case 'A': 
                        pollIndex = IdolVoting.getCurVotingPollIndexAll();
                        
                        ArrayList<Votee> descVoteeResult = new ArrayList<>();
                        descVoteeResult = IdolVoting.getPollLinkedList().getEntry(pollIndex + 1).descRanking();

                        result(descVoteeResult, pollIndex, 'B');
                        break;

                    case 'b':
                    case 'B':
                        pollIndex = IdolVoting.getCurVotingPollIndexAll();
                        
                        ArrayList<Votee> ascVoteeResult = new ArrayList<>();
                        ascVoteeResult = IdolVoting.getPollLinkedList().getEntry(pollIndex + 1).ascRanking();

                        result(ascVoteeResult, pollIndex, 'C');
                        break;

                    case '3':
                       loop = 1;
                       break;

                    default:
                        System.out.println("\n==================================");
                        System.out.println("! Error input. Please try again. !");
                        System.out.println("==================================\n");
                        loop = 1;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n====================================");
                System.out.println("! Invalid input. Please try again. !");
                System.out.println("====================================\n");
                loop = 1;
            }

        } while (loop != 1);

//            System.out.print("Do you wish to continue ? [Y|N] : ");
//            contProcess = input.next().charAt(0);
    }

    //Display Result after the Poll is end (The result can't be review if the poll still processing)
    public static void result(ArrayList<Votee> voteeList, int pollIndex, char chooseRankType) {
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

            for (int i = 0; i < voteeList.size(); i++) {
                System.out.printf("%-10s %-20s %-6d\n", (i + 1) + ". ", voteeList.get(i).getName(), IdolVoting.getPollLinkedList().getEntry(pollIndex + 1).getPollStatus().getVoteCount().get(voteeList.get(i)));
            }
        } else {
            System.out.println("***********************************************");
            System.out.println("*             The List is EMPTY!              *");
            System.out.println("***********************************************");
        }
    }
}
