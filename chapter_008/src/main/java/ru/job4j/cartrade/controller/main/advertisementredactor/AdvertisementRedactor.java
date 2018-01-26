package ru.job4j.cartrade.controller.main.advertisementredactor;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.cartrade.controller.authorization.UserIdentification;
import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.model.car.Body;
import ru.job4j.cartrade.model.car.Car;
import ru.job4j.cartrade.model.car.Engine;
import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.IAdvertisementDAO;
import ru.job4j.cartrade.storage.dao.ICarDAO;
import ru.job4j.cartrade.storage.dao.IUserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import  javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class MainPage описывает контроллер редактора объявления.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 25.01.2018
 * */
public class AdvertisementRedactor extends HttpServlet {
    /**логгер.*/
    private static final Logger LOGGER = LogManager.getLogger(AdvertisementRedactor.class);

    /**
     * Возвращает страницу редактора объявлений.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/cartrade/view/main/advertisementredactor/advertisementredactor.jsp");
        requestDispatcher.forward(req, resp);


    }
    /**
     * Добавляет обюявление.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            byte[] bPhoto = null;
            Map<String, String> parametrs = new HashMap<>();
            for (FileItem item : items) {
                if (item.isFormField()) {
                    parametrs.put(item.getFieldName(), item.getString());
                } else {
                    bPhoto = item.get();
                }
            }

            HttpSession session = req.getSession();
            UserIdentification identification = (UserIdentification) session.getAttribute("identification");
            Storage storage = new Storage();
            storage.open();
            IUserDAO userDAO = storage.getUserDAO();
            User user = userDAO.get(identification.getId());
            ICarDAO carDAO = storage.getCarDAO();
            Car car = new Car();
            car.setModel(parametrs.get("model"));
            Body body = new Body(parametrs.get("bodytype"), parametrs.get("bodycolor"));
            car.setBody(body);
            Engine engine = new Engine(parametrs.get("engine"));
            car.setEngine(engine);
            Photo photo = new Photo();
            photo.setFile(bPhoto);
            car.getPhotos().add(photo);
            carDAO.persist(car);
            car.getOwners().add(user);
            user.getCars().add(car);
            Advertisement advertisement = new Advertisement();
            advertisement.setProduct(car);
            advertisement.setSeller(user);
            advertisement.getComments().add(parametrs.get("comment"));
            IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
            advertisementDAO.persist(advertisement);
            storage.submit();
            resp.sendRedirect(String.format("%s/main", req.getContextPath()));
        } catch (FileUploadException e) {
            LOGGER.error(e);
            resp.sendError(501);
        }



    }
}
