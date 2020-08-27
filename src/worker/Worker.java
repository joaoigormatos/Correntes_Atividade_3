package worker;

import app.App;


public class Worker extends Thread {
    private App appInstance;
    private int position;
    public Worker(int i){
        position = i;
        appInstance = App.getInstance();
    }
    @Override
    public void run() {
        appInstance.setMyNumber(appInstance.getArrayCElement(position));
        appInstance.setCount(countTimesBiggerThan(appInstance.getMyNumber(),appInstance.getArrayC()));
        appInstance.setArrayDAtPosition(appInstance.getCount(),appInstance.getMyNumber());
    }
    private int countTimesBiggerThan(int number, int [] array){
        int count = 0;
        for (int elem: array){
            if( number > elem){
                count++;
            }
        }
        return count ==appInstance.getArrayC().length? count -1: count;
    }
}

