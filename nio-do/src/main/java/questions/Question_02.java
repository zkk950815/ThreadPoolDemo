package questions;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Question_02 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newCachedThreadPool();
        StringBuilder sb = new StringBuilder();
        char[] chars;
        for (int i = 0; i < 10000000; i++) {

            //for (int j = 0; j < 4; j++) {

            //}
            chars = new char[]{(char) (int) (26 * Math.random() + 97), (char) (int) (26 * Math.random() + 97), (char) (int) (26 * Math.random() + 97), (char) (int) (26 * Math.random() + 97)};
            sb.append(chars);
            sb.append(",");
            sb.append((int) (100 * Math.random()));
            sb.append(",");
            sb.append((int) (5 * Math.random()));
            sb.append((char) 10);

        }

        System.out.println(System.currentTimeMillis()-start);//1.9
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/salary/salary.txt", true), "utf-8"));
            bw.write(sb.toString());
            //bw.flush();
            //System.out.println(System.currentTimeMillis()-start);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            service.shutdown();
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis()-start);//2.5


        BufferedReader br = null;
        List<String> salaryList = new ArrayList<>();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/salary/salary.txt"), "utf-8"));
            String oneLine = null;
            while ((oneLine = br.readLine()) != null) {
                salaryList.add(oneLine);//太慢
            }
            //System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis()-start);//8.9

        List<String> standardPreNameList = new ArrayList<>();
        char name_01;
        char name_02;
        for (int i = 0; i < 26; i++) {
            name_01 = (char) (97 + i);
            for (int j = 0; j < 26; j++) {
                name_02 = (char) (97 + j);
                standardPreNameList.add(new String(new char[]{name_01, name_02}));
            }
        }
        System.out.println(System.currentTimeMillis()-start);
        Map<String, Integer> salaryMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        for (String salary : salaryList) {
            if (salary.trim() == "") break;
            String namePre = null;
            namePre = salary.substring(0, 2);
            String[] informations = new String[0];
            informations = salary.split(",");


            int salaryNum = Integer.parseInt(informations[1]) * 13 + Integer.parseInt(informations[2]);
            int preSalaryNum = salaryMap.get(namePre) == null ? 0 : salaryMap.get(namePre);
            if (salaryMap.get(namePre) != null) {
                countMap.put(namePre, countMap.get(namePre) == null ? 0 : countMap.get(namePre) + 1);
            }
            salaryMap.put(namePre, salaryNum + preSalaryNum);


        }
        System.out.println(System.currentTimeMillis()-start);
        List<String> topSalary = new ArrayList<>();
        for (String standardPreName : standardPreNameList) {
            int count = 0;
            Set<String> names = salaryMap.keySet();
            Iterator<String> iterator = names.iterator();
            while (iterator.hasNext()) {
                String name = iterator.next();
                if (standardPreName.equals(name)) {
                    count++;
                    topSalary.add(standardPreName + "," + salaryMap.get(standardPreName) + "," + countMap.get(standardPreName));
                }
            }
        }
        System.out.println(System.currentTimeMillis()-start);
        topSalary.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int salary_1 = Integer.parseInt(o1.split(",")[1]);
                int salary_2 = Integer.parseInt(o2.split(",")[1]);
                return salary_2 - salary_1;
            }
        });
        /*for (int i = 0; i < 10; i++) {
            System.out.println(topSalary.get(i));
        }*/
        System.out.println(System.currentTimeMillis() - start);
    }
}
