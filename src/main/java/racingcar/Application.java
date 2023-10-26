package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Application {
    String[] names = new String[]{"pobi,woni,jun"};

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();
        String[] turnIntoCarArray = input.split(",");
        // valdation input exception
        // setting carArray
        // put that's name on the car
        List<Car> cars = new ArrayList<>();
        for (String eachCar : turnIntoCarArray) {
            cars.add(Car.makeCarOwnName(eachCar));
        }
        System.out.println("시도할 회수는 몇회인가요?");
        int times = Integer.parseInt(Console.readLine());
        System.out.println();
        // validation ( length, only Integers stuff, It should be )
        System.out.println("실행 결과");
        for (int i = 0; i < times; i++) {
            for (Car c : cars) {
                c.dropTheDice();
            }
            broadcastStatus(cars);
            System.out.println();
        }
        System.out.println("최종 우승자 : " + Car.getWinners(cars));
    }
    public static void broadcastStatus(List<Car> carList) {
        for(Car car : carList) {
            System.out.println(car.getName() + " : " + car.makeProgress(car.getStep()));
        }

    }
}

class Car {
    private String name;
    private int step = 0;

    public Car(String name) {
        this.name = name;
    }
    // why should I use static in the scope? If I use in another package, What happened?
    public static Car makeCarOwnName(String name) {
        return new Car(name);
    }
    // Do not this way, Use RequiredConstructor with name


    public String getName() {
        return name;
    }

    public int getStep() {
        return step;
    }

    public void go() {
        step++;
    }
    public void stop() {
    }
    public void dropTheDice() {
        if (Randoms.pickNumberInRange(0, 9) >= 4)
            this.go();
    }


    public String makeProgress(int num) {
        StringBuilder dash = new StringBuilder();
        for (int i = 0; i < num; i++) {
            dash.append("-");
        }
        return dash.toString();
    }
    public static List<Car> findWinnerIsExist(List<Car> carList) {
        List<Car> winners = new ArrayList<>();

        List<Car> sortedCarList = carList.stream()
            .sorted(Comparator.comparingInt(Car::getStep).reversed()) // step 속성을 내림차순으로 정렬
            .toList();
        for (Car c : sortedCarList) {
            if (winners.isEmpty()) {
                winners.add(c);
                continue;
            }
            if (winners.get(0).getStep() == c.getStep()) {
                winners.add(c);
            }
        }

        return winners;
    }
    public static String getWinners(List<Car> cars) {
        int max;
        List<Car> list = findWinnerIsExist(cars);
        max = list.size();
        if (max == 1) {

            return list.get(0).getName();
        } else {
            StringBuilder sentence = new StringBuilder();
            for (Car c : list) {
                sentence.append(c.getName()).append(", ");
            }
            return sentence.substring(0, max - 2);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


