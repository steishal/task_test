package com.servlets;

import com.dao.OrderRepository;
import com.models.Order;
import com.utils.ConnectionProvider;
import com.utils.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/orders")
public class OrderController extends HttpServlet {

    private OrderRepository orderRepository;

    @Override
    public void init() throws ServletException {
        // Инициализируем ConnectionProvider из контекста
        ConnectionProvider connectionProvider = (ConnectionProvider) getServletContext().getAttribute("connectionProvider");
        if (connectionProvider == null) {
            throw new ServletException("ConnectionProvider не инициализирован");
        }

        // Инициализируем OrderRepository
        orderRepository = new OrderRepository(connectionProvider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            // Если параметр id не передан, выводим список заказов
            try {
                List<Order> orders = orderRepository.findAll();
                req.setAttribute("orders", orders);
                req.getRequestDispatcher("/WEB-INF/orders.jsp").forward(req, resp);
            } catch (DbException e) {
                throw new ServletException("Ошибка при получении списка заказов", e);
            }
        } else {
            // Если передан параметр id, показываем детали заказа
            try {
                Order order = orderRepository.findById(Integer.parseInt(id));
                if (order == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Заказ не найден");
                } else {
                    req.setAttribute("order", order);
                    req.getRequestDispatcher("/WEB-INF/order-details.jsp").forward(req, resp);
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный ID заказа");
            } catch (DbException e) {
                throw new ServletException("Ошибка при получении информации о заказе", e);
            }
        }
    }
}




