import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:hive/hive.dart';
import 'package:reading_app/utilites/habit_tile.dart';
import 'package:reading_app/utilites/new_habit_box.dart';
import 'package:reading_app/utilites/streak_map.dart';

import '../data/streak_database.dart';
import '../utilites/my_fab.dart';

class SecondPage extends StatefulWidget {
  const SecondPage({super.key});

  @override
  State<SecondPage> createState() => _SecondPageState();
}

class _SecondPageState extends State<SecondPage> {
 StreakDatabase db = StreakDatabase();
 final _myBox = Hive.box("Streak_Database");

  bool isCompleted = false;

  void initState() {

    if (_myBox.get("CURRENT_STREAK") == null) {
      db.createDefaultData();
    }
    else {
      db.loadData();
    }

    super.initState();
  }

  void checkBoxTapped(bool? value, int index) {
    setState(() {
      db.todaysHabitList[index][1] = value;
    });
    db.updateDatabase();

  }

  final _newHabitController = TextEditingController();

  void addHabit() {
    showDialog(
      context: context,
      builder: (context) {
        return HabitBox(
          hintText: 'Enter Habit Name',
          controller: _newHabitController,
          onSave: saveNewHabit,
          onCancel: cancelNewHabit,
        );
      },
    );
    db.updateDatabase();

  }

  void saveNewHabit() {
    setState(() {
      db.todaysHabitList.add([_newHabitController.text, false]);
    });
    _newHabitController.clear();
    Navigator.of(context).pop();
    db.updateDatabase();
  }

  void cancelNewHabit() {
    _newHabitController.clear();
    Navigator.of(context).pop();
  }

  void saveExistingHabit(int index) {
    setState(() {
      db.todaysHabitList[index][0] = _newHabitController.text;
    });
    _newHabitController.clear();
    Navigator.pop(context);
    db.updateDatabase();

  }

  void deleteHabit(int index) {
    setState(() {
      db.todaysHabitList.removeAt(index);
    });
    db.updateDatabase();

  }

  void openHabitSettings(int index) {
    showDialog(
      context: context,
      builder: (context) {
        return HabitBox(
          controller: _newHabitController,
          hintText: db.todaysHabitList[index][0],
          onSave: () => saveExistingHabit(index),
          onCancel: cancelNewHabit,
        );
      },
    );
    db.updateDatabase();

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[600],
      floatingActionButton: ActionButton(
        onPressed: addHabit,
      ),
      body: ListView(
        children: [
          MonthlySummary(
              datasets: db.heatMapDataset,
              startDate: _myBox.get("START_DATE")
          ),
          ListView.builder(
            shrinkWrap: true,
              physics: const NeverScrollableScrollPhysics(),
              itemCount: db.todaysHabitList.length,
              itemBuilder: (context, index) {
                return HabitTile(
                  habitName: db.todaysHabitList[index][0],
                  isCompleted: db.todaysHabitList[index][1],
                  onChange: (value) => checkBoxTapped(value, index),
                  settingsTapped: (context) => openHabitSettings(index),
                  onDelete: (context) => deleteHabit(index),
                );
              }),
        ],
      )
    );
  }
}
