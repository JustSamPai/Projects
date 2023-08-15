import 'package:flutter/material.dart';
import 'package:hive/hive.dart';
import 'package:hive_flutter/adapters.dart';
import 'package:reading_app/pages/home_page.dart';
import 'package:reading_app/pages/quiz_page.dart';
import 'package:reading_app/pages/second_page.dart';
import 'package:reading_app/pages/switch_page.dart';



void main() async {
  await Hive.initFlutter();
  await Hive.openBox("Streak_Database");
  runApp(const MyApp());
}


class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: SwitchPage(),
      theme: ThemeData(primarySwatch: Colors.purple),
      routes: {
        '/second': (context) => QuizPage(),
      },
    );
  }
}
