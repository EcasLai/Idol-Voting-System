/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import adt.ReportInterface;

/**
 *
 * @author limky
 */

public class Report <T extends Comparable<T>, Y> implements ReportInterface<T, Y>{

    private T[] idolName;
    private Y[] idolTotalVotes;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 100;

  public Report() {
    this(DEFAULT_CAPACITY);
  }
  
  public Report(int initialCapacity) {
    numberOfEntries = 0;
    idolName = (T[]) new Comparable[initialCapacity];
    idolTotalVotes = (Y[]) new Object[initialCapacity];
  }
  
    @Override
    public boolean addADT(T newEntryName, Y newEntryTotalVotes){ //add value
        int i = 0;
        while (i < numberOfEntries && newEntryName.compareTo(idolName[i]) > 0) {
          i++;
        }
        makeRoom(i + 1);
        idolName[i] = newEntryName;
        idolTotalVotes[i] = newEntryTotalVotes;
        numberOfEntries++;
        return true;
    }

    @Override
    public int allTotalVotes() {//calculate total votes
        if(isEmpty()){
            return 0;
        }
        else{
            int total = 0;
            for(int i = 0; i < numberOfEntries; i++){
                total += Integer.parseInt(idolTotalVotes[i].toString());
            }
            return total;
        }
    }

    @Override
    public boolean sortList() { //bubble sort
        boolean sorted = false;
        int n = numberOfEntries;

        while (!sorted) {
            sorted = true;
             for (int i = 0; i < n - 1; i++) {
                if ((int)idolTotalVotes[i] < (int)idolTotalVotes[i + 1]) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
            n--;
        }

        return true;
    }
    private void swap(int i, int j) {
        T tempName = idolName[i];
        Y tempTotalVotes = idolTotalVotes[i];
        idolName[i] = idolName[j];
        idolTotalVotes[i] = idolTotalVotes[j];
        idolName[j] = tempName;
        idolTotalVotes[j] = tempTotalVotes;
}
    
    @Override
    public double percentageHighestOverAll() { //calculate the highest voted idol's vote over all votes
        double percentage = 0.0;
        int highest = 0;
        for(int i = 0; i < numberOfEntries; i++){
            if(Integer.parseInt(idolTotalVotes[i].toString()) > highest){
                highest = Integer.parseInt(idolTotalVotes[i].toString());
            }
        }
        try{
            percentage = ((double)highest / (double)allTotalVotes()) * 100.0;
            return percentage;
        }catch(ArithmeticException ex){
            return 0;
        }
    }
    
    private void makeRoom(int newPosition) {
    int newIndex = newPosition - 1;
    int lastIndex = numberOfEntries - 1;

    for (int index = lastIndex; index >= newIndex; index--) {
      idolName[index + 1] = idolName[index];
      idolTotalVotes[index + 1] = idolTotalVotes[index];
    }
  }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    
    public double percentVoted(int index){ //Calculate the percentage of the vote over total for individual idol
        return (Double.parseDouble(idolTotalVotes[index].toString())/(double)allTotalVotes()) * 100.0;
    }

    @Override
    public String toString() {
        String outputStr = "";
        sortList();
        for (int index = 0; index < numberOfEntries; ++index) {
                String fmt = String.format("  %-20d %-40s %-10d %-10.2f%%", index+1,
                      (idolName[index]).toString(), (int)idolTotalVotes[index], percentVoted(index));
              outputStr += fmt;
              if(index == 0){
                  outputStr += "(Winner)";
              }
              outputStr += "\n";
         }
        return outputStr;
    }
    
    
    @Override
    public String toStringTop3(){
        String outputStr = "";
        sortList();
        outputStr += "Winner : " + idolName[0] + "\nVotes Count : " + idolTotalVotes[0] +
                "\n\nSecond Place : " + idolName[1] + "\nVotes Count : " + idolTotalVotes[1] +
                "\n\nThird Place : " + idolName[2] + "\nVotes Count : " + idolTotalVotes[2];
        return outputStr;
    };
}

