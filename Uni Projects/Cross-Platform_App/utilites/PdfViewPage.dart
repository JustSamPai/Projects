import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_pdfview/flutter_pdfview.dart';

class PdfViewerPage extends StatefulWidget {
  final String bookPath;

  PdfViewerPage({required this.bookPath, required String bookTitle});

  @override
  _PdfViewerPageState createState() => _PdfViewerPageState();
}

class _PdfViewerPageState extends State<PdfViewerPage> {
  late PDFViewController _pdfViewController;
  int _currentPage = 0;
  bool _isBookmarked = false;

  void _onPageChanged(int? page, int? total) {
    setState(() {
      _currentPage = page ?? 0;
    });
  }

  void _toggleBookmark() {
    setState(() {
      _isBookmarked = !_isBookmarked;
    });
  }

  void _scrollToPage(int page) {
    _pdfViewController.setPage(page);
  }

  void _onRender(int? pages) {
    // Function to handle when the PDF document is rendered
    setState(() {
      _isBookmarked = false;
    });
  }
  void _firstPage(){
    _scrollToPage(0);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Book Viewer'),
        actions: [
          IconButton(
            icon: Icon(_isBookmarked ? Icons.bookmark : Icons.bookmark_border),
            onPressed: _toggleBookmark,
          ),
          IconButton(
            icon: Icon(Icons.search),
            onPressed: () {
              // Implement search functionality here
            },
          ),
          IconButton(
            icon: Icon(Icons.format_list_numbered),
            onPressed: () {
              // Implement page navigation functionality here
            },
          ),
        ],
      ),
      body: PDFView(
        filePath: widget.bookPath,
        enableSwipe: true,
        swipeHorizontal: false,
        autoSpacing: false,
        pageFling: false,
        defaultPage: _currentPage,
        fitPolicy: FitPolicy.BOTH,
        onRender: (_pages) => _onRender(_pages), // Update callback to onRender
        onError: (error) {
          // Function to handle any errors that occur during PDF loading
        },
        onPageError: (page, error) {
          // Function to handle errors that occur on a specific page
        },
        onViewCreated: (PDFViewController controller) {
          setState(() {
            _pdfViewController = controller;
          });
        },
        onPageChanged: (page, total) => _onPageChanged(page, total),
      ),
    );
  }
}
