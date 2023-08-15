import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:reading_app/main.dart';
import 'package:reading_app/pages/home_page.dart';
import 'package:reading_app/pages/second_page.dart';
import 'package:reading_app/pages/quiz_page.dart';

class SwitchPage extends StatefulWidget {
  const SwitchPage({super.key});

  @override
  State<SwitchPage> createState() => _SwitchPageState();
}

class _SwitchPageState extends State<SwitchPage> {
  int index = 0;

  final pages = [
    HomePage(),
    SecondPage(),
    QuizPage(),


  ];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: pages[index],
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: index,
        onTap: (int index) => setState(() => this.index = index),
        items: [
          BottomNavigationBarItem(icon: Icon(Icons.home), label: ''),
          BottomNavigationBarItem(icon: Icon(Icons.person), label: ''),
          BottomNavigationBarItem(icon: Icon(Icons.library_books), label: ''),
          // BottomNavigationBarItem(icon: Icon(Icons.book), label: ''),
        ],
      ),
    );
  }
}
