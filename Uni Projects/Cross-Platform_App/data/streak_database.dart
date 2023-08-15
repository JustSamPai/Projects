//reference
import 'package:hive/hive.dart';
import 'package:reading_app/data/date.dart';

final _myBox = Hive.box("Streak_Database");


class StreakDatabase {
  List pdfList = [];
  List todaysHabitList = [];
  Map<DateTime, int> heatMapDataset = {};

  //reate initial defautl data
  void createDefaultData() {
    todaysHabitList = [
      ["Read", false],
      ["Quiz", false],
    ];
    _myBox.put("START_DATE", todaysDateFormatted());
  }
  void updateHeatMapDataset() {
    heatMapDataset = {};
    for (int i = 0; i < _myBox.length; i++) {
      if (_myBox.keyAt(i) != "START_DATE" && _myBox.keyAt(i) != "CURRENT_STREAK") {
        List<dynamic> habits = _myBox.get(_myBox.keyAt(i));
        int count = 0;
        for (int j = 0; j < habits.length; j++) {
          if (habits[j][1] == true) {
            count++;
          }
        }
        heatMapDataset[DateTime.parse(_myBox.keyAt(i))] = count;
      }
    }
  }


  //load data if ti already exists
  void loadData() {
    if (_myBox.get(todaysDateFormatted()) == null) {
      todaysHabitList = _myBox.get("CURRENT_STREAK");
      for (int i = 0; i < todaysHabitList.length; i++) {
        todaysHabitList[i][1] = false;
      }
    }
    else {
      todaysHabitList = _myBox.get(todaysDateFormatted());
    }
  }

  //update database
  void updateDatabase() {
    _myBox.put(todaysDateFormatted(), todaysHabitList);

    _myBox.put("CURRENT_STREAK", todaysHabitList);

    calculateHabitPercentages();

    loadHeatMap();
  }

  void calculateHabitPercentages() {
    int count = 0;
    for (int i = 0; i < todaysHabitList.length; i++) {
      if (todaysHabitList[i][1] == true) {
        count++;
      }
    }
    String percent = todaysHabitList.isEmpty
        ? '0.0'
        : (count / todaysHabitList.length).toStringAsFixed(1);

    _myBox.put("STREAK_PERCENT_${todaysDateFormatted()}", percent);
  }

  void loadHeatMap() {
    DateTime startDate = createDateTimeObject(_myBox.get("START_DATE"));
    int daysInBetween = DateTime
        .now()
        .difference(startDate)
        .inDays;
    for (int i = 0; i < daysInBetween + 1; i++) {
      String yyyymmdd = convertDateTimeToString(
        startDate.add(Duration(days: i)),
      );

      double strengthAsPercent = double.parse(
        _myBox.get("PERCENTAGE_SUMMARY_$yyyymmdd") ?? "0.0",
      );

      // split the datetime up like below so it doesn't worry about hours/mins/secs etc.

      // year
      int year = startDate
          .add(Duration(days: i))
          .year;

      // month
      int month = startDate
          .add(Duration(days: i))
          .month;

      // day
      int day = startDate
          .add(Duration(days: i))
          .day;

      final percentForEachDay = <DateTime, int>{
        DateTime(year, month, day): (10 * strengthAsPercent).toInt(),
      };

      heatMapDataset.addEntries(percentForEachDay.entries);
      print(heatMapDataset);
    }
  }
}