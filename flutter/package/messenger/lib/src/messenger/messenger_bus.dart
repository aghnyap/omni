import 'dart:async';

import 'package:injectable/injectable.dart';

@singleton
class MessengerBus {
  final _controller = StreamController.broadcast();

  Stream get stream => _controller.stream;

  void fire(event) {
    _controller.add(event);
  }

  void dispose() {
    _controller.close();
  }
}
