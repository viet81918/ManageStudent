package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class Menu<T> {
 
    protected String title; 
    protected ArrayList<T> mChon;
    
    protected Scanner scanner = new Scanner(System.in);
    
    public Menu() {}
    
    public Menu (String td, String[] mc) { 
        title=td;
        mChon= new ArrayList<>();
        for (String s:mc) mChon.add((T) s);
    }
    
    public void display () {
        System.out.println(title);
        System.out.println("---------------------------------");
        for (int i=0; i<mChon.size();i++) 
            System.out.println((i+1)+". "+mChon.get(i));
        System.out.println("---------------------------------");
    }
    
    public void display(List<T> list) {
        System.out.println("List of Customers");
        System.out.println("---------------------------------");
        if(!list.isEmpty()) {
            for (T i : list) {
                System.out.println(i.toString());
            } 
        }
        else
            System.out.println("List is empty");
        System.out.println("---------------------------------");
        System.out.println("Total : " + list.size() + " customers.");
    }
    
    public String getSelected() {
        display(); 
        System.out.print ("Enter selection: ");
        return scanner.nextLine();
    }
    
    public void run() {
        while(true) {
            String n = getSelected();
            execute(n);
            try {
                 if(Integer.parseInt(n) >= mChon.size())
                    break; 
            }
            catch(NumberFormatException e) {
                run();
            }
            
        }
    }
    
    public abstract void execute (String n);
       
}