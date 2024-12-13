package com;

import com.dao.OrderRepository;
import com.utils.ConnectionProvider;
import com.utils.DbException;
import org.flywaydb.core.Flyway;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Получаем экземпляр ConnectionProvider для работы с базой данных
            ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

            // Добавляем ConnectionProvider в контекст
            sce.getServletContext().setAttribute("connectionProvider", connectionProvider);

            // Настроим Flyway с использованием HikariDataSource
            Flyway flyway = Flyway.configure()
                    .dataSource(connectionProvider.getDataSource())  // передаем пул соединений
                    .load();

            // Выполняем миграции
            flyway.migrate();

            // Инициализируем OrderRepository и сохраняем его в контексте
            OrderRepository orderRepository = new OrderRepository(connectionProvider);
            sce.getServletContext().setAttribute("orderRepository", orderRepository);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при инициализации приложения", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Очистка ресурсов при завершении работы приложения
        ConnectionProvider.getInstance().close();
    }
}




