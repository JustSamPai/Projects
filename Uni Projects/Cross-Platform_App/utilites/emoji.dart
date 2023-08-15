import 'package:flutter/material.dart';

class Emoji extends StatelessWidget {
  final String readingEmoji;
  const Emoji({
    Key? key,
    required this.readingEmoji,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: Colors.blue[600],
        borderRadius: BorderRadius.circular(12),
      ),
      padding: EdgeInsets.all(12),
      child: Center(
        child: Text(
          readingEmoji,
          style: TextStyle(
            fontSize: 38,
          ),
        ),
      ),
    );
  }
}
