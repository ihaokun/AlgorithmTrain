import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 一个统计工具类，原有idea插件无统计类数量的功能，这里手写一个
 * 补充：是有的，但没有按包统计
 * 再补充：按选中统计也有
 * 目前希望看到的是能用树形图显式统计，而不是插件这种二维图表的形式，一种较为完全、直观的显示
 *
 * @author ihaokun
 * @date 2020/2/13 20:11
 */
public class Statistic {
  public static void main(String[] args) {
    String currentDir = System.getProperty("user.dir");
    System.out.println(currentDir);
    Path path = Paths.get(currentDir);
    // ofSingle(path.toFile());
    // System.out.println(count);

    // levelTraversal(0, Arrays.asList(path.toFile().listFiles()));

    showFileTree(0, path.toFile());
  }

  private static int count = 0;

  /**
   * 简单统计单个路径下的文件数
   *
   * @param file
   */
  private static void countOfSingle(File file){
    File[] files = file.listFiles();
    if (files != null){
      for (File f : files) {
        if (f.getName().endsWith(".java")) count++;
        countOfSingle(f);
      }
    }
  }

  /**
   * 复杂一点的，文件树结构显示
   * 基本实现了console输出展示的功能，后续可以考虑添加统计的功能，再后续可以考虑前端页面显示而不是console输出
   *
   * @param depth
   * @param file
   */
  private static void showFileTree(int depth, File file){
    for (int i = 0; i < depth; i++) System.out.print("\t");
    System.out.println(file.getName());
    if (file.isDirectory()){
      for (File f : file.listFiles()) {
        if (f.isDirectory()) showFileTree(depth + 1, f);
        else {
          for (int i = 0; i < depth + 1; i++) System.out.print("\t");
          System.out.println(f.getName());
        }
      }
    }
  }

  private void showSystem(){
    // OS环境变量查看
    System.getenv().forEach((key, value) -> System.out.println(key + " : " + value));
    System.out.println("----------------------------------------------");
    // Properties extends Hashtable
    System.getProperties().list(System.out);
  }
}