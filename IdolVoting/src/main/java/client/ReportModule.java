/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import adt.ArrayList;
import adt.ListInterface;
import entity.Poll;
import entity.Report;
import entity.Votee;



import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**S
 *
 * @author limky
 */
public class ReportModule {

    public static void main(String args[]){
        ReportModule rp = new ReportModule();
        //rp.generateSummaryReport();
        rp.generateDetailedReport();
    }

    
    public void generateDetailedReport(){
        Report<String, Integer> rpt = new Report<String, Integer>();
        

        // Get data result from the main driver (IdolVoting.java)
        Poll currentPoll = IdolVoting.getPollLinkedList().getEntry(IdolVoting.getCurVotingPollIndexAll() + 1);
  
        ListInterface<Votee> sortedVoteeResultList = currentPoll.descRanking();
        ListInterface<Integer> sortedVoteCountResultList = new ArrayList<>();;
        
        for(int i=0; i<sortedVoteeResultList.size(); i++) {
            sortedVoteCountResultList.set(i, currentPoll.getPollStatus().getVoteCount().get(sortedVoteeResultList.get(i)));
        }
        
        for(int i=0; i<currentPoll.getPollStatus().getVoteCount().size(); i++) {
            rpt.addADT(sortedVoteeResultList.get(i).getName(), sortedVoteCountResultList.get(i));
        }

//        rpt.addADT("ABC", 1);
//        rpt.addADT("GHI", 4);
//        rpt.addADT("CDE",2);
//        rpt.addADT("Singer XYZ", 30);
//        rpt.addADT("Gigi", 12);
        
        JFrame frame = new JFrame("Detailed Report");
        JTextArea textArea = new JTextArea(20,40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
        String formattedDateTime = timeNow.format(format);
        
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
        textArea.setEditable(false);
        
        textArea.append("Detailed Report for Voting\n");
        textArea.append("Report Generated on " + formattedDateTime + "\n\n'");
        String fmt = String.format("%-15s %-30s %-15s %-15s\n", "Position", "Idol Name", "Vote Count", "Percentage(%)");
        textArea.append("Total Amount of Idol : " + rpt.getNumberOfEntries() + "\n\n" + fmt);
        textArea.append(rpt.toString() + "\n");
        textArea.append(String.format("%-15s %75d", "Total Votes :", rpt.allTotalVotes()));
    }
    
    public void generateSummaryReport(){
        Report<String, Integer> rpt = new Report<String, Integer>();
        

        // Get data result from the main driver (IdolVoting.java)
        Poll currentPoll = IdolVoting.getPollLinkedList().get(IdolVoting.getCurVotingPollIndexAvailable());
        
        ListInterface<Votee> sortedVoteeResultList = currentPoll.descRanking();
        ListInterface<Integer> sortedVoteCountResultList = new ArrayList<>();;
        
        for(int i=0; i<sortedVoteeResultList.size(); i++) {
            sortedVoteCountResultList.set(i, currentPoll.getPollStatus().getVoteCount().get(sortedVoteeResultList.get(i)));
        }
        
        for(int i=0; i<currentPoll.getPollStatus().getVoteCount().size(); i++) {
            rpt.addADT(sortedVoteeResultList.get(i).getName(), sortedVoteCountResultList.get(i));
        }

//        rpt.addADT("ABC", 1);
//        rpt.addADT("GHI", 4);
//        rpt.addADT("CDE",2);
//        rpt.addADT("Singer XYZ", 30);
//        rpt.addADT("Gigi", 12);
        
        JFrame frame = new JFrame("Summary Report");
        JTextArea textArea = new JTextArea(20,40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
        String formattedDateTime = timeNow.format(format);
        
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
        textArea.setEditable(false);
        
        textArea.append("Summary Report for Voting\n");
        textArea.append("Report Generated on " + formattedDateTime + "\n\n");
        textArea.append("Total Amount of Idol : " + rpt.getNumberOfEntries() + "\n\n");
        textArea.append("Total Amount of Voters :" + rpt.allTotalVotes() + "\n");
        textArea.append("Top 3 Amount of Votes on Idol : \n" + rpt.toStringTop3() + "\n\n");
        String percentage = String.format("%.2f", rpt.percentageHighestOverAll());
        textArea.append("There are " + percentage + "% of the votes on the Winner!");
        
        
    }
}

