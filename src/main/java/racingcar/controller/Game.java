package racingcar.controller;

import java.util.List;
import racingcar.InputView;
import racingcar.OutputView;
import racingcar.domain.Car;
import racingcar.service.CarService;

public class Game {
    private final CarService carService;
    private final InputView inputView;
    private final OutputView outputView;

    public Game(CarService carService, InputView inputView, OutputView outputView) {
        this.carService = carService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void ready() {
        outputView.naming();
        for (String ownerName : inputView.carNameList()) {
            carService.carGenerate(ownerName);
        }
    }

    public void start(int count) {
    }

    public void running(List<Car> cars) {
    }

    public void end() {

    }

    public void racing() {
        ready();
        outputView.counting();
        start(inputView.roundAmount());
        end();
    }
}
