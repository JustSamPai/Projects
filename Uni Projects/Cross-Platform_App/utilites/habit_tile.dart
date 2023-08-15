import 'package:flutter/material.dart';
import 'package:flutter_slidable/flutter_slidable.dart';

class HabitTile extends StatelessWidget {
  final String habitName;
  final bool isCompleted;
  final Function(bool?)? onChange;
  final Function(BuildContext)? settingsTapped;
  final Function(BuildContext)? onDelete;
  const HabitTile({
    super.key,
    required this.habitName,
    required this.isCompleted,
    required this.onChange,
    required this.onDelete,
    required this.settingsTapped,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(24),
      child: Slidable(
        endActionPane: ActionPane(
          motion: StretchMotion(),
          children: [
            //settings
            SlidableAction(
              onPressed: settingsTapped,
              backgroundColor: Color.fromARGB(255, 48, 0, 80),
              icon: Icons.settings,
              borderRadius: BorderRadius.circular(12),
            ),

            //delete
            SlidableAction(
              onPressed: onDelete,
              backgroundColor: Colors.red,
              icon: Icons.delete,
              borderRadius: BorderRadius.circular(12),
            ),
          ],
        ),
        child: Container(
          padding: EdgeInsets.all(16),
          decoration: BoxDecoration(
            color: Colors.deepPurpleAccent,
            borderRadius: BorderRadius.circular(12),
          ),
          child: Row(
            children: [
              // checkboxc
              Checkbox(
                value: isCompleted,
                onChanged: onChange,
              ),

              //habit name
              Text(habitName),
            ],
          ),
        ),
      ),
    );
  }
}
