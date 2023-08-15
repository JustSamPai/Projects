import 'dart:io';
import 'package:flutter/material.dart';
import 'package:hive/hive.dart';
import 'package:path/path.dart' as path;
import 'package:path_provider/path_provider.dart';
import 'package:flutter_pdfview/flutter_pdfview.dart';
import 'package:file_picker/file_picker.dart';
import 'package:http/http.dart' as http;

import '../data/streak_database.dart';
import '../utilites/PdfViewPage.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  List<String> _books = [];
  List<String> _filteredBooks = [];
  StreakDatabase db = StreakDatabase();

  void initState() {
      db.loadData();


    super.initState();
  }

  void _onBookLongPress(String bookPath) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text('Delete Book?'),
        content: Text('Are you sure you want to delete this book?'),
        actions: [
          TextButton(
            onPressed: () {
              Navigator.pop(context);
            },
            child: Text('Cancel'),
          ),
          TextButton(
            onPressed: () {
              setState(() {
                _books.remove(bookPath);
              });
              Navigator.pop(context);
            },
            child: Text('Delete'),
          ),
        ],
      ),
    );
  }

  Future<void> _addBookFromDevice() async {
    FilePickerResult? result = await FilePicker.platform.pickFiles(
    );
    if (result != null && result.files.isNotEmpty) {
      String? pickedFilePath = result.files.first.path;
      if (pickedFilePath != null) {
        setState(() {
          _books.add(pickedFilePath);
        });
      }
    }

  }

  void _openBook(String bookPath, String bookTitle) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => PdfViewerPage(bookPath: bookPath, bookTitle: bookTitle),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[600],
      appBar: AppBar(
        title: Text('My Books'),
      ),
      body: Column(
        children: [
          Padding(
            padding: EdgeInsets.all(16.0),
            child: TextField(
              decoration: InputDecoration(
                suffixIcon: IconButton(
                  icon: Icon(Icons.search), onPressed: () {  },
                )
              ),
              onChanged: (value) {
                setState(() {
                  _books = _books.where((bookPath) {
                    final bookTitle = path.basenameWithoutExtension(bookPath);
                    return bookTitle.toLowerCase().contains(value.toLowerCase());
                  }).toList();
                });
              },
            ),
          ),
          Expanded(
            child: ListView.builder(
              itemCount: _books.length,

              itemBuilder: (context, index) {
                String bookFilePath = _books[index];
                String bookTitle = path.basenameWithoutExtension(bookFilePath);

                return GestureDetector(
                  onLongPress: () {
                    _onBookLongPress(bookFilePath);
                  },
                  child: ListTile(
                    leading: Icon(Icons.picture_as_pdf),
                    title: Text(bookTitle),
                    onTap: () {
                      _openBook(bookFilePath, bookTitle);
                    },
                  ),
                );
              },
            ),

          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _addBookFromDevice,
        child: Icon(Icons.add),
      ),
    );
  }
}
