import java.io.*;
import java.util.*;
public class MyTxtReader {
    public  static class Students implements Comparable<Students>{
        public String num;
        public String name;
        public String num1;
        public Students()
        {
            num=null;
            name=null;
            num1=null;
        }
        public void set(String a,String b)
        {
            num=a;
            name=b;
            if(num.charAt(0)>'9'&&num.charAt(0)<'0') num1=num.substring(1);
            else num1=num;
        }
        public void display(){
            System.out.println("学号:"+num+" 姓名:"+name);
        }

        @Override
        public int compareTo(Students o) {
            return this.num1.compareTo(o.num1);
        }
        @Override
        public String toString() {
            return "Person [姓名=" + name + ", 学号=" + num + "]";
        }
    }
    public static void main(String[] args){
        Students [] a = new Students[1000];
        String b;
        int i=0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("班级名单.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\t");
                if (words.length >= 2) { // 检查数组长度是否足够
                    a[i] = new Students(); // 初始化数组元素
                    a[i].set(words[0], words[1]);
                    i++;
                } else {
                    System.out.println("Invalid format: " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        a = Arrays.stream(a)
                .filter(Objects::nonNull)
                .toArray(Students[]::new);
        Arrays.sort(a);
        for (int j=0;j<i;j++) {
            a[j].display();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("班级名单-sorted.txt"));
            for(int j=0;j<i;j++)
            {
                writer.write(a[j].num+"\t"+a[j].name);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("请输入查找人员学号：");
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            String n = sc.nextLine();
            int j=0;
            for (;j<i;j++) {
                if(Objects.equals(n, a[j].num))
                {
                    a[j].display();
                    break;
                }
            }
            if(j==i) System.out.println("错误，未找到人员");
            System.out.println("请输入查找人员学号：");
        }
    }
}
