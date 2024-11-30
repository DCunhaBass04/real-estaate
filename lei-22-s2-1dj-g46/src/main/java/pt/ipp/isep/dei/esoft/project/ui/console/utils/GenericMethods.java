package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GenericMethods {
    /**
     * This method is used to print a list that consists of a certain object
     * @param list the list of <E>
     * @param <E> the object
     */
    public <E> void printFullList(List<E> list){
        if (!list.isEmpty()){
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i+1) + " - " + getItemToString(list.get(i)));
            }
            System.out.println("0 - Exit");
        } else {
            System.out.printf("No items available.%n");
        }
    }

    /**
     * This method is used to get the specific object's toString()
     * @param object the specific object
     * @return the object's toString()
     * @param <E> the object
     */
    public <E> String getItemToString(E object){
        String string;
        if (object instanceof Date){
            int day = ((Date) object).getDate();
            int month = ((Date) object).getMonth() + 1;
            int year = ((Date) object).getYear() + 1900;
            string = String.format("%02d-%02d-%4d", day, month, year);
        } else {
            string = object.toString();
        }
        return string;
    }
}
