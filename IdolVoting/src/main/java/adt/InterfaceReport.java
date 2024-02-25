/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author limky
 */
public interface InterfaceReport <T extends Comparable<T>, Y> {
    public boolean addADT(T newEntryName, Y newEntryTotalVotes);
    public int allTotalVotes();
    public double percentageHighestOverAll();
    public String toStringTop3();
    
    public boolean sortList();
    public void clear();
    public int getNumberOfEntries();
    public boolean isEmpty();
}
