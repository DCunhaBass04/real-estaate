package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CheckVisitsController;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.repository.MessageRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.GenericMethods;
import pt.ipp.isep.dei.esoft.project.ui.gui.menu.AgentUIFX;


import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class BookingSortUI {
    private Scene preScene;
    private List<Message> muttableList;
    private EditAndSortLists editAndSortLists = new EditAndSortLists();
    private GenericMethods genericMethods = new GenericMethods();
    private CheckVisitsController controller = new CheckVisitsController();

    @FXML
    private ListView<String> messageListView;

    @FXML
    private Button back;

    @FXML
    private Button ReplyButton;

    @FXML
    private TextArea ReplyMessage;

    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show();
    }

    /**
     * This method picks the chosen message, the response's content and sends the new response to that message's email and system
     * @param event
     * @throws MessagingException if there's an error while sending the email
     * @throws IOException if there's an error finding the 'config.properties' file
     */
    @FXML
    void reply(ActionEvent event) throws MessagingException, IOException {
        String answer = ReplyMessage.getText();
        String selection = messageListView.getSelectionModel().getSelectedItem();
        String otherMessage;
        boolean sameMessage = false;

        for (int i = 0; i < muttableList.size() && !sameMessage; i++) {
            otherMessage  = muttableList.get(i).toString();
            if (otherMessage.equals(selection)){
                Message message = muttableList.get(i);
                sameMessage = true;
                controller.deleteFromRepositoryAndSendEmail(message, answer);
                MessageRepository messageRepository = Repositories.getInstance().getMessageRepository();
                messageRepository.remove(message);
                muttableList = removeMessageFromList(muttableList, message);
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/BookingDates.fxml"));
//                BookingsUI bookingUI = fxmlLoader.getController();
//                bookingUI.setMessageList(muttableList);
                messageListView.getItems().remove(messageListView.getSelectionModel().getSelectedItem());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Reply sent successfully!");
                alert.showAndWait();
            }
        }

        if (!sameMessage) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Something went wrong while sending the reply.");
            alert.showAndWait();
        }

    }

    /**
     * This method removes an answered message from the list
     * @param messages message list
     * @param message message
     * @return message list without the answered message
     */
    private List<Message> removeMessageFromList(List<Message> messages, Message message){
        List<Message> mutableMessageList = new ArrayList<>(messages);
        mutableMessageList.remove(message);
        return Collections.unmodifiableList(mutableMessageList);
    }
    @FXML
    void selection(MouseEvent event) {
        ReplyMessage.setVisible(true);
        ReplyButton.setVisible(true);
    }

    public void setPreScene(Scene preScene, List<Message> messageList) {
        this.preScene = preScene;
        this.muttableList = messageList;
    }

    /**
     * This method is used to initialize the list view with only the message list specified in the previous class (BookingsUI.java)
     * @param messageList the message list to insert in the list view
     */
    public void initializeWithMessageList(List<Message> messageList){
        messageList = editAndSortLists.sortListThroughConfigFile(messageList, true);
        List<String> items = new ArrayList<>();
        for (int i = 0; i < messageList.size(); i++) {
            items.add(messageList.get(i).toString());
        }
        messageListView.getItems().addAll(items);
        this.muttableList = messageList;
    }
}

//    public  void getMessageList(){
//        int option = -1;
//        while (!messageList.isEmpty() && option != 0) {
//            genericMethods.printFullList(messageList);
//            option = choseFromList(messageList);
//            if(option != 0) {
//                respondToXMessage(messageList.get(option - 1), messageList);
//            }
//        }
//    }
//    public  int choseFromList(List<Message> list){
//        int option;
//        boolean valid;
//        do {
//            option = verifyInput.verifyInt(input,"option");
//            valid = controller.validateAnswer(option, list.size());
//        }while(!valid);
//        return option;
//    }
//
//    public void respondToXMessage(Message message, List<Message> messageList){
//        System.out.println(message);
//        String response = verifyInput.verifyComplexString(input, "your response to this booking request");
//        try {
//            controller.deleteFromRepositoryAndSendEmail(message, response);
//            messageList.remove(message);
//        } catch (MessagingException | IOException e){
//            System.out.println("There was an unexpected error. The email was not sent.");
//        }
//    }

