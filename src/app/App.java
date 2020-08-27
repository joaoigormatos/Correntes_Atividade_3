package app;

import worker.Worker;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class App {

    private static  App appInstance = null;
    private  int[] arrayC;
    private  int[] arrayD;
    private final int BOUND = 10;
    private List<Worker> workerList;

    private int myNumber;
    private int count;

    private App(){
        workerList = new ArrayList<>();
    }


    public static App getInstance(){
        if(appInstance==null){
            appInstance = new App();
        }
        return appInstance;
    }
    public void execute(int quantity){
        this.arrayD = new int [quantity];
        this.arrayC = new int [quantity];

        populateArray(quantity);
        initWorkers(quantity);
        callWorkers();
        printArray(arrayC, "ArrayC");
        printArray(arrayD, "ArrayD");
    }

    private void printArray(int[] array, String mensager) {
        System.out.print(mensager+": ");
        for(int elem: array)
            System.out.print(elem+ ",");
        System.out.println();

    }

    private void initWorkers(int quantity){
        for (int i = 0; i < quantity; i++) {
            workerList.add(new Worker(i));
        }
    }
    private void populateArray(int quantity){
        Random random = new Random();
        int number;
        //boolean result;
        for (int i = 0; i < quantity; i++) {
            arrayD[i] = -1;
        }
        while(quantity!=0){
            number = random.nextInt(BOUND) ;
          //  result = Arrays.asList(arrayC).contains(number);
          //  if(!result){
          //  }
            arrayC[quantity-1] = number;
            quantity--;
        }
    }
    private void callWorkers(){
        for(Worker worker: workerList){
            worker.start();
            try{
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int[] getArrayD() {
        return arrayD;
    }

    public void setArrayD(int[] arrayD) {
        this.arrayD = arrayD;
    }

    public int getArrayCElement(int position) {
        return arrayC[position];
    }

    public void setArrayC() {
        this.arrayC = arrayC;
    }

    public int getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(int myNumber) {
        this.myNumber = myNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public void setArrayDAtPosition(int position, int value){
        int equals = countEguals(value);
        this.arrayD[position+equals] = value;
    }
    private int countEguals(int value){
        int countEquals = 0;
        for(int elem: arrayD){
            if(elem == value){
                countEquals++;
            }
        }
        return countEquals;
    }
    public int[] getArrayC() {
        return arrayC;
    }
}
