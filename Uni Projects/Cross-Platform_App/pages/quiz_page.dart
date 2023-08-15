

import 'package:flutter/material.dart';
import '../utilites/quiz_view.dart';

class QuizPage extends StatefulWidget {


  @override
  _QuizPageState createState() => _QuizPageState();
}

class _QuizPageState extends State<QuizPage> {
  double percentage = 0;
  int xpEarned = 0;
  int currentLevel = 0;
  bool _quiz1Completed = false; // Added flag for quiz 1 completion
  bool _quiz2Completed = false; // Added flag for quiz 2 completion

  void _startQuiz1() async {
    if (!_quiz1Completed) { // Check if quiz 1 is not completed
      Map<String, dynamic> quizResult = await Navigator.push(
        context,
        MaterialPageRoute(
          builder: (BuildContext context) => QuizView(),
        ),
      );

      if (quizResult != null) {
        setState(() {
          percentage = quizResult['percentage'];
          xpEarned = quizResult['xpEarned'];
          currentLevel = quizResult['level'];
          _quiz1Completed = true; // Set quiz 1 completion flag
        });
      }
    }
  }

  void _startQuiz2() {
    // Navigation logic for Quiz 2, you can implement it as needed
    print('Quiz 2 pressed!');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[600],
      appBar: AppBar(
        title: Text('Quiz Page'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
        Text(
        'XP Earned: $xpEarned',
          style: TextStyle(fontSize: 24.0, fontWeight: FontWeight.bold),
        ),
        SizedBox(height: 16.0),
        Text(
          'Current Level: $currentLevel',
          style: TextStyle(fontSize: 18.0),
        ),
        SizedBox(height: 16.0),
        LinearProgressIndicator(
          value: percentage / 100, // Set the progress based on percentage
          minHeight: 16.0,
          backgroundColor: Colors.grey,
          valueColor: AlwaysStoppedAnimation<Color>(Colors.blue),
        ),
        SizedBox(height: 16.0),
        ElevatedButton(
          onPressed: _quiz1Completed ? null : _startQuiz1,
          child: Text('Quiz 1'),
        ),
            ElevatedButton(
              onPressed: _startQuiz2,
              child: Text('Quiz 2'),
            ),
          ],
        ),
      ),
    );
  }
}
