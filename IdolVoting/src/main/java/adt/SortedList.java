package adt;

import entity.Poll;
import java.util.Comparator;
import java.util.Iterator;

public class SortedList <T extends Comparable<T>> implements SortedListInterface<T> {

    private Node firstNode;
    private int numberOfEntries;

    public SortedList() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean add(T newEntry) { // need to aware the CAPITAL LETTER
        Node newNode = new Node(newEntry);
        Node currentNode = firstNode;
        Node nodeBefore = null;

        //1st condition: The list is empty or the entry is equal or smaller than the first entry in the list 
        if (firstNode == null || newEntry.compareTo(firstNode.data) <= 0) {
            if (firstNode != null) {
                
                //The new entry that having the same value with the entry in the list will not be allowed
                //false value will be returned
                if (newEntry.compareTo(firstNode.data) == 0) {
                    return false;
                    
                // The new entry is added into the sorted list successfully
                } else {
                    newNode.next = firstNode;
                    firstNode = newNode;
                }
            } else {
                newNode.next = firstNode;
                firstNode = newNode;
            }
        } else {
            //Keep looping when the currentNode is not null and the new entry is greater than the currentNode.data
            while (currentNode != null && newEntry.compareTo(currentNode.data) > 0) {
                nodeBefore = currentNode;
                currentNode = currentNode.next;

            }
            if (currentNode != null) {
                //The new entry that having the same value with the entry in the list will not be allowed
                //False value will be returned
                if (newEntry.compareTo(currentNode.data) == 0) {
                    return false;
                    
                //The new entry is added into the sorted list successfully
                } else {
                    newNode.next = currentNode;
                    nodeBefore.next = newNode;
                }
            //The new entry is added into the sorted list successfully
            } else {
                newNode.next = currentNode;
                nodeBefore.next = newNode;
            }

        }
        numberOfEntries++;
        return true;
    }

    // private method 
    private boolean search(Node currentNode, T anEntry) {
        boolean found;

        if (currentNode == null) {
            found = false;
        } else if (anEntry.compareTo(currentNode.data) == 0) {
            found = true;
        } else {
            found = search(currentNode.next, anEntry);
        }
        return found;
    }

    @Override
    public boolean contains(T anEntry) {
        //return search(firstNode, anEntry);

        boolean found = false;
        Node tempNode = firstNode;
        int count = 1;

        if (isEmpty()) {
            found = false;
        } else {
            if (tempNode != null) {
                //Loop to continue for checking the next data
                //1st Condition: count must be less than the number of entries to avoid null
                //2nd Condition: the entry must greater than the data inside the list
                while (count < numberOfEntries && anEntry.compareTo(tempNode.data) > 0) {
                    tempNode = tempNode.next;
                    count++;
                }

                //The entry was found which means the list contains the entry
                if (tempNode.data.compareTo(anEntry) == 0) {
                    found = true;
                }
            }
        }
        return found;
    }

    @Override
    public T get(Integer selectedPosition) {
        int count = selectedPosition - 1;
        Node findNode;

        if (isEmpty()) {
            return null;
        } else {
            //To ensure that the selected position is not out of the range of the list
            if (count >= 0 & count < numberOfEntries) {
                findNode = firstNode;
                //Loop only within the list to avoid null value
                for (int i = 0; i < count; i++) {
                    findNode = findNode.next;
                }
                //return the data if it is found
                return findNode.data;
            } else {
                //return null if it is not exists in the list
                return null;
            }
        }
    }

    @Override
    public T get(T anEntry) {
        T result = null;
        int count = 0;
        Node findNode;

        if (isEmpty()) {
            return null;
        } else {
            findNode = firstNode;
            //Loop if the firstNode is not null AND
            //count for the loop is not greater than numberOfEntries
            while (findNode != null && count <= numberOfEntries) {
                //Return data if it is in the list
                if (anEntry.compareTo(findNode.data) == 0) {
                    result = findNode.data;
                    //System.out.println("RESULT" + findNode.data);
                }
                //Make the firstNode point to the next node for comparison
                findNode = findNode.next;
                count++;
            }
        }
        return result;
    }

    @Override
    public void sortAscending(SortedList listA, Comparator A) {

        // create a empty list
        SortedList emptyList = new SortedList<>();
        
        //Assign the forstNode of the listA to the newNode
        Node newNode = firstNode;

        //If the listA is not empty
        while (newNode != null) {
            //Assign the newNode to the tempNode as a moving node to check all data
            Node tempNode = new Node(newNode.data);
            //Assign emptyList.firstNode to the currentNode
            Node currentNode = emptyList.firstNode;
            Node nodeBefore = null;

            //If the currentNode is null or the tempNode.data is smaller than the currentNode.data
            if (currentNode == null || A.compare(tempNode.data, currentNode.data) < 0) {
                //Assign the firstNode of the emptyList to the next node of the tempNode
                tempNode.next = emptyList.firstNode;
                //Assign tempNode to the firstNode of the emptyList
                emptyList.firstNode = tempNode;

            } else {
                //If the currentNode is not null and the tempNode.data is greater than currentNode.data
                while (currentNode != null && A.compare(tempNode.data, currentNode.data) > 0) {
                    //Assign currentNode to the nodeBefore
                    nodeBefore = currentNode;
                    //Assign the currentNode to the next node to move forward and check next data
                    currentNode = currentNode.next;
                }

                //Add the data into the list when it located between 2 nodes (notin first position or last)
                tempNode.next = currentNode;
                nodeBefore.next = tempNode;
            }
            
            //Assign new node to the next node
            newNode = newNode.next;
        }

        //listA.firstNode = emptyList.firstNode;
        System.out.println(emptyList);
    }

    @Override
    public void sortDescending(SortedList listA, Comparator A) {
        // create a empty list
        SortedList emptyList = new SortedList<>();
        
        //Assign the firstNode of the listA to the newNode
        Node newNode = firstNode;

        //If the listA is not empty
        while (newNode != null) {
            Node tempNode = new Node(newNode.data);
            Node currentNode = emptyList.firstNode;
            Node nodeBefore = null;

            //If the currentNode is null or the tempNode.data is greater than the currentNode.data
            if (currentNode == null || A.compare(tempNode.data, currentNode.data) > 0) {
                //Assign the firstNode of the emptyList to the next node of the tempNode
                tempNode.next = emptyList.firstNode;
                //Assign tempNode to the firstNode of the emptyList
                emptyList.firstNode = tempNode;

            } else {
                //If the currentNode is not null and the tempNode.data is smaller than currentNode.data
                while (currentNode != null && A.compare(tempNode.data, currentNode.data) < 0) {
                    //Assign currentNode to the nodeBefore
                    nodeBefore = currentNode;
                    //Assign the currentNode to the next node to move forward and check next data
                    currentNode = currentNode.next;
                }

                //Add the data into the list when it located between 2 nodes (not in first position or last)
                tempNode.next = currentNode;
                nodeBefore.next = tempNode;
            }
            //Assign new node to the next node
            newNode = newNode.next;
        }

        //listA.firstNode = emptyList.firstNode;
        System.out.println(emptyList);
    }

    @Override
    public SortedListInterface filter(SortedList listA, String str) {
        int i;
        String tempStr = str;
        String output;
        Character tempChar = str.charAt(0);
        SortedListInterface<T> tempList = new SortedList<>();

        //To capitalize the first letter of the word
        if (Character.isLetter(tempChar)) {
            output = tempStr.substring(0, 1).toUpperCase() + tempStr.substring(1);
        } else {
            //remain the same if the character is not a letter (such as number)
            output = tempStr;
        }

        //If the listA is not empty
        if (!listA.isEmpty()) {
            //Assign the first node to the temp node
            Node newNode = firstNode;
            //Assign new node to the temp node
            Node tempNode = newNode;

            //If the tempNode is not null, it means that it doesn't reach the node after the last node in the list
            while (tempNode != null) {
                //Loop the whole list to filter the data by using contains method
                for (i = 0; i < numberOfEntries; i++) {
                    //If the data does contains the word, add it into the empty list for display later
                    if ((tempNode.data.toString().contains(output)) == true) {
                        //System.out.println(newList.toString());
                        //System.out.println(tempNode.data.toString() + "inside");
                        tempList.add(tempNode.data);
                    }
                    //System.out.println(tempNode.data.toString() + "outside");
                    tempNode = tempNode.next;
                }
            }
        }
        //display the list which contains the data that have been filtered
        System.out.println(tempList);
        return tempList;
    }

    @Override
    public T getLast() {
        //Reuse get() method to retrieve the lastNode.data
        return (get(numberOfEntries));
    }

    @Override
    public boolean isEmpty() {
        return (numberOfEntries == 0);
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
        firstNode = null;
    }

    @Override
    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            //Display the currentNode.data
            outputStr += currentNode.data.toString() + "\n";;
            //Make the currentNode point to the next node
            currentNode = currentNode.next;
        }
        return outputStr;
    }

    @Override
    public Iterator<T> getIterator() {
        return new ListIterator();
    }

    @Override
    public Object get(SortedListInterface<Poll> Poll) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private class ListIterator implements Iterator<T> {

        private Node currentNode = firstNode;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() { // pointing to new node
            T currentData = null;
            if (hasNext()) {
                currentData = currentNode.data;
                currentNode = currentNode.next;
            }
            return currentData;
        }

    }

    private class Node {

        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            next = null;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

    }

}
