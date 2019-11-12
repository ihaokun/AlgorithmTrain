package classloader;

/**
 * @author ihaokun
 * @date 2019/11/11 10:54
 */
public class SelfClassLoader extends ClassLoader {
  static class SuperClass{
    static {
      System.out.println("SuperClass Init");
    }
    public static int value = 1;
  }
  static class SubClass extends SuperClass{
    static {
      System.out.println("SubClass Init");
    }
  }
  static class ConstantClass{
    static {
      System.out.println("ConstantClass Init");
    }
    final static int INT_CONSTANT = 1;
  }
  public static void main(String[] args) {
    // 1. 使用静态字段，只会对定义字段的类进行初始化
    // System.out.println(SubClass.value);
    // 2. 通过数组定义来使用类，不会导致类的初始化
    // SuperClass[] supers = new SuperClass[10];
    // 3. final修饰的字段是常量，会在编译期存入常量池中，不会导致初始化
    // System.out.println(ConstantClass.INT_CONSTANT);
    try{
      Object obj = new SelfClassLoader().loadClass("classloader.SelfClassLoader").newInstance();
      System.out.println(obj instanceof SelfClassLoader);
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    System.out.println(name);
    return super.loadClass(name);
  }
}
