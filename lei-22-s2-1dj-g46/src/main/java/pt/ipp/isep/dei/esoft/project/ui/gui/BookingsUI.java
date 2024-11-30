package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CheckVisitsController;
import pt.ipp.isep.dei.esoft.project.domain.Message;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.ListView;


public class BookingsUI implements Initializable{
    private int beginDateInt;
    private int endDateInt;
    private LocalDate beginDate;
    private LocalDate endDate;
    private boolean validDate = true;
    private CheckVisitsController controller = new CheckVisitsController();
    private List<Message> messageList = new ArrayList<>();
    private List<Date> originalDateList;
    private Scene preScene;
    @FXML
    private Button back;
    @FXML
    private ListView<String> dateList;
    @FXML
    private DatePicker endDatePicker;

    @FXML
    private DatePicker beginDatePicker;

    @FXML
    private Button dateSubmission;

    @FXML
    private Text invalid;

    /**
     * This method goes to the previous menu
     * @param event
     */
    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show();
    }

    /**
     * This method is used to set a begin date
     * @param event
     */
    @FXML
    void chooseBeginDate(ActionEvent event) {
        beginDate = beginDatePicker.getValue();
        int year = beginDate.getYear();
        int month = beginDate.getMonthValue();
        int day = beginDate.getDayOfMonth();
        beginDateInt = Integer.parseInt(String.format("%4d%02d%02d",year,month,day));
        endDatePicker.setVisible(true);
    }
    /**
     * This method is used to set an end date
     * @param event
     */
    @FXML
    void chooseEndDate(ActionEvent event){
        endDate = endDatePicker.getValue();
        int year = endDate.getYear();
        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        endDateInt = Integer.parseInt(String.format("%4d%02d%02d",year,month,day));
        if (endDateInt < beginDateInt) {
            invalid.setVisible(true);
            validDate = false;
        } else {
            invalid.setVisible(false);
            dateSubmission.setVisible(true);
            validDate = true;
        }
    }
    /**
     * This method is used to set a date interval and send the messages that fit that interval into the next scene
     * @param event
     */
    @FXML
    void submitDate(ActionEvent event) throws IOException{
        if (validDate) {
            messageList = changeListWithTimePeriod();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/BookingsSort.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            BookingSortUI bookingSortUI = fxmlLoader.getController();
            bookingSortUI.setPreScene(dateSubmission.getScene(), messageList);
            bookingSortUI.initializeWithMessageList(messageList);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("See Booking Request List");
            stage.setScene(scene);
            stage.show();
        } else {
            back(event);
        }
    }

    /**
     * Change list according to the specified date interval
     * @return filtered list
     */
    public List<Message> changeListWithTimePeriod(){
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i).getPreferredDateInComparableInt() < beginDateInt || messageList.get(i).getPreferredDateInComparableInt() > endDateInt){
                messageList.remove(messageList.get(i));
            }
        }
        return messageList;
    }

    /**
     * This will initialize the scene with the dates
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> items = new ArrayList<>();
        try{
            messageList = controller.getMessages();
            originalDateList = controller.getDates(messageList);
            int day, month, year;
            String string;
            for (int i = 0; i < originalDateList.size(); i++) {
                day = originalDateList.get(i).getDate();
                month = originalDateList.get(i).getMonth() + 1;
                year = originalDateList.get(i).getYear() + 1900;
                string = String.format("%02d-%02d-%4d", day, month, year);
                items.add(string);
            }
        } catch (NullPointerException e){
            items.add("no data available");
        }
        dateList.getItems().addAll(items);
    }

    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }
    public void setMessageList(List<Message> messageList){this.messageList = messageList;}
}
