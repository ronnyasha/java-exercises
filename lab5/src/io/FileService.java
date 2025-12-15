package io;

import java.io.*;

public final class FileService {
    private FileService() {}

    public static <T extends Serializable> void saveObject(String path, T obj) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(obj); // default serialization
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T loadObject(String path, Class<T> type) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            Object obj = ois.readObject();
            return type.cast(obj);
        }
    }
}
