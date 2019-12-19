package dao;

import model.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//Добавить id в таблицу record
public  class RecordsDAO  {
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/snake_shack?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234Qwerty";
    private static final String TABLE = "snake_shack.record";

    private static RecordsDAO instance; // синглтон, чтобы было только одно подключение к бд)

        private Connection connection;

        private RecordsDAO() { // приватный, чтобы получить объект только через метод гет(реализация синглтона)
            try {
                Class.forName(DRIVER_CLASS);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        }

        public static RecordsDAO get() { // тоже часть синглтона
            if (instance == null) instance = new RecordsDAO();
            return instance;
        }

        protected Record parseEntityFrom(ResultSet resultSet) { // получаем объект класса Рекорд из resultSet(jdbc пихает в него значения,которые достает из бд)))))
            Record record = null;
            try {
                record = new Record(
                        resultSet.getString("nickname"),
                        resultSet.getInt("value")
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return record;
        }

        public void insert(Record entity) { // вставка в бд

            try {
                String sql = "INSERT INTO " + TABLE + " VALUES(?, ?)";
                PreparedStatement statement = getPreparedStatement(sql);//специальный класс, который выполняет данный запрос
                statement.setString(1, entity.getNickname()); // вставляет вместо 1 знака вопроса никнейм
                statement.setInt(2, entity.getValue());
                statement.execute(); // выполненяем запрос
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        public Record getRecordByNickname(String nickname) {
            String sql = "SELECT * FROM " + TABLE + " WHERE nickname = ?" ;
            List<Record> result = query(sql, new String[]{nickname}); // выполняем выборку
            return result.isEmpty() ? null : result.get(0);
        }

        public List<Record> getAll() {
           String sql = "SELECT * FROM " + TABLE ;
            return query(sql, new String[0]);
        }


        public void update(Record entity) {
            String sql = "UPDATE " + TABLE + " SET value = ? WHERE nickname = ?";
            try (PreparedStatement statement = getPreparedStatement(sql)) {
                statement.setInt(1, entity.getValue());
                statement.setString(2, entity.getNickname());
               statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        private PreparedStatement getPreparedStatement(String sql) { // метод, создающий preparedStatement с нашим запросом
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return statement;
        }

       private List<Record> query(String sql, String[] values) { // выполнение выюорки
            List<Record> entityList = new ArrayList<>();
            try (PreparedStatement statement = getPreparedStatement(sql)) {
                for (int i = 0; i < values.length; i++) {
                    statement.setNString(i + 1, values[i]);//  вставляем значения вместо знаков вопроса строчку sql
                }
                ResultSet resultSet = statement.executeQuery();// когда наш statement кончил выполнение запроса, выпихиваем из него значения
                while (resultSet.next()) entityList.add(parseEntityFrom(resultSet));
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return entityList;
        }

    public static void main(String[] args) {

    }

    }

