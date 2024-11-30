package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CheckVisitsController;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.GenericMethods;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

/**
 * The CheckVisitsUI class represents the user interface for checking visits.
 * It interacts with the CheckVisitsController to retrieve and display visit messages.
 */
public class CheckVisitsUI implements Runnable {
    private final VerifyInput verifyInput = new VerifyInput();
    private final CheckVisitsController controller = new CheckVisitsController();
    private List<Message> messageList = new ArrayList<>();
    private List<Date> dateList;
    private Scanner input = new Scanner(System.in);
    private GenericMethods genericMethods = new GenericMethods();
    private EditAndSortLists editAndSortLists = new EditAndSortLists();
    private boolean nullDate = false;

    /**
     * Runs the check visits user interface.
     * Prints all visit messages by calling the corresponding controller method.
     */
    public void run() {
        messageList = controller.getMessages();
        if(!messageList.isEmpty()) {
            getTimePeriod();
            if (!nullDate){
                sortList();
                getMessageList();
            } else {
                System.out.println("Please insert a valid date range.");
            }
        } else {
            System.out.println("You have no messages.");
        }
        input.nextLine();
    }

    /**
     * This method is used to filter the list of messages, calling the responsible controller's method
     */
    public void sortList(){
        messageList = editAndSortLists.sortListThroughConfigFile(messageList, true);
    }

    /**
     * This method is used to print the list of dates of all messages
     */
    public void getTimePeriod(){
        System.out.printf("Available dates for selection:%n");
        dateList = controller.getDates(messageList);
        genericMethods.printFullList(dateList);
        System.out.print("Please chose begin date.");
        Date beginDate = (Date)Utils.selectsObject(dateList);
        if (beginDate == null){
            nullDate = true;
        } else {
            Date endDate = getEndDate(beginDate);
            if (endDate == null){
                nullDate = true;
            }
            else {
                messageList = controller.changeListWithTimePeriod(messageList, beginDate, endDate);
            }
        }
    }

    /**
     * This method is used to get an end date from the user, verifying if it's a day after the set begin date
     * @param beginDate the begin date set by the user
     * @return the end date
     */
    public Date getEndDate(Date beginDate){
        int day = beginDate.getDate();
        int month = beginDate.getMonth() + 1;
        int year = beginDate.getYear() + 1900;
        int beginDateInt = Integer.parseInt(String.format("%4d%02d%02d",year,month,day));
        boolean valid = false;
        System.out.print("Please chose end date.");
        Date endDate = (Date)Utils.selectsObject(dateList);
        if (endDate == null){
            nullDate = true;
        } else {
            int endDay, endMonth, endYear, endDateInt;
            while (!valid){
                endDay = endDate.getDate();
                endMonth = endDate.getMonth() + 1;
                endYear = endDate.getYear() + 1900;
                endDateInt = Integer.parseInt(String.format("%4d%02d%02d",endYear,endMonth,endDay));
                if (endDateInt >= beginDateInt){
                    valid = true;
                } else {
                    System.out.printf("An interval cannot end in a date prior to its start.%n");
                    endDate = (Date)Utils.selectsObject(dateList);
                }
            }
        }
        return endDate;
    }

    /**
     * This method prints the full list of messages
     */
    public  void getMessageList(){
        int option = -1;
        while (!messageList.isEmpty() && option != 0) {
            genericMethods.printFullList(messageList);
            option = choseFromList(messageList);
            if(option != 0) {
                messageList = respondToXMessage(messageList.get(option - 1), messageList);
            }
        }
    }

    /**
     * This method is used to choose a certain message from the message list
     * @param list message list
     * @return the number if that message in its array
     */
    public  int choseFromList(List<Message> list){
        int option;
        boolean valid;
        do {
            option = verifyInput.verifyInt(input,"option");
            valid = controller.validateAnswer(option, list.size());
        }while(!valid);
        return option;
    }

    /**
     * This method is used to get a full written response from the user, deleting the message which it is responding to from the list
     * @param message the message that is going to be answered
     * @param messageList the message list
     * @return an updated message list
     */
    public List<Message> respondToXMessage(Message message, List<Message> messageList){
        List<Message> mutableMessageList = new ArrayList<>(messageList);
        System.out.println(message);
        String response = verifyInput.verifyComplexString(input, "your response to this booking request");
        try {
            controller.deleteFromRepositoryAndSendEmail(message, response);
            mutableMessageList.remove(message);
        } catch (MessagingException | IOException e){
            System.out.println("There was an unexpected error. The email was not sent.");
        }
        return Collections.unmodifiableList(mutableMessageList);
    }
}
