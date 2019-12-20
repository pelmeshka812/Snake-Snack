package socket;

import annotation.MyAnno;
import model.Dot;
import model.Snake;

import java.io.*;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.List;

public class Client {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String nickname;

    public void connect(String ip, int port, String nickname) throws IOException {
        socket = new Socket(ip, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        this.nickname = nickname;

        out.println(nickname);
    }
    // Отправляем объект, который мы преобразуем в json,  на сервер
    public  void sendData(Object data){
        String json = writeObjectToJson(data);
        out.println(json);
    }
public void setMessageFromServer(String str){
        if(str.equals(""))
}
//Парсим объект в json
    public String writeObjectToJson(Object data) {
        StringBuilder stringBuilder = new StringBuilder("{");
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(MyAnno.class)) {
                MyAnno anno = field.getAnnotation(MyAnno.class);
                try {
                    Object o = field.get(data);
                    stringBuilder.append(anno.name()).append(":");
                    if (o instanceof List) {
                        stringBuilder.append(writeListToJson((List) o));

                    }
                    stringBuilder.append(field.get(data)).append(";");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
// Парсим лист в json
    private String writeListToJson(List data) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Object item : data) {
            Field[] fields = data.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(MyAnno.class)) {
                    MyAnno anno = field.getAnnotation(MyAnno.class);
                    try {
                        Object o = field.get(data);
                        stringBuilder.append(anno.name()).append(":");
                        if (o instanceof List) {
                           stringBuilder.append(writeListToJson((List) o));
                        } else {
                            stringBuilder.append(o);
                        }
                        stringBuilder.append(",");

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
