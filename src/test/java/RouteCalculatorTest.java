import core.Line;
import core.Station;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RouteCalculatorTest {

  @Test
  void getShortestRoute() {
    //GIVEN
    Line lineRed = new Line(1, "Кировско-Выборгская");
    Line lineViolet = new Line(5, "Фрунзенско-Приморская");
    Station station1 = new Station("Лесная", lineRed);
    Station station2 = new Station("Площадь Ленина", lineRed);
    Station station3 = new Station("Выборгская", lineRed);
    Station station4 = new Station("Площадь Чернышевская", lineRed);
    Station station5 = new Station("Площадь Восстания", lineRed);
    Station station6 = new Station("Пушкинская", lineRed);
    Station station7 = new Station("Звенигородская", lineViolet);
    Station station8 = new Station("Обводный канал", lineViolet);
    Station station9 = new Station("Волковская", lineViolet);

    List<Station> expectedList = List.of(station1, station2, station3, station4, station5,
        station6, station7, station8, station9);

    //WHEN
    StationIndex index = new StationIndex();
    RouteCalculator calculator = new RouteCalculator(index);
    List<Station> actualList = calculator.getShortestRoute(station1, station9);

    //THEN
    Object[] objects = actualList.toArray();
    Object[] objects1 = expectedList.toArray();
    Assertions.assertArrayEquals(objects, objects1);
  }

}