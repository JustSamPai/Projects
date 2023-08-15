// import 'package:flutter/cupertino.dart';
// import 'package:flutter/material.dart';
// import 'package:flutter_pdfview/flutter_pdfview.dart';
//
// class _PdfSearchDelegate extends SearchDelegate<String> {
//   final PDFViewController controller;
//   final int pageCount;
//
//   _PdfSearchDelegate({required this.controller, required this.pageCount});
//
//   @override
//   List<Widget>? buildActions(BuildContext context) {
//     return [
//       IconButton(
//         icon: Icon(Icons.clear),
//         onPressed: () {
//           query = '';
//           showSuggestions(context);
//         },
//       ),
//     ];
//   }
//
//   @override
//   Widget? buildLeading(BuildContext context) {
//     return IconButton(
//       icon: Icon(Icons.arrow_back),
//       onPressed: () {
//         close(context, '');
//       },
//     );
//   }
//
//   @override
//   Widget buildResults(BuildContext context) {
//     return Container();
//   }
//
//   @override
//   Widget buildSuggestions(BuildContext context) {
//     if (query.isEmpty) {
//       return Container();
//     }
//
//     final List<int> searchResults = [];
//
//     for (int i = 0; i < pageCount; i++) {
//       final Future<int?> result = controller.search(query, page: i);
//
//       result.then((value) {
//         if (value != null && value >= 0) {
//           setState(() {
//             searchResults.add(i);
//           });
//         }
//       });
//     }
//
//     return ListView.builder(
//       itemCount: searchResults.length,
//       itemBuilder: (BuildContext context, int index) {
//         final int page = searchResults[index];
//         return ListTile(
//           title: Text('Page ${page + 1}'),
//           onTap: () {
//             controller.setPage(page);
//             close(context, query);
//           },
//         );
//       },
//     );
//   }
// }
