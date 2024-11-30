package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.*;

public class SaveAndReadData {
    /**
     * This method is used to read data from a binary file, and update the Repositories' instance with that data
     * @param fileName the binary file's name
     * @throws IOException if the file is not found
     * @throws ClassNotFoundException if a class that is in the file is not found
     */
    public void readFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(file);
        Repositories repositories;
        repositories = (Repositories) in.readObject();
        repositories.setInstance(repositories);
        in.close(); file.close();
    }

    /**
     * This method is used to save data from the system to a binary file
     * @param fileName the binary file's desired name
     * @throws IOException
     */
    public void saveFile(String fileName) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);
        Repositories repositories = Repositories.getInstance().clone();
        out.writeObject(repositories);
        out.close(); file.close();
    }
}
