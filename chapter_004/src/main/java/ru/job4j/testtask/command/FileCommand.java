package ru.job4j.testtask.command;



import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;

/**
 * Class XMLCommand формирует поток окманд из XML файла.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.07.2017
 */
public final class FileCommand implements ICommandStream {
    /** Имя файла. */
    private final String fileName;

    /** Указатель на элемент XML файла. */
    private XMLStreamReader reader;

    /** Множество имен команд. */
    private Collection<String> commandsName;
    /**
     * Конструктор для класса FileCommand.
     * @param fileName - имя файла с командами.
     * */
    private FileCommand(String fileName) {
        this.fileName = fileName;
    }


    /**
     * Создает XML файл c потоком команд.
     * @param fileName - имя файла
     * @param commandsName - список имен команд
     * @return файл с потоком команд, null - если не удалось создать поток
     * */
    public static FileCommand openStream(String fileName, Collection<String> commandsName) {
        FileCommand  file = new FileCommand(fileName);
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            file.reader = factory.createXMLStreamReader(new FileInputStream(fileName));
            file.commandsName = commandsName;
        } catch (FileNotFoundException | XMLStreamException e) {
            file = null;
        }
        return file;
    }
    /**
     * Освобождает поток.
     * */
    public void close() {
        try {
            reader.close();
        } catch (XMLStreamException e) {
            System.out.println("Can't close stream");
        }
    }
    /**
     * Возвращает все атрибуты элемента.
     * @param reader - поток элементов
     * @return атрибуты
     * */
    private HashMap<String, String> getAllAttributes(XMLStreamReader reader) {
        HashMap<String, String> attributes = new HashMap<>();
        for (int i = 0; i < reader.getAttributeCount(); i++) {
            attributes.put(reader.getAttributeName(i).getLocalPart(), reader.getAttributeValue(i));
        }
        return attributes;
    }

    /**
     * Возвращает команду из потока данных.
     * @return Command, null - если в пооке отсутствует команда.
     * */
    public Command getCommand() {
        Command command = null;
        try {
            while (reader.hasNext()) {
                reader.next();
                if (reader.isStartElement()) {
                    if (commandsName.contains(reader.getLocalName())) {
                        command = new Command(reader.getLocalName(), getAllAttributes(reader));
                        break;
                    }
                }
            }
        } catch (XMLStreamException e) {
            command = null;
        }
        return command;
    }


}
