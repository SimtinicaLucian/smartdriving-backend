package ro.softwarelabz.smartdriving;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ro.softwarelabz.smartdriving.car.domain.Car;
import ro.softwarelabz.smartdriving.car.repository.CarRepository;
import ro.softwarelabz.smartdriving.car.service.CarService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CarServiceUnitTest {
    @Autowired
    private CarService classUnderTest;
    @MockBean
    private CarRepository carRepository;


//    @Test
//    public void add_car_test() {
//        Car car = Car.builder()
//                .carNumber("15BVACV")
//                .insurance(true)
//                .vignette(true)
//                .build();
//
//        Car createdCarMock = Car.builder()
//                .id(432423432421L)
//                .carNumber(car.getCarNumber())
//                .insurance(car.isInsurance())
//                .vignette(car.isVignette())
//                .build();
//        given(carRepository.save(car)).willReturn(createdCarMock);
//
//        var createdCar = classUnderTest.create(car);
//        assertEquals(car.getCarNumber(), createdCar.getCarNumber());
//        assertEquals(createdCarMock.getId(), createdCar.getId());
//    }

//    @Test
//    public void add_car_when_already_exists_one() {
//        Car car = Car.builder()
//                .carNumber("15BC")
//                .insurance(true)
//                .vignette(true)
//                .build();
//
//        given(carRepository.findByCarNumber(car.getCarNumber())).willReturn(List.of(car));
//        Exception exception = assertThrows(RuntimeException.class, () -> classUnderTest.create(car));
//        assertEquals("The car with number 15BC already exists", exception.getMessage());
//    }

}
