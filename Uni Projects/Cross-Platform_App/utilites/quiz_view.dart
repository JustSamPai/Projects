import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:reading_app/utilites/quiz_question.dart';

class QuizView extends StatefulWidget {
  @override
  _QuizViewState createState() => _QuizViewState();
}

class _QuizViewState extends State<QuizView> {
  int currentQuestionIndex = 0;
  List<String> selectedAnswers = List<String>.filled(quizQuestions.length, '');
  int correctAnswers = 0;
  bool quizCompleted = false;
  int currentXP = 0;
  int currentLevel = 1;
  int xpNeededForNextLevel = 10;

  void _submitAnswer(String answer) {
    if (quizCompleted) {
      return;
    }

    setState(() {
      selectedAnswers[currentQuestionIndex] = answer;
    });

    if (answer == quizQuestions[currentQuestionIndex]['correctAnswer']) {
      setState(() {
        correctAnswers++;
      });
    }

    if (currentQuestionIndex < quizQuestions.length - 1) {
      setState(() {
        currentQuestionIndex++;
      });
    } else {
      double percentage = (correctAnswers / quizQuestions.length) * 100;
      int xpEarned = (percentage / 10).floor();

      // Update XP and check for level up
      setState(() {
        currentXP += xpEarned;
        if (currentXP >= xpNeededForNextLevel) {
          currentXP -= xpNeededForNextLevel;
          currentLevel++;
          xpNeededForNextLevel = ((currentLevel / 0.07) * (currentLevel / 0.07)).floor();
        }
        quizCompleted = true;
      });

      Navigator.pop(
        context,
        {
          'percentage': percentage,
          'xpEarned': xpEarned,
          'level': currentLevel,
        },
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Quiz View'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Question ${currentQuestionIndex + 1}',
              style: TextStyle(fontSize: 24.0, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 16.0),
            Text(
              quizQuestions[currentQuestionIndex]['question'],
              style: TextStyle(fontSize: 18.0),
              textAlign: TextAlign.center,
            ),
            SizedBox(height: 16.0),
            Column(
              children: quizQuestions[currentQuestionIndex]['answers']
                  .map<Widget>(
                    (answer) => ElevatedButton(
                  onPressed: quizCompleted ? null : () => _submitAnswer(answer),
                  child: Text(answer),
                ),
              )
                  .toList(),
            ),
            SizedBox(height: 16.0),
            ElevatedButton(
              onPressed: quizCompleted ? null : () => _submitAnswer(''),
              child: Text('Submit'),
            ),
            SizedBox(height: 16.0),
            Text(
              'XP: $currentXP / $xpNeededForNextLevel', // Display current XP and XP needed for next level
              style: TextStyle(fontSize: 18.0),
            ),
            SizedBox(height: 8.0),
            LinearProgressIndicator(
              value: currentXP / xpNeededForNextLevel, // Show progress in the progress bar
            ),
          ],
        ),
      ),
    );
  }
}
