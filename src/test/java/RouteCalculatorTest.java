import core.Line;
import core.Station;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RouteCalculatorTest {
  private StationIndex stationIndex = new StationIndex();
  private RouteCalculator calculator = new RouteCalculator(stationIndex);


  @Test
  void getRouteOnTheLine() {
    //GIVEN
    Line line = new Line(5, "Кировско-Выборгская");

    Station station1 = new Station("Девяткино", line);
    Station station2 = new Station("Гражданский проспект", line);
    Station station3 = new Station("Академическая", line);
    Station station4 = new Station("Политехническая", line);
    Station station5 = new Station("Площадь Мужества", line);

    line.addStation(station1);
    line.addStation(station2);
    line.addStation(station3);
    line.addStation(station4);
    line.addStation(station5);

    List<Station> expectedList = List.of(station1, station2, station3, station4, station5);

    //WHEN
    stationIndex.addStation(station1);
    stationIndex.addStation(station2);
    stationIndex.addStation(station3);
    stationIndex.addStation(station4);
    stationIndex.addStation(station5);

    stationIndex.addLine(line);

    List<Station> actualList = calculator.getShortestRoute(station1, station5);

    //THEN
    Assertions.assertEquals(expectedList, actualList);
  }

  @Test
  void getRouteWithOneConnection() {
    //GIVEN
    Line lineViolet = new Line(5, "Фрунзенско-Приморская");
    Line lineBlue = new Line(1, "Московско-Петроградская");

    Station station1 = new Station("Адмиралтейская", lineViolet);
    Station station2 = new Station("Садовая", lineViolet);
    Station station3 = new Station("Сенная Площадь", lineBlue);
    Station station4 = new Station("Невский проспект", lineBlue);
    Station station5 = new Station("Горьковская", lineBlue);

    lineViolet.addStation(station1);
    lineViolet.addStation(station2);
    lineBlue.addStation(station3);
    lineBlue.addStation(station4);
    lineBlue.addStation(station5);

    List<Station> expectedList = List.of(station1, station2, station3, station4, station5);

    //WHEN
    stationIndex.addStation(station1);
    stationIndex.addStation(station2);
    stationIndex.addStation(station3);
    stationIndex.addStation(station4);
    stationIndex.addStation(station5);

    stationIndex.addLine(lineBlue);
    stationIndex.addLine(lineViolet);

    stationIndex.addConnection(List.of(station2, station3));

    List<Station> actualList = calculator.getShortestRoute(station1, station5);

    //THEN
    Assertions.assertEquals(expectedList, actualList);
  }

  @Test
  void getRouteWithTwoConnections() {
    //GIVEN
    Line lineViolet = new Line(5, "Фрунзенско-Приморская");
    Line lineBlue = new Line(1, "Московско-Петроградская");
    Line lineGreen = new Line(2, "Московско-Петроградская");

    Station station1 = new Station("Адмиралтейская", lineViolet);
    Station station2 = new Station("Садовая", lineViolet);
    Station station3 = new Station("Сенная Площадь", lineBlue);
    Station station4 = new Station("Невский проспект", lineBlue);
    Station station5 = new Station("Гостиный двор", lineGreen);
    Station station6 = new Station("Василеостровская", lineGreen);

    List<Station> expectedList = List.of(station1, station2, station3, station4, station5, station6);

    //WHEN
    lineViolet.addStation(station1);
    lineViolet.addStation(station2);
    lineBlue.addStation(station3);
    lineBlue.addStation(station4);
    lineGreen.addStation(station5);
    lineGreen.addStation(station6);

    stationIndex.addLine(lineViolet);
    stationIndex.addLine(lineBlue);
    stationIndex.addLine(lineGreen);

    stationIndex.addConnection(List.of(station2, station3));
    stationIndex.addConnection(List.of(station4, station5));

    List<Station> actualList = calculator.getShortestRoute(station1, station6);

    //THEN
    Assertions.assertEquals(expectedList, actualList);
  }

  @Test
  void calculateDuration() {
    //GIVEN
    double expectedDouble = 8.5;
    //WHEN
    Line lineRed = new Line(5, "Кировско-Выборгская");
    Line lineGreen = new Line(2, "Московско-Петроградская");

    Station station1 = new Station("Чернышевская", lineRed);
    Station station2 = new Station("Площадь Восстания", lineRed);
    Station station3 = new Station("Маяковская", lineRed);
    Station station4 = new Station("Площадь Александра Невского", lineGreen);

    lineRed.addStation(station1);
    lineRed.addStation(station2);
    lineRed.addStation(station3);
    lineRed.addStation(station4);

    stationIndex.addStation(station1);
    stationIndex.addStation(station2);
    stationIndex.addStation(station3);
    stationIndex.addStation(station4);

    stationIndex.addConnection(List.of(station2, station3));

    double actualDouble = RouteCalculator.calculateDuration(List.of(station1, station2, station3, station4));

    //THEN
    Assertions.assertEquals(actualDouble, expectedDouble);
  }
}