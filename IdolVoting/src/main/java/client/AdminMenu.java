package client;

import adt.ArrayList;
import entity.Admin;
import entity.Votee;
import entity.Poll;
import adt.ListInterface;
import adt.MapInterface;
import adt.SortedList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Joshua Koh Min En
 */
public class AdminMenu {
    // Global util
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Constant
        String[][] MENU_ITEM_ARR = {
            {"Votee", "Create Votee", "Update Votee", "Delete Votee", "View Votee List"},
            {"Poll", "Create Poll", "Start Poll", "End Poll", "View Poll List"},
            {"Login"},
            {"Return to Main Menu"}
        };
        
        // Status Variables
        boolean notExit = true;
        

        while(notExit) {
            System.out.printf("\n%33s\n", centerString(" Welcome to ADMIN ", 33, '-'));
            
            // Display Admin Menu
            adminMenuLev1(MENU_ITEM_ARR);    
            
            System.out.print("Select option: ");
            int choiceAdmin = sc.nextInt();
            sc.nextLine();
            
            switch (choiceAdmin) {
                case 1:
                case 2:
                    if(!IdolVoting.isIsLogged()) {
                        // Error Msg
                        displayErrMessage(" Login Required! ", "Please Login first to proceed");
                        break;
                    } else {
                        adminMenuLev2(MENU_ITEM_ARR,choiceAdmin);
                        // Get level 2 choice
                        System.out.print("Please select choice (1 - " + MENU_ITEM_ARR.length + "): ");
                        int choiceAdmin2 = sc.nextInt();
                        sc.nextLine();

                        adminActionLev2(choiceAdmin,choiceAdmin2);
                        break;
                    }
                case 3:
                    if (IdolVoting.isIsLogged()) {
                        loginNotify();
                        loginAdmin();
                    } else {
                        loginAdmin();       
                    }
                    break;
                case 4:
                    notExit = false;
                    break;
                default:
                    break;
            }
        }
    }

    
    // Display Methods
    public static void adminMenuLev1(String[][] menuItemArr) {          
        for(int i=0; i<menuItemArr.length; i++) {
            System.out.println((i+1) + ". " + menuItemArr[i][0]);
        }
        System.out.print("\n");
    }
    
    public static void adminMenuLev2(String[][] menuItemArr, int choice) {    
        choice = choice - 1;
        
        System.out.println("\n");
        for(int i=1; i<menuItemArr[choice].length; i++) {
            System.out.println((i) + ". " + menuItemArr[choice][i]);
        }
        System.out.print("\n");
    }
    
    public static void loginNotify() {
        System.out.printf("%33s\n", centerString(" Already Logged In! ", 33, '-'));
        System.out.println("|   You are already logged in!  |");
        System.out.printf("%33s\n", centerString("", 33, '-'));
    }
    
    public static void displayErrMessage(String title, String description) {
        int titleTextCount = title.length();
        int descTextCount = description.length();
        ListInterface<String> splittedDesc = new ArrayList<>();
        
        // Title
        System.out.printf("\n%33s\n", centerString(" " + title + " ", 33, '-'));
        
        // Content
        if(descTextCount > 33) {
            splittedDesc = splitString(description, 33 - 4);
            
            for(int i=0; i<splittedDesc.size(); i++) {
                System.out.println("| " + splittedDesc.get(i) + " |");
            }
        } else {
            System.out.printf("| %29s |\n", centerString(description, 29));
        }
        // Footer
        System.out.printf("%33s\n\n", centerString("", 33, '-'));
    }
    
    // Operation Methods
    public static void adminActionLev2(int choiceAdminLevel1,int choiceAdminLevel2) {
        if(choiceAdminLevel1 == 1) {
            // Votee
            switch(choiceAdminLevel2) {
                case 1:
                    // Create Votee Function
                    boolean validCreateVoteeInput = false;
                    
                    while(!validCreateVoteeInput) {
                            // - Get new Votee details
                        System.out.println("");
                        System.out.println("Please enter the details of the new votee in the format(Name, Description)");
                        System.out.println("Example: Joshua Koh, A popular Malaysian singer and songwriter.");
                        System.out.print("New Votee Details: ");
                        String newVoteeDetails = sc.nextLine();

                        // Split string by character ","
                        String[] newVoteeDetailArr = newVoteeDetails.split(", ");

                        if(newVoteeDetailArr.length == 2) {
                            // Assign new Votee detail
                            Votee newVotee = new Votee(newVoteeDetailArr[0], newVoteeDetailArr[1]);

                            // Create New Votee
                            IdolVoting.getPollLinkedList().getEntry(IdolVoting.getCurVotingPollIndexAvailable() + 1).addVotee(newVotee);
                            
                            // break loop
                            validCreateVoteeInput = true;
                            
                            // Successful Message
                            System.out.println("New Votee (" + newVotee.getName()+ ") created successfully!!");
                        } else if (newVoteeDetailArr.length < 2) {
                            
                            displayErrMessage("Invalid Input!", "You have entered too few information. Please try again!");
                        } else {
                            displayErrMessage("Invalid Input!", "You have entered too much information. Please try again!");
                        }  
                    }
                    break;
                case 2:
                    // Update Votee Information
                    boolean validUpdateVoteeInput = false;
                    
                    while(!validUpdateVoteeInput) {
                        // Get Votee List in the poll
                        int updateVoteePollIndex = IdolVoting.getCurVotingPollIndexAll();
                        ListInterface<Votee> updateVoteeList = IdolVoting.getPollLinkedList().getEntry(updateVoteePollIndex + 1).getVoteeList();
                        
                        // If search result is not null
                        if(!updateVoteeList.isEmpty() && updateVoteeList != null) {
                            boolean validselectedVoteeIndex = false;
                            
                            while(!validselectedVoteeIndex) {
                                IdolVoting.displayVoteeList(updateVoteeList);

                                // Get confirmed Votee
                                System.out.print("Please select the Votee you would like to update: ");
                                int selectedVoteeIndex = sc.nextInt();
                                sc.nextLine();

                                // Check if valid selectedVoteeIndex
                                if(selectedVoteeIndex > 0 && selectedVoteeIndex < updateVoteeList.size()) {
                                    boolean validUpdatedVoteeDetails = false;

                                    while(!validUpdatedVoteeDetails) {
                                        // Get updated detail of the selected Votee
                                        System.out.println("Please enter the updated detail: ");

                                        System.out.println("Old: " + updateVoteeList.getEntry(selectedVoteeIndex).getId() + ", " + updateVoteeList.getEntry(selectedVoteeIndex).getName() + ", " +
                                                            updateVoteeList.getEntry(selectedVoteeIndex).getDescription());
                                        System.out.print("New: " + updateVoteeList.getEntry(selectedVoteeIndex).getId() + ", ");

                                        String updatedVoteeDetails = sc.nextLine();

                                        // Split string by character ","
                                        String[] updatedVoteeDetailArr = updatedVoteeDetails.split(", ");

                                        // Check if the input is valid (exactly 2 parameter)
                                        if(updatedVoteeDetailArr.length == 2) {
                                            Votee updatedVotee = new Votee(updatedVoteeDetailArr[0],updatedVoteeDetailArr[1]);

                                            // Update the selected Votee detail
                                            IdolVoting.getPollLinkedList().getEntry(updateVoteePollIndex + 1).updateVotee(updateVoteeList.getEntry(selectedVoteeIndex), updatedVotee);
                                            //admin.updateVotee(updatedVotee);

                                            // break loop
                                            // Set all flags to true
                                            validUpdatedVoteeDetails = true;        // This Level
                                            validselectedVoteeIndex = true;         // Upper Level
                                            validUpdateVoteeInput = true;           // Outmost Level

                                            // Successful Message
                                            System.out.println("Updated Votee (" + updatedVotee.getName()+ ") updated successfully!!");
                                        }
                                        else if (updatedVoteeDetailArr.length < 2) {
                                            displayErrMessage("Invalid Input!", "You have entered too few information. Please try again!");
                                        } else {
                                            displayErrMessage("Invalid Input!", "You have entered too much information. Please try again!");
                                        }  
                                    } 
                                } else {
                                    // If invalid selectedVoteeIndex

                                    displayErrMessage("Invalid Number!", "Please enter a number that is in the range of (1 - " + updateVoteeList.size() + "). Please try again");
                                }
                            }  
                        } else {
                            // If search result is null
                            
                            displayErrMessage("Votee Not Found", "There is no Votee in the list. Please try again");
                            validUpdateVoteeInput = true;
                        }
                    }       
                    break;
                case 3:
                    // Delete Votee
                    boolean validDeleteVoteeInput = false;
                    
                    while(!validDeleteVoteeInput) {
                        // Get Votee List in the poll
                        int deleteVoteePollIndex = IdolVoting.getCurVotingPollIndexAll();
                        ListInterface<Votee> deleteVoteeList = IdolVoting.getPollLinkedList().getEntry(deleteVoteePollIndex + 1).getVoteeList();
                        
                        // If search result is not null
                        if(!deleteVoteeList.isEmpty() && deleteVoteeList != null) {
                            boolean validSelectedVoteeIndex = false;
                            
                            while(!validSelectedVoteeIndex) {                       
                                IdolVoting.displayVoteeList(deleteVoteeList);

                                // Get confirmed Votee
                                System.out.print("Please select the Votee you would like to delete: ");
                                int selectedVoteeIndex = sc.nextInt();
                                sc.nextLine();

                                // Check if valid selectedVoteeIndex
                                if(selectedVoteeIndex > 0 && selectedVoteeIndex <= deleteVoteeList.size()) {
                                    boolean validConfirmDeleteVoteeInput = false;
                                    
                                    while(!validConfirmDeleteVoteeInput) {
                                        // Print selected Votee detail
                                        System.out.println("Below is the detail of the selected Votee: ");
                                        System.out.println("Votee ID: " + deleteVoteeList.getEntry(selectedVoteeIndex).getId() + "\n" +
                                                            "Votee Name: " + deleteVoteeList.getEntry(selectedVoteeIndex).getName() + "\n" +
                                                            "Votee Description: " + deleteVoteeList.getEntry(selectedVoteeIndex).getDescription() + "\n");

                                        // Get confirmation from the admin
                                        System.out.println("Do you confirm to delete the Votee? (Y/N) ");
                                        char deleteVoteeConfirmation = Character.toUpperCase(sc.nextLine().charAt(0));

                                        if(deleteVoteeConfirmation == 'Y') {
                                            Votee deletedVotee = IdolVoting.getPollLinkedList().getEntry(deleteVoteePollIndex).removeVotee(deleteVoteeList.getEntry(selectedVoteeIndex));

                                            // Set all flags to true
                                            validConfirmDeleteVoteeInput = true;    // Current Level
                                            validSelectedVoteeIndex = true;         // Upper Level
                                            validDeleteVoteeInput = true;           // Outmost Level
                                            
                                            // Successful Message
                                            System.out.println("Updated Votee (" + deletedVotee.getName()+ ") deleted successfully!!");
                                        } else if(deleteVoteeConfirmation == 'N') {
                                            validSelectedVoteeIndex = true;
                                        } else {
                                            System.err.println("Please enter a Y-Yes, N-No");
                                        }
                                    }
                                } else {
                                    // If invalid selectedVoteeIndex

                                    System.err.println("\nPlease enter a number that is in the range of (1 - " + deleteVoteeList.size() + "). Please try again\n");
                                }
                            } 
                        } else {
                            // If search result is null
                            
                            System.err.println("\nThere is no Votee in the list that matched the entered name. Please try again\n");
                        }
                    }
                    break;
                case 4:
                    // View Votee List
                    int pollIndex = IdolVoting.getCurVotingPollIndexAll();
                    
                    // If list is empty
                        if(IdolVoting.getPollLinkedList().isEmpty()) {
                            System.err.println("\n\nThere is no polls available to display...\n");
                            break;
                        } else {
                            ListInterface voteeList = IdolVoting.getPollLinkedList().getEntry(pollIndex + 1).getVoteeList();
                            if(voteeList.isEmpty()) {
                                displayErrMessage("Votee Not Found", "There is no Votee in the poll");
                            }
                            IdolVoting.displayVoteeList(voteeList);
                        }
                    break;
                default:
                    break;
            }
        } else if(choiceAdminLevel1 == 2) {
            // Poll
            switch(choiceAdminLevel2) {
                case 1:
                    // Create Poll
                    System.out.print("Enter the poll title: ");
                    String pollName = sc.nextLine(); 
                    
                    // Update the current Voting Poll Index to the latest poll
                    int createPollIndex = IdolVoting.getCurVotingPollIndexAll();
                    
                    // Add new Poll object into pollList
                    IdolVoting.getPollLinkedList().add(new Poll(pollName));
                    
                    
                    // Respond Message
                    System.out.println("The Poll (" + pollName + ") has been created!");
                    break;
                case 2:
                    // Start Poll          
                    boolean validStartPollInput = false;
                    
                    while(!validStartPollInput) {
                        // If list is empty
                        if(IdolVoting.getClosedPolls().isEmpty()) {
                            System.err.println("\n\nThere is no polls available to start...\n");
                            break;
                        } else {
                            IdolVoting.displayClosedPolls();
                        
                            // Get confirmed Votee
                            System.out.print("Please select the Poll you would like to start: ");
                            int startPollInput = sc.nextInt();
                            sc.nextLine();

                            if(startPollInput > 0 && startPollInput <= IdolVoting.getClosedPolls().size()) {
                                boolean validConfirmStartPollInput = false;

                                while(!validConfirmStartPollInput) {
                                    // Get confirmation from the admin
                                    System.out.print("Do you confirm to start this Poll? (Y/N): ");
                                    char startPollConfirmation = Character.toUpperCase(sc.nextLine().charAt(0));

                                    if(startPollConfirmation == 'Y') {
                                        // Get selected Poll from closedPollList and compare with pollLinkedList
                                        int startPollIndex = IdolVoting.getPollLinkedList().indexOf(IdolVoting.getClosedPolls().get(startPollInput - 1));

                                        // Start the selected Poll
                                        IdolVoting.getPollLinkedList().getEntry(startPollIndex + 1).open();

                                        // Change flag status to true
                                        validConfirmStartPollInput = true;      // Current Level
                                        validStartPollInput = true;             // Outmost Level

                                        // Respond Message
                                        System.out.println("The Poll (" + IdolVoting.getPollLinkedList().getEntry(startPollIndex + 1).getName() + ") has started!");
                                    } else if(startPollConfirmation == 'N') {
                                        // Change flag status to true
                                        validConfirmStartPollInput = true;      // Current Level
                                    } else {
                                        System.err.println("Please enter a Y-Yes, N-No");
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    // End Poll
                    boolean validEndPollInput = false;
                        
                    while(!validEndPollInput) {
                        // If list is empty
                        if(IdolVoting.getClosedPolls().isEmpty()) {
                            System.err.println("\n\nThere is no polls available to end...\n");
                            break;
                        } else {
                            IdolVoting.displayAvailablePolls();

                            ArrayList<Poll> availablePollList = IdolVoting.getAvailablePolls();

                            // Get confirmed Votee
                            System.out.print("Please select the Poll you would like to end: ");
                            int endPollInput = sc.nextInt();
                            sc.nextLine();

                            // Check if valid endPollInput
                            if(endPollInput > 0 && endPollInput <= availablePollList.size()) {
                                boolean validConfirmEndPollInput = false;

                                while(!validConfirmEndPollInput) {
                                    // Get confirmation from the admin
                                    System.out.print("Do you confirm to end this Poll? (Y/N): ");
                                    char deleteVoteeConfirmation = Character.toUpperCase(sc.nextLine().charAt(0));

                                    if(deleteVoteeConfirmation == 'Y') {
                                        // Get selected Poll from closedPollList and compare with pollLinkedList
                                        int endPollIndex = IdolVoting.getPollLinkedList().indexOf(IdolVoting.getAvailablePolls().get(endPollInput - 1));

                                        // Start the selected Poll
                                        IdolVoting.getPollLinkedList().getEntry(endPollIndex + 1).end();

                                        // Set all flags to true
                                        validConfirmEndPollInput = true;            // Current Level
                                        validEndPollInput = true;                   // Outmost Level
                                        
                                        // Respond Message
                                        System.out.println("The Poll (" + IdolVoting.getPollLinkedList().getEntry(endPollIndex + 1).getName() + ") has ended!");
                                    } else if(deleteVoteeConfirmation == 'N') {
                                        validConfirmEndPollInput = true;
                                    } else {
                                        System.err.println("Please enter a Y-Yes, N-No");
                                    }
                                }
                            } else {
                                // If invalid endPollInput

                                System.err.println("\nPlease enter a number that is in the range of (1 - " + availablePollList.size() + "). Please try again\n");
                            } 
                        }
                    }
                    break;
                case 4:
                    // View Poll List
                    
                    // If list is empty
                        if(IdolVoting.getClosedPolls().isEmpty()) {
                            System.err.println("\n\nThere is no polls available to display...\n");
                            break;
                        } else {
                            IdolVoting.displayAllPolls();
                        }
                    break;
                default:
                    break;
            }
        } 
    }
    
    // Utils Methods
    public static void loginAdmin() {
        int loginTry = 0;
        //Check if user logged in
        if (!IdolVoting.isIsLogged()) {
            //login only attempt less than 3 times
            while (!IdolVoting.isIsLogged() && loginTry < 3) {
                System.out.println("");
                System.out.printf("%37s\n".replace(' ', '-'), centerString("ADMIN Login", 37));
                System.out.printf("~ Username  : ");
                String tempUsername = sc.nextLine();
                System.out.printf("~ Password  : ");
                String tempPassword = sc.nextLine();

                //Verify login detail with linked list contain
                for (int i = 1; i <= IdolVoting.getVoterLinkedList().getNumberOfEntries(); i++) {
                    if (IdolVoting.getAdmin().validateAccount(tempUsername, tempPassword)) {
                        IdolVoting.setIsLogged(true);

                        System.out.println("\nLogin Successful!!");
                        break;
                    }
                }
                if (!IdolVoting.isIsLogged()) {
                    System.out.println("Login Failed, try again...(Attempt " + (loginTry + 1) + ")\n");
                    loginTry++;
                }

            }
        } else {
            // Logout user
            logoutAdmin();
        }
        System.out.println("\nBack to menu....");

    }
    
    public static void logoutAdmin() {
        System.out.printf("Do you wish to logout? (\"Y\" to log out): ");
        char logoutAccChoice = Character.toUpperCase(sc.next().charAt(0));

        if (logoutAccChoice == 'Y') {
            System.out.println("Admin " + IdolVoting.getAdmin().getName()
                    + "(Username: " + IdolVoting.getAdmin().getUsername() + ") has successfully logout.");
            IdolVoting.setIsLogged(false);
        }
    }
    
    public static String centerString(String s, int size) {
        return centerString(s, size, ' ');
    }

    public static String centerString(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }
    
    public static ListInterface<String> splitString(String text, int n) {
        ArrayList<String> splittedCharList = new ArrayList<>();
        
        String[] results = text.split("(?<=\\G.{" + n + "})");
        
        for(int i=0; i<results.length; i++) {
            splittedCharList.add(results[i]);
        }

        return splittedCharList;
    }
}
