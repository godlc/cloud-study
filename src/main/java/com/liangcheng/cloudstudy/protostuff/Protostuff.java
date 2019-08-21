//package com.liangcheng.cloudstudy.protostuff;
//
//import io.protostuff.LinkedBuffer;
//import io.protostuff.Schema;
//import io.protostuff.runtime.RuntimeSchema;
//import org.springframework.objenesis.Objenesis;
//import org.springframework.objenesis.ObjenesisStd;
//
///**
// * @author lc
// * @version 1.0
// * @date 2019/8/15 17:11
// */
//public class Protostuff {
//    private static Objenesis objenesis = new ObjenesisStd(true);
//    @SuppressWarnings("unchecked")
//    public static <T> byte[] serialize(T obj) {
//        Class<T> cls = (Class<T>) obj.getClass();
//        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
//        try {
//            Schema<T> schema = getSchema(cls);
//            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
//        } catch (Exception e) {
//            throw new IllegalStateException(e.getMessage(), e);
//        } finally {
//            buffer.clear();
//        }
//    }
//
//    public static <T> T deserialize(byte[] data, Class<T> cls) {
//        try {
//            T message = objenesis.newInstance(cls);
//            Schema<T> schema = getSchema(cls);
//            ProtostuffIOUtil.mergeFrom(data, message, schema);
//            return message;
//        } catch (Exception e) {
//            throw new IllegalStateException(e.getMessage(), e);
//        }
//    }
//
//    private static <T> Schema<T> getSchema(Class<T> cls) {
//        Schema<T> schema = schema = RuntimeSchema.getSchema(cls);
//        return schema;
//    }
//}
